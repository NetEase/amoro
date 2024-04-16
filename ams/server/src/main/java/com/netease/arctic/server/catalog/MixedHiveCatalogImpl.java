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

package com.netease.arctic.server.catalog;

import com.netease.arctic.AmoroTable;
import com.netease.arctic.TableFormat;
import com.netease.arctic.api.CatalogMeta;
import com.netease.arctic.formats.mixed.MixedTable;
import com.netease.arctic.server.persistence.mapper.TableMetaMapper;
import com.netease.arctic.server.table.TableMetadata;
import com.netease.arctic.server.table.internal.InternalTableCreator;
import com.netease.arctic.server.table.internal.InternalTableHandler;
import com.netease.arctic.utils.ArcticCatalogUtil;
import org.apache.amoro.hive.CachedHiveClientPool;
import org.apache.amoro.hive.HMSClient;
import org.apache.amoro.hive.catalog.MixedHiveTables;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.iceberg.rest.requests.CreateTableRequest;
import org.apache.thrift.TException;

import java.util.List;

public class MixedHiveCatalogImpl extends InternalCatalog {
  protected MixedHiveTables tables;
  private volatile CachedHiveClientPool hiveClientPool;

  protected MixedHiveCatalogImpl(CatalogMeta catalogMeta) {
    super(catalogMeta);
    this.tables =
        new MixedHiveTables(
            catalogMeta.getCatalogProperties(), ArcticCatalogUtil.buildMetaStore(catalogMeta));
    hiveClientPool = ((MixedHiveTables) tables()).getHiveClientPool();
  }

  @Override
  public void updateMetadata(CatalogMeta metadata) {
    super.updateMetadata(metadata);
    hiveClientPool = ((MixedHiveTables) tables()).getHiveClientPool();
    this.tables =
        new MixedHiveTables(
            metadata.getCatalogProperties(), ArcticCatalogUtil.buildMetaStore(metadata));
  }

  @Override
  public AmoroTable<?> loadTable(String database, String tableName) {
    TableMetadata tableMetadata =
        getAs(
            TableMetaMapper.class,
            mapper ->
                mapper.selectTableMetaByName(getMetadata().getCatalogName(), database, tableName));
    if (tableMetadata == null) {
      return null;
    }
    return new MixedTable(
        tables.loadTableByMeta(tableMetadata.buildTableMeta()), TableFormat.MIXED_HIVE);
  }

  @Override
  public void createDatabase(String databaseName) {
    // do not handle database operations
  }

  @Override
  public void dropDatabase(String databaseName) {
    // do not handle database operations
  }

  @Override
  public InternalTableCreator newTableCreator(
      String database, String tableName, TableFormat format, CreateTableRequest creatorArguments) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <O> InternalTableHandler<O> newTableHandler(String database, String tableName) {
    throw new UnsupportedOperationException();
  }

  @Override
  protected void decreaseDatabaseTableCount(String databaseName) {
    // do not handle database operations
  }

  @Override
  protected void increaseDatabaseTableCount(String databaseName) {
    // do not handle database operations
  }

  @Override
  public boolean exist(String database) {
    try {
      return hiveClientPool.run(
          client -> {
            try {
              client.getDatabase(database);
              return true;
            } catch (NoSuchObjectException exception) {
              return false;
            }
          });
    } catch (TException | InterruptedException e) {
      throw new RuntimeException("Failed to get databases", e);
    }
  }

  @Override
  public List<String> listDatabases() {
    try {
      return hiveClientPool.run(HMSClient::getAllDatabases);
    } catch (TException | InterruptedException e) {
      throw new RuntimeException("Failed to list databases", e);
    }
  }

  public CachedHiveClientPool getHiveClient() {
    return hiveClientPool;
  }

  private MixedHiveTables tables() {
    return tables;
  }
}
