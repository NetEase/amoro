/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.amoro.spark.table;

import org.apache.amoro.catalog.ArcticCatalog;
import org.apache.amoro.hive.table.SupportHive;
import org.apache.amoro.spark.reader.SparkScanBuilder;
import org.apache.amoro.spark.writer.ArcticSparkWriteBuilder;
import org.apache.amoro.table.ArcticTable;
import org.apache.amoro.table.TableProperties;
import org.apache.iceberg.Schema;
import org.apache.iceberg.relocated.com.google.common.collect.ImmutableMap;
import org.apache.iceberg.relocated.com.google.common.collect.ImmutableSet;
import org.apache.iceberg.relocated.com.google.common.collect.Sets;
import org.apache.iceberg.spark.Spark3Util;
import org.apache.iceberg.spark.SparkSchemaUtil;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.analysis.NoSuchPartitionException;
import org.apache.spark.sql.catalyst.analysis.PartitionAlreadyExistsException;
import org.apache.spark.sql.connector.catalog.SupportsPartitionManagement;
import org.apache.spark.sql.connector.catalog.SupportsRead;
import org.apache.spark.sql.connector.catalog.SupportsWrite;
import org.apache.spark.sql.connector.catalog.Table;
import org.apache.spark.sql.connector.catalog.TableCapability;
import org.apache.spark.sql.connector.expressions.Transform;
import org.apache.spark.sql.connector.read.ScanBuilder;
import org.apache.spark.sql.connector.write.LogicalWriteInfo;
import org.apache.spark.sql.connector.write.WriteBuilder;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.util.CaseInsensitiveStringMap;

import java.util.Map;
import java.util.Set;

public class ArcticSparkTable
    implements Table,
        SupportsRead,
        SupportsWrite,
        SupportsRowLevelOperator,
        SupportsPartitionManagement {
  private static final Set<String> RESERVED_PROPERTIES =
      Sets.newHashSet("provider", "format", "current-snapshot-id");
  private static final Set<TableCapability> CAPABILITIES =
      ImmutableSet.of(
          TableCapability.BATCH_READ,
          TableCapability.BATCH_WRITE,
          TableCapability.STREAMING_WRITE,
          TableCapability.OVERWRITE_BY_FILTER,
          TableCapability.OVERWRITE_DYNAMIC);

  private final ArcticTable arcticTable;
  private final String sparkCatalogName;
  private StructType lazyTableSchema = null;
  private SparkSession lazySpark = null;
  private final ArcticCatalog catalog;

  public static Table ofArcticTable(
      ArcticTable table, ArcticCatalog catalog, String sparkCatalogName) {
    if (table.isUnkeyedTable()) {
      if (!(table instanceof SupportHive)) {
        return new ArcticIcebergSparkTable(table.asUnkeyedTable(), false, sparkCatalogName);
      }
    }
    return new ArcticSparkTable(table, catalog, sparkCatalogName);
  }

  public ArcticSparkTable(ArcticTable arcticTable, ArcticCatalog catalog, String sparkCatalogName) {
    this.arcticTable = arcticTable;
    this.sparkCatalogName = sparkCatalogName;
    this.catalog = catalog;
  }

  private SparkSession sparkSession() {
    if (lazySpark == null) {
      this.lazySpark = SparkSession.active();
    }

    return lazySpark;
  }

  public ArcticTable table() {
    return arcticTable;
  }

  @Override
  public String name() {
    return sparkCatalogName
        + "."
        + arcticTable.id().getDatabase()
        + "."
        + arcticTable.id().getTableName();
  }

  @Override
  public StructType schema() {
    if (lazyTableSchema == null) {
      Schema tableSchema = arcticTable.schema();
      this.lazyTableSchema = SparkSchemaUtil.convert(tableSchema);
    }

    return lazyTableSchema;
  }

  @Override
  public Transform[] partitioning() {
    // return toTransforms(arcticTable.spec());
    return Spark3Util.toTransforms(arcticTable.spec());
  }

  @Override
  public Map<String, String> properties() {
    ImmutableMap.Builder<String, String> propsBuilder = ImmutableMap.builder();

    if (!arcticTable.properties().containsKey(TableProperties.BASE_FILE_FORMAT)) {
      propsBuilder.put("base.write.format", TableProperties.BASE_FILE_FORMAT_DEFAULT);
    }

    if (!arcticTable.properties().containsKey(TableProperties.DELTA_FILE_FORMAT)) {
      propsBuilder.put(
          TableProperties.DELTA_FILE_FORMAT,
          arcticTable
              .properties()
              .getOrDefault(
                  TableProperties.CHANGE_FILE_FORMAT, TableProperties.CHANGE_FILE_FORMAT_DEFAULT));
    }
    propsBuilder.put("provider", "arctic");
    arcticTable.properties().entrySet().stream()
        .filter(entry -> !RESERVED_PROPERTIES.contains(entry.getKey()))
        .forEach(propsBuilder::put);

    return propsBuilder.build();
  }

  @Override
  public Set<TableCapability> capabilities() {
    return CAPABILITIES;
  }

  @Override
  public String toString() {
    return arcticTable.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } else if (other == null || getClass() != other.getClass()) {
      return false;
    }

    // use only name in order to correctly invalidate Spark cache
    ArcticSparkTable that = (ArcticSparkTable) other;
    return arcticTable.id().equals(that.arcticTable.id());
  }

  @Override
  public int hashCode() {
    // use only name in order to correctly invalidate Spark cache
    return arcticTable.id().hashCode();
  }

  @Override
  public ScanBuilder newScanBuilder(CaseInsensitiveStringMap options) {
    return new SparkScanBuilder(sparkSession(), arcticTable, options);
  }

  @Override
  public WriteBuilder newWriteBuilder(LogicalWriteInfo info) {
    return new ArcticSparkWriteBuilder(arcticTable, info, catalog);
  }

  @Override
  public SupportsExtendIdentColumns newUpsertScanBuilder(CaseInsensitiveStringMap options) {
    return new SparkScanBuilder(sparkSession(), arcticTable, options);
  }

  @Override
  public boolean requireAdditionIdentifierColumns() {
    return true;
  }

  @Override
  public boolean appendAsUpsert() {
    return arcticTable.isKeyedTable()
        && Boolean.parseBoolean(
            arcticTable.properties().getOrDefault(TableProperties.UPSERT_ENABLED, "false"));
  }

  @Override
  public StructType partitionSchema() {
    return SparkSchemaUtil.convert(new Schema(table().spec().partitionType().fields()));
  }

  @Override
  public void createPartition(InternalRow ident, Map<String, String> properties)
      throws PartitionAlreadyExistsException, UnsupportedOperationException {
    throw new UnsupportedOperationException("not supported create partition");
  }

  @Override
  public boolean dropPartition(InternalRow ident) {
    return false;
  }

  @Override
  public void replacePartitionMetadata(InternalRow ident, Map<String, String> properties)
      throws NoSuchPartitionException, UnsupportedOperationException {
    throw new UnsupportedOperationException("not supported replace partition");
  }

  @Override
  public Map<String, String> loadPartitionMetadata(InternalRow ident)
      throws UnsupportedOperationException {
    return null;
  }

  @Override
  public InternalRow[] listPartitionIdentifiers(String[] names, InternalRow ident) {
    return new InternalRow[0];
  }
}
