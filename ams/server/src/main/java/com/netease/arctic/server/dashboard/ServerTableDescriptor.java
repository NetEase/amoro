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

package com.netease.arctic.server.dashboard;

import com.netease.arctic.AmoroTable;
import com.netease.arctic.TableFormat;
import com.netease.arctic.ams.api.TableIdentifier;
import com.netease.arctic.server.catalog.ServerCatalog;
import com.netease.arctic.server.dashboard.model.AmoroSnapshotsOfTable;
import com.netease.arctic.server.dashboard.model.DDLInfo;
import com.netease.arctic.server.dashboard.model.OperationType;
import com.netease.arctic.server.dashboard.model.OptimizingProcessInfo;
import com.netease.arctic.server.dashboard.model.OptimizingTaskInfo;
import com.netease.arctic.server.dashboard.model.PartitionBaseInfo;
import com.netease.arctic.server.dashboard.model.PartitionFileBaseInfo;
import com.netease.arctic.server.dashboard.model.ServerTableMeta;
import com.netease.arctic.server.dashboard.model.TagOrBranchInfo;
import com.netease.arctic.server.persistence.PersistentBase;
import com.netease.arctic.server.table.TableService;
import com.netease.arctic.server.utils.Configurations;
import org.apache.iceberg.util.Pair;
import org.apache.iceberg.util.ThreadPools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class ServerTableDescriptor extends PersistentBase {

  private final Map<TableFormat, FormatTableDescriptor> formatDescriptorMap = new HashMap<>();

  private final TableService tableService;

  public ServerTableDescriptor(TableService tableService, Configurations serviceConfig) {
    this.tableService = tableService;

    // All table formats will jointly reuse the work thread pool named iceberg-worker-pool-%d
    ExecutorService executorService = ThreadPools.getWorkerPool();

    FormatTableDescriptor[] formatTableDescriptors =
        new FormatTableDescriptor[] {
          new MixedAndIcebergTableDescriptor(executorService),
          new PaimonTableDescriptor(executorService)
        };
    for (FormatTableDescriptor formatTableDescriptor : formatTableDescriptors) {
      for (TableFormat format : formatTableDescriptor.supportFormat()) {
        formatDescriptorMap.put(format, formatTableDescriptor);
      }
    }
  }

  public ServerTableMeta getTableDetail(TableIdentifier tableIdentifier) {
    AmoroTable<?> amoroTable = loadTable(tableIdentifier);
    FormatTableDescriptor formatTableDescriptor = formatDescriptorMap.get(amoroTable.format());
    return formatTableDescriptor.getTableDetail(amoroTable);
  }

  public List<AmoroSnapshotsOfTable> getSnapshots(
      TableIdentifier tableIdentifier, String ref, OperationType operationType) {
    AmoroTable<?> amoroTable = loadTable(tableIdentifier);
    FormatTableDescriptor formatTableDescriptor = formatDescriptorMap.get(amoroTable.format());
    return formatTableDescriptor.getSnapshots(amoroTable, ref, operationType);
  }

  public List<PartitionFileBaseInfo> getSnapshotDetail(
      TableIdentifier tableIdentifier, long snapshotId) {
    AmoroTable<?> amoroTable = loadTable(tableIdentifier);
    FormatTableDescriptor formatTableDescriptor = formatDescriptorMap.get(amoroTable.format());
    return formatTableDescriptor.getSnapshotDetail(amoroTable, snapshotId);
  }

  public List<DDLInfo> getTableOperations(TableIdentifier tableIdentifier) {
    AmoroTable<?> amoroTable = loadTable(tableIdentifier);
    FormatTableDescriptor formatTableDescriptor = formatDescriptorMap.get(amoroTable.format());
    return formatTableDescriptor.getTableOperations(amoroTable);
  }

  public List<PartitionBaseInfo> getTablePartition(TableIdentifier tableIdentifier) {
    AmoroTable<?> amoroTable = loadTable(tableIdentifier);
    FormatTableDescriptor formatTableDescriptor = formatDescriptorMap.get(amoroTable.format());
    return formatTableDescriptor.getTablePartitions(amoroTable);
  }

  public List<PartitionFileBaseInfo> getTableFile(
      TableIdentifier tableIdentifier, String partition, Integer specId) {
    AmoroTable<?> amoroTable = loadTable(tableIdentifier);
    FormatTableDescriptor formatTableDescriptor = formatDescriptorMap.get(amoroTable.format());
    return formatTableDescriptor.getTableFiles(amoroTable, partition, specId);
  }

  public List<TagOrBranchInfo> getTableTags(TableIdentifier tableIdentifier) {
    AmoroTable<?> amoroTable = loadTable(tableIdentifier);
    FormatTableDescriptor formatTableDescriptor = formatDescriptorMap.get(amoroTable.format());
    return formatTableDescriptor.getTableTags(amoroTable);
  }

  public List<TagOrBranchInfo> getTableBranches(TableIdentifier tableIdentifier) {
    AmoroTable<?> amoroTable = loadTable(tableIdentifier);
    FormatTableDescriptor formatTableDescriptor = formatDescriptorMap.get(amoroTable.format());
    return formatTableDescriptor.getTableBranches(amoroTable);
  }

  public Pair<List<OptimizingProcessInfo>, Integer> getOptimizingProcessesInfo(
      TableIdentifier tableIdentifier, int limit, int offset) {
    AmoroTable<?> amoroTable = loadTable(tableIdentifier);
    FormatTableDescriptor formatTableDescriptor = formatDescriptorMap.get(amoroTable.format());
    return formatTableDescriptor.getOptimizingProcessesInfo(amoroTable, limit, offset);
  }

  public List<OptimizingTaskInfo> getOptimizingProcessTaskInfos(
      TableIdentifier tableIdentifier, long processId) {
    AmoroTable<?> amoroTable = loadTable(tableIdentifier);
    FormatTableDescriptor formatTableDescriptor = formatDescriptorMap.get(amoroTable.format());
    return formatTableDescriptor.getOptimizingTaskInfos(amoroTable, processId);
  }

  private AmoroTable<?> loadTable(TableIdentifier identifier) {
    ServerCatalog catalog = tableService.getServerCatalog(identifier.getCatalog());
    return catalog.loadTable(identifier.getDatabase(), identifier.getTableName());
  }
}
