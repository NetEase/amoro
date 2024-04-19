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

package org.apache.amoro.optimizing;

import org.apache.amoro.data.DataTreeNode;
import org.apache.amoro.data.PrimaryKeyedFile;
import org.apache.amoro.io.MixedFileIO;
import org.apache.amoro.io.reader.DataReaderCommon;
import org.apache.amoro.io.reader.MixedDeleteFilter;
import org.apache.amoro.scan.KeyedTableScanTask;
import org.apache.amoro.scan.MixedFileScanTask;
import org.apache.amoro.table.PrimaryKeySpec;
import org.apache.amoro.utils.map.StructLikeCollections;
import org.apache.iceberg.FileScanTask;
import org.apache.iceberg.Schema;
import org.apache.iceberg.StructLike;
import org.apache.iceberg.io.CloseableIterable;
import org.apache.iceberg.io.CloseableIterator;
import org.apache.iceberg.io.InputFile;
import org.apache.iceberg.mapping.NameMappingParser;
import org.apache.iceberg.parquet.Parquet;
import org.apache.iceberg.parquet.ParquetValueReader;
import org.apache.iceberg.types.Type;
import org.apache.parquet.schema.MessageType;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Abstract implementation of arctic data reader consuming {@link KeyedTableScanTask}, return
 * records after filtering with {@link MixedDeleteFilter}.
 *
 * @param <T> to indicate the record data type.
 */
public abstract class BaseArcticOptimizingDataReader<T> {

  protected final MixedFileIO fileIO;
  protected final Schema tableSchema;
  protected final Schema projectedSchema;
  protected final String nameMapping;
  protected final boolean caseSensitive;
  protected final Set<DataTreeNode> sourceNodes;
  protected final BiFunction<Type, Object, Object> convertConstant;
  protected final PrimaryKeySpec primaryKeySpec;
  protected final boolean reuseContainer;
  protected final RewriteFilesInput rewriteFilesInput;
  protected StructLikeCollections structLikeCollections;

  public BaseArcticOptimizingDataReader(
      MixedFileIO fileIO,
      Schema tableSchema,
      Schema projectedSchema,
      PrimaryKeySpec primaryKeySpec,
      String nameMapping,
      boolean caseSensitive,
      BiFunction<Type, Object, Object> convertConstant,
      Set<DataTreeNode> sourceNodes,
      boolean reuseContainer,
      RewriteFilesInput rewriteFilesInput,
      StructLikeCollections structLikeCollections) {
    this.fileIO = fileIO;
    this.tableSchema = tableSchema;
    this.projectedSchema = projectedSchema;
    this.primaryKeySpec = primaryKeySpec;
    this.nameMapping = nameMapping;
    this.caseSensitive = caseSensitive;
    this.convertConstant = convertConstant;
    this.sourceNodes = sourceNodes;
    this.reuseContainer = reuseContainer;
    this.rewriteFilesInput = rewriteFilesInput;
    this.structLikeCollections =
        structLikeCollections == null ? StructLikeCollections.DEFAULT : structLikeCollections;
  }

  public CloseableIterator<T> readData(KeyedTableScanTask keyedTableScanTask) {
    MixedDeleteFilter<T> mixedDeleteFilter =
        new GenericMixedDeleteFilter(
            keyedTableScanTask,
            tableSchema,
            projectedSchema,
            primaryKeySpec,
            sourceNodes,
            structLikeCollections);
    Schema newProjectedSchema = mixedDeleteFilter.requiredSchema();

    CloseableIterable<T> dataIterable =
        CloseableIterable.concat(
            CloseableIterable.transform(
                CloseableIterable.withNoopClose(keyedTableScanTask.dataTasks()),
                fileScanTask ->
                    mixedDeleteFilter.filter(
                        newParquetIterable(
                            fileScanTask,
                            newProjectedSchema,
                            DataReaderCommon.getIdToConstant(
                                fileScanTask, newProjectedSchema, convertConstant)))));
    return dataIterable.iterator();
  }

