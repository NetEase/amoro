/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *  *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netease.arctic.spark.test;

import com.netease.arctic.table.PrimaryKeySpec;
import com.netease.arctic.utils.CollectionHelper;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.iceberg.PartitionSpec;
import org.apache.iceberg.Schema;
import org.apache.iceberg.hive.HiveSchemaUtil;
import org.apache.iceberg.types.Type;
import org.apache.iceberg.types.Types;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

public class Asserts {

  public static void assertType(Type expect, Type actual) {
    Assert.assertEquals(
        "type should be same",
        expect.isPrimitiveType(), actual.isPrimitiveType());
    if (expect.isPrimitiveType()) {
      Assert.assertEquals(expect, actual);
    } else {
      List<Types.NestedField> expectFields = expect.asNestedType().fields();
      List<Types.NestedField> actualFields = actual.asNestedType().fields();
      Assert.assertEquals(expectFields.size(), actualFields.size());

      CollectionHelper.zip(expectFields, actualFields)
          .forEach(x -> {
            Assert.assertEquals(x.getLeft().name(), x.getRight().name());
            Assert.assertEquals(x.getLeft().isOptional(), x.getRight().isOptional());
            Assert.assertEquals(x.getLeft().doc(), x.getRight().doc());
            assertType(x.getLeft().type(), x.getRight().type());
          });
    }
  }

  public static void assertPartition(PartitionSpec expectSpec, PartitionSpec actualSpec) {
    Schema expectSchema = expectSpec.schema();
    Schema actualSchema = actualSpec.schema();
    Assertions.assertEquals(expectSpec.fields().size(), actualSpec.fields().size());
    CollectionHelper.zip(expectSpec.fields(), actualSpec.fields())
        .forEach(x -> {
          Assertions.assertEquals(x.getLeft().transform(), x.getRight().transform());
          Assertions.assertEquals(
              expectSchema.findField(x.getLeft().sourceId()).name(),
              actualSchema.findField(x.getRight().sourceId()).name());
        });
  }

  public static void assertPrimaryKey(PrimaryKeySpec expect, PrimaryKeySpec actual) {
    Assertions.assertEquals(expect.fields().size(), actual.fields().size());

    CollectionHelper.zip(expect.fields(), actual.fields())
        .forEach(x -> {
          Assertions.assertEquals(x.getLeft().fieldName(), x.getRight().fieldName());
        });
  }

  public static <K, V> void assertHashMapContainExpect(Map<K, V> expect, Map<K, V> actual) {
    for (K key : expect.keySet()) {
      V _expect = expect.get(key);
      V _actual = actual.get(key);
      Assertions.assertEquals(_expect, _actual);
    }
  }

  public static void assertHiveSchema(Table hiveTable, Schema expectSchema) {
    CollectionHelper.zip(hiveTable.getSd().getCols(), expectSchema.columns())
        .forEach(x -> {
          Assert.assertEquals(x.getLeft().getName(), x.getRight().name());
          String expectTypeInfoString = HiveSchemaUtil.convert(x.getRight().type()).toString();
          Assert.assertEquals(x.getLeft().getType(), expectTypeInfoString);
        });
  }
}
