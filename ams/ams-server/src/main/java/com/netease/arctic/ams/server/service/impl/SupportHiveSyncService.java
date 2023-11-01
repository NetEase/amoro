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

package com.netease.arctic.ams.server.service.impl;

import com.netease.arctic.ams.server.model.TableMetadata;
import com.netease.arctic.ams.server.service.ISupportHiveSyncService;
import com.netease.arctic.ams.server.service.ServiceContainer;
import com.netease.arctic.ams.server.utils.ScheduledTasks;
import com.netease.arctic.ams.server.utils.ThreadPool;
import com.netease.arctic.catalog.ArcticCatalog;
import com.netease.arctic.catalog.CatalogLoader;
import com.netease.arctic.hive.utils.HiveMetaSynchronizer;
import com.netease.arctic.hive.utils.TableTypeUtil;
import com.netease.arctic.table.ArcticTable;
import com.netease.arctic.table.TableIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SupportHiveSyncService implements ISupportHiveSyncService {
  private static final Logger LOG = LoggerFactory.getLogger(SupportHiveSyncService.class);
  private static final long SYNC_INTERVAL = 3600_000; // 1 hour

  private ScheduledTasks<TableIdentifier, SupportHiveSyncService.SupportHiveSyncTask> syncTasks;

  @Override
  public void checkHiveSyncTasks() {
    try {
      LOG.info("Schedule Support Hive Sync");
      if (syncTasks == null) {
        syncTasks = new ScheduledTasks<>(ThreadPool.Type.HIVE_SYNC);
      }
      List<TableMetadata> tables = ServiceContainer.getMetaService().listTables();
      Set<TableIdentifier> ids =
          tables.stream().map(TableMetadata::getTableIdentifier).collect(Collectors.toSet());
      syncTasks.checkRunningTask(ids,
          () -> 0L,
          () -> SYNC_INTERVAL,
          SupportHiveSyncService.SupportHiveSyncTask::new,
          false);
      LOG.info("Schedule Support Hive Sync finished with {} valid ids", ids.size());
    } catch (Throwable t) {
      LOG.error("Schedule Support Hive Sync failed", t);
    }
  }

  @Override
  public void close() throws IOException {
    syncTasks = null;
  }

  public static class SupportHiveSyncTask implements ScheduledTasks.Task {
    private final TableIdentifier tableIdentifier;

    SupportHiveSyncTask(TableIdentifier tableIdentifier) {
      this.tableIdentifier = tableIdentifier;
    }

    @Override
    public void run() {
      long startTime = System.currentTimeMillis();
      try {
        LOG.info("{} start hive sync", tableIdentifier);
        ArcticCatalog catalog =
            CatalogLoader.load(ServiceContainer.getTableMetastoreHandler(), tableIdentifier.getCatalog());
        ArcticTable arcticTable = catalog.loadTable(tableIdentifier);
        if (!TableTypeUtil.isHive(arcticTable)) {
          LOG.debug("{} is not a support hive table", tableIdentifier);
          return;
        }

        syncIcebergToHive(arcticTable);
      } catch (Exception e) {
        LOG.error("{} hive sync failed", tableIdentifier, e);
      } finally {
        LOG.info("{} hive sync finished, cost {}ms", tableIdentifier,
            System.currentTimeMillis() - startTime);
      }
    }

    public static void syncIcebergToHive(ArcticTable arcticTable) throws Exception {
      HiveMetaSynchronizer.syncArcticDataToHive(arcticTable);
    }
  }

}