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

package com.netease.arctic.spark.reader;

import com.netease.arctic.hive.io.reader.AbstractAdaptHiveIcebergDataReader;
import com.netease.arctic.io.ArcticFileIO;
import com.netease.arctic.spark.parquet.SparkParquetRowReaders;
import com.netease.arctic.spark.util.ArcticSparkUtil;
import org.apache.iceberg.Schema;
import org.apache.iceberg.StructLike;
import org.apache.iceberg.parquet.ParquetValueReader;
import org.apache.iceberg.spark.SparkStructLike;
import org.apache.parquet.schema.MessageType;
import org.apache.spark.sql.Row;

import java.util.Map;
import java.util.function.Function;

public class ArcticSparkUnkeyedDataReader extends AbstractAdaptHiveIcebergDataReader<Row> {
  public ArcticSparkUnkeyedDataReader(
      ArcticFileIO fileIO,
      Schema tableSchema,
      Schema projectedSchema,
      String nameMapping,
      boolean caseSensitive) {
    super(fileIO, tableSchema, projectedSchema, nameMapping, caseSensitive,
        ArcticSparkUtil::convertConstant, true);
  }

  @Override
  protected Function<MessageType, ParquetValueReader<?>> getNewReaderFunction(
      Schema projectedSchema,
      Map<Integer, ?> idToConstant) {
    return fileSchema -> SparkParquetRowReaders.buildReader(projectedSchema, fileSchema, idToConstant);
  }

  @Override
  protected Function<Schema, Function<Row, StructLike>> toStructLikeFunction() {
    return schema -> {
      SparkStructLike structLike = new SparkStructLike(schema.asStruct());
      return row -> structLike.wrap(row);
    };
  }
}