  public CloseableIterator<T> readDeletedData(KeyedTableScanTask keyedTableScanTask) {
    List<PrimaryKeyedFile> equDeleteFiles =
        keyedTableScanTask.arcticEquityDeletes().stream()
            .map(MixedFileScanTask::file)
            .collect(Collectors.toList());

    boolean hasDeleteFile =
        keyedTableScanTask.arcticEquityDeletes().size() > 0
            || keyedTableScanTask.dataTasks().stream().allMatch(s -> s.deletes().size() > 0);

    if (hasDeleteFile) {
      MixedDeleteFilter<T> mixedDeleteFilter =
          new GenericMixedDeleteFilter(
              keyedTableScanTask,
              tableSchema,
              projectedSchema,
              primaryKeySpec,
              sourceNodes,
              structLikeCollections);

      Schema newProjectedSchema = mixedDeleteFilter.requiredSchema();

      CloseableIterable<T> dataIterable =
          CloseableIterable.concat(
              CloseableIterable.transform(
                  CloseableIterable.withNoopClose(keyedTableScanTask.dataTasks()),
                  fileScanTask ->
                      mixedDeleteFilter.filterNegate(
                          newParquetIterable(
                              fileScanTask,
                              newProjectedSchema,
                              DataReaderCommon.getIdToConstant(
                                  fileScanTask, newProjectedSchema, convertConstant)))));
      return dataIterable.iterator();
    } else {
      return CloseableIterator.empty();
    }
  }

  protected CloseableIterable<T> newParquetIterable(
      FileScanTask task, Schema schema, Map<Integer, ?> idToConstant) {
    Parquet.ReadBuilder builder =
        Parquet.read(fileIO.newInputFile(task.file().path().toString()))
            .split(task.start(), task.length())
            .project(schema)
            .createReaderFunc(getNewReaderFunction(schema, idToConstant))
            .filter(task.residual())
            .caseSensitive(caseSensitive);

    if (reuseContainer) {
      builder.reuseContainers();
    }
    if (nameMapping != null) {
      builder.withNameMapping(NameMappingParser.fromJson(nameMapping));
    }

    return fileIO.doAs(builder::build);
  }

  protected abstract Function<MessageType, ParquetValueReader<?>> getNewReaderFunction(
      Schema projectSchema, Map<Integer, ?> idToConstant);

  protected abstract Function<Schema, Function<T, StructLike>> toStructLikeFunction();

  private class GenericMixedDeleteFilter extends MixedDeleteFilter<T> {

    protected Function<T, StructLike> asStructLike;

    protected GenericMixedDeleteFilter(
        KeyedTableScanTask keyedTableScanTask,
        Schema tableSchema,
        Schema requestedSchema,
        PrimaryKeySpec primaryKeySpec) {
      super(keyedTableScanTask, tableSchema, requestedSchema, primaryKeySpec);
      this.asStructLike =
          BaseArcticOptimizingDataReader.this.toStructLikeFunction().apply(requiredSchema());
    }

    protected GenericMixedDeleteFilter(
        KeyedTableScanTask keyedTableScanTask,
        Schema tableSchema,
        Schema requestedSchema,
        PrimaryKeySpec primaryKeySpec,
        Set<DataTreeNode> sourceNodes,
        StructLikeCollections structLikeCollections) {
      super(
          keyedTableScanTask,
          tableSchema,
          requestedSchema,
          primaryKeySpec,
          sourceNodes,
          structLikeCollections);
      this.asStructLike =
          BaseArcticOptimizingDataReader.this.toStructLikeFunction().apply(requiredSchema());
    }

    protected GenericMixedDeleteFilter(
        KeyedTableScanTask keyedTableScanTask,
        Schema tableSchema,
        Schema requestedSchema,
        PrimaryKeySpec primaryKeySpec,
        Set<DataTreeNode> sourceNodes) {
      super(keyedTableScanTask, tableSchema, requestedSchema, primaryKeySpec, sourceNodes);
      this.asStructLike =
          BaseArcticOptimizingDataReader.this.toStructLikeFunction().apply(requiredSchema());
    }

    @Override
    protected StructLike asStructLike(T record) {
      return asStructLike.apply(record);
    }

    @Override
    protected InputFile getInputFile(String location) {
      return fileIO.newInputFile(location);
    }

    @Override
    protected MixedFileIO getArcticFileIo() {
      return fileIO;
    }
  }
}
