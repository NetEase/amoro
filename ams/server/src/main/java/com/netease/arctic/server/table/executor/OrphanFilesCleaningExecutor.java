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

package com.netease.arctic.server.table.executor;

import com.google.common.base.Strings;
import com.netease.arctic.server.table.TableRuntime;
import com.netease.arctic.server.table.TableRuntimeManager;
import com.netease.arctic.server.utils.HiveLocationUtil;
import com.netease.arctic.server.utils.IcebergTableUtil;
import com.netease.arctic.io.ArcticFileIO;
import com.netease.arctic.table.ArcticTable;
import com.netease.arctic.table.KeyedTable;
import com.netease.arctic.table.TableIdentifier;
import com.netease.arctic.table.TableProperties;
import com.netease.arctic.table.UnkeyedTable;
import com.netease.arctic.utils.CompatiblePropertyUtil;
import com.netease.arctic.utils.TableFileUtil;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.iceberg.ManifestFile;
import org.apache.iceberg.ReachableFileUtil;
import org.apache.iceberg.Snapshot;
import org.apache.iceberg.relocated.com.google.common.collect.Iterables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class OrphanFilesCleaningExecutor extends BaseTableExecutor {
  private static final Logger LOG = LoggerFactory.getLogger(OrphanFilesCleaningExecutor.class);
  // same as org.apache.iceberg.flink.sink.IcebergFilesCommitter#FLINK_JOB_ID
  public static final String FLINK_JOB_ID = "flink.job-id";
  public static final String METADATA_FOLDER_NAME = "metadata";
  public static final String DATA_FOLDER_NAME = "data";
  // 1 days
  private static final long INTERVAL = 24 * 60 * 60 * 1000L;

  public OrphanFilesCleaningExecutor(TableRuntimeManager tableRuntimes, int poolSize) {
    super(tableRuntimes, poolSize);
  }

  @Override
  protected long getNextExecutingTime(TableRuntime tableRuntime) {
    return INTERVAL;
  }

  @Override
  protected boolean enabled(TableRuntime tableRuntime) {
    return tableRuntime.getTableConfiguration().isCleanOrphanEnabled();
  }

  @Override
  protected void execute(TableRuntime tableRuntime) {
    try {
      LOG.info("{} clean orphan files", tableRuntime.getTableIdentifier());
      ArcticTable arcticTable = tableRuntime.loadTable();

      boolean needOrphanClean = CompatiblePropertyUtil.propertyAsBoolean(arcticTable.properties(),
          TableProperties.ENABLE_ORPHAN_CLEAN,
          TableProperties.ENABLE_ORPHAN_CLEAN_DEFAULT);

      if (!needOrphanClean) {
        return;
      }

      long keepTime = CompatiblePropertyUtil.propertyAsLong(
          arcticTable.properties(),
          TableProperties.MIN_ORPHAN_FILE_EXISTING_TIME,
          TableProperties.MIN_ORPHAN_FILE_EXISTING_TIME_DEFAULT) * 60 * 1000;

      LOG.info("{} clean orphan files, keepTime={}", tableRuntime.getTableIdentifier(), keepTime);
      // clear data files
      cleanContentFiles(arcticTable, System.currentTimeMillis() - keepTime);

      arcticTable = tableRuntime.loadTable();
      // clear metadata files
      cleanMetadata(arcticTable, System.currentTimeMillis() - keepTime);
    } catch (Throwable t) {
      LOG.error("{} orphan file clean unexpected error", tableRuntime.getTableIdentifier(), t);
    }
  }

  public static void cleanContentFiles(ArcticTable arcticTable, long lastTime) {
    // For clean data files, should get valid files in the base store and the change store, so acquire in advance
    // to prevent repeated acquisition
    Set<String> validFiles = getValidContentFiles(arcticTable);
    if (arcticTable.isKeyedTable()) {
      KeyedTable keyedArcticTable = arcticTable.asKeyedTable();
      LOG.info("{} start clean content files of base store", arcticTable.id());
      int deleteFilesCnt = clearInternalTableContentsFiles(keyedArcticTable.baseTable(), lastTime, validFiles);
      LOG.info("{} total delete {} files from base store", arcticTable.id(), deleteFilesCnt);

      LOG.info("{} start clean content files of change store", arcticTable.id());
      deleteFilesCnt = clearInternalTableContentsFiles(keyedArcticTable.changeTable(), lastTime, validFiles);
      LOG.info("{} total delete {} files from change store", arcticTable.id(), deleteFilesCnt);
    } else {
      LOG.info("{} start clean content files", arcticTable.id());
      int deleteFilesCnt = clearInternalTableContentsFiles(arcticTable.asUnkeyedTable(), lastTime, validFiles);
      LOG.info("{} total delete {} files", arcticTable.id(), deleteFilesCnt);
    }
  }

  public static void cleanMetadata(ArcticTable arcticTable, long lastTime) {
    if (arcticTable.isKeyedTable()) {
      KeyedTable keyedArcticTable = arcticTable.asKeyedTable();
      LOG.info("{} start clean metadata files of base store", arcticTable.id());
      int deleteFilesCnt = clearInternalTableMetadata(keyedArcticTable.baseTable(), lastTime);
      LOG.info("{} total delete {} metadata files from base store", arcticTable.id(), deleteFilesCnt);

      LOG.info("{} start clean metadata files of change store", arcticTable.id());
      deleteFilesCnt = clearInternalTableMetadata(keyedArcticTable.changeTable(), lastTime);
      LOG.info("{} total delete {} metadata files from change store", arcticTable.id(), deleteFilesCnt);
    } else {
      LOG.info("{} start clean metadata files", arcticTable.id());
      int deleteFilesCnt = clearInternalTableMetadata(arcticTable.asUnkeyedTable(), lastTime);
      LOG.info("{} total delete {} metadata files", arcticTable.id(), deleteFilesCnt);
    }
  }

  private static Set<String> getValidContentFiles(ArcticTable arcticTable) {
    Set<String> validFiles = new HashSet<>();
    if (arcticTable.isKeyedTable()) {
      Set<String> baseValidFiles = IcebergTableUtil.getAllContentFilePath(arcticTable.asKeyedTable().baseTable());
      LOG.info("{} get {} valid files in the base store", arcticTable.id(), baseValidFiles.size());
      Set<String> changeValidFiles = IcebergTableUtil.getAllContentFilePath(arcticTable.asKeyedTable().changeTable());
      LOG.info("{} get {} valid files in the change store", arcticTable.id(), baseValidFiles.size());
      validFiles.addAll(baseValidFiles);
      validFiles.addAll(changeValidFiles);
    } else {
      Set<String> baseValidFiles = IcebergTableUtil.getAllContentFilePath(arcticTable.asUnkeyedTable());
      validFiles.addAll(baseValidFiles);
    }

    LOG.info("{} get {} valid files", arcticTable.id(), validFiles.size());

    // add hive location to exclude
    Set<String> hiveValidLocations = HiveLocationUtil.getHiveLocation(arcticTable);
    LOG.info("{} get {} valid locations in the Hive", arcticTable.id(), hiveValidLocations.size());
    validFiles.addAll(hiveValidLocations);

    return validFiles;
  }

  private static int clearInternalTableContentsFiles(UnkeyedTable internalTable, long lastTime,
                                                     Set<String> exclude) {
    int deleteFilesCnt = 0;
    String dataLocation = internalTable.location() + File.separator + DATA_FOLDER_NAME;
    if (internalTable.io().exists(dataLocation)) {
      for (FileStatus fileStatus : internalTable.io().list(dataLocation)) {
        deleteFilesCnt += deleteInvalidContentFiles(internalTable.io(),
            fileStatus,
            lastTime,
            exclude);
      }
    }
    return deleteFilesCnt;
  }

  private static int deleteInvalidContentFiles(ArcticFileIO io,
                                               FileStatus fileStatus,
                                               Long lastTime,
                                               Set<String> exclude) {
    String location = TableFileUtil.getUriPath(fileStatus.getPath().toString());
    if (io.isDirectory(location)) {
      if (!io.isEmptyDirectory(location)) {
        LOG.info("start orphan files clean in {}", location);
        int deleteFileCnt = 0;
        for (FileStatus file : io.list(location)) {
          deleteFileCnt += deleteInvalidContentFiles(io, file, lastTime, exclude);
        }
        LOG.info("delete {} files in {}", deleteFileCnt, location);

        if (location.endsWith(METADATA_FOLDER_NAME) || location.endsWith(DATA_FOLDER_NAME)) {
          return 0;
        }
        TableFileUtil.deleteEmptyDirectory(io, location, exclude);
        return deleteFileCnt;
      } else if (io.isEmptyDirectory(location) &&
          fileStatus.getModificationTime() < lastTime) {
        if (location.endsWith(METADATA_FOLDER_NAME) || location.endsWith(DATA_FOLDER_NAME)) {
          return 0;
        }

        TableFileUtil.deleteEmptyDirectory(io, location, exclude);
        LOG.info("delete empty dir : {}[{}]", location, formatTime(fileStatus.getModificationTime()));
        return 0;
      } else {
        return 0;
      }
    } else {
      if (!exclude.contains(location) &&
          !exclude.contains(TableFileUtil.getUriPath(new Path(location).getParent().toString())) &&
          fileStatus.getModificationTime() < lastTime) {
        io.deleteFile(location);
        return 1;
      }

      return 0;
    }
  }

  private static int clearInternalTableMetadata(UnkeyedTable internalTable, long lastTime) {
    Set<String> validFiles = getValidMetadataFiles(internalTable);
    LOG.info("{} table get {} valid files", internalTable.id(), validFiles.size());
    Pattern excludeFileNameRegex = getExcludeFileNameRegex(internalTable);
    LOG.info("{} table get exclude file name pattern {}", internalTable.id(), excludeFileNameRegex);
    int deleteFilesCnt = 0;
    String metadataLocation = internalTable.location() + File.separator + METADATA_FOLDER_NAME;
    LOG.info("start orphan files clean in {}", metadataLocation);
    for (FileStatus fileStatus : internalTable.io().list(metadataLocation)) {
      deleteFilesCnt += deleteInvalidMetadata(internalTable.io(),
          fileStatus,
          lastTime,
          validFiles,
          excludeFileNameRegex);
    }
    return deleteFilesCnt;
  }

  private static Set<String> getValidMetadataFiles(UnkeyedTable internalTable) {
    TableIdentifier tableIdentifier = internalTable.id();
    Set<String> validFiles = new HashSet<>();
    Iterable<Snapshot> snapshots = internalTable.snapshots();
    int size = Iterables.size(snapshots);
    LOG.info("{} get {} snapshots to scan", tableIdentifier, size);
    int cnt = 0;
    for (Snapshot snapshot : snapshots) {
      cnt++;
      int before = validFiles.size();
      String manifestListLocation = snapshot.manifestListLocation();

      validFiles.add(TableFileUtil.getUriPath(manifestListLocation));

      // valid data files
      List<ManifestFile> manifestFiles = snapshot.allManifests(internalTable.io());
      for (ManifestFile manifestFile : manifestFiles) {
        validFiles.add(TableFileUtil.getUriPath(manifestFile.path()));
      }

      LOG.info("{} scan snapshot {}: {} and get {} files, complete {}/{}", tableIdentifier, snapshot.snapshotId(),
          formatTime(snapshot.timestampMillis()), validFiles.size() - before, cnt, size);
    }
    Stream.concat(
            ReachableFileUtil.metadataFileLocations(internalTable, false).stream(),
            Stream.of(ReachableFileUtil.versionHintLocation(internalTable)))
        .map(TableFileUtil::getUriPath)
        .forEach(validFiles::add);

    return validFiles;
  }

  private static Pattern getExcludeFileNameRegex(UnkeyedTable table) {
    String latestFlinkJobId = null;
    for (Snapshot snapshot : table.snapshots()) {
      String flinkJobId = snapshot.summary().get(FLINK_JOB_ID);
      if (!Strings.isNullOrEmpty(flinkJobId)) {
        latestFlinkJobId = flinkJobId;
      }
    }
    if (latestFlinkJobId != null) {
      // file name starting with flink.job-id should not be deleted
      return Pattern.compile(latestFlinkJobId + ".*");
    }
    return null;
  }

  private static int deleteInvalidMetadata(ArcticFileIO io,
                                           FileStatus fileStatus,
                                           Long lastTime,
                                           Set<String> exclude,
                                           Pattern excludeFileNameRegex) {
    String location = TableFileUtil.getUriPath(fileStatus.getPath().toString());
    if (io.isDirectory(location)) {
      LOG.warn("unexpected dir in metadata/, {}", location);
      return 0;
    } else {
      if (!exclude.contains(location) && fileStatus.getModificationTime() < lastTime &&
          (excludeFileNameRegex == null ||
              !excludeFileNameRegex.matcher(TableFileUtil.getFileName(location)).matches())) {
        io.deleteFile(location);
        return 1;
      } else {
        return 0;
      }
    }
  }

  private static String formatTime(long timestamp) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).toString();
  }
}

