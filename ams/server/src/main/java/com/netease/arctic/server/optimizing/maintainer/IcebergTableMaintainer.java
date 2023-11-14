/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netease.arctic.server.optimizing.maintainer;

import static org.apache.iceberg.relocated.com.google.common.primitives.Longs.min;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.netease.arctic.IcebergFileEntry;
import com.netease.arctic.io.ArcticFileIO;
import com.netease.arctic.io.PathInfo;
import com.netease.arctic.io.SupportsFileSystemOperations;
import com.netease.arctic.op.SnapshotSummary;
import com.netease.arctic.server.ArcticServiceConstants;
import com.netease.arctic.server.table.DataExpirationConfig;
import com.netease.arctic.server.table.TableConfiguration;
import com.netease.arctic.server.table.TableRuntime;
import com.netease.arctic.server.utils.IcebergTableUtil;
import com.netease.arctic.table.ArcticTable;
import com.netease.arctic.table.BaseTable;
import com.netease.arctic.table.ChangeTable;
import com.netease.arctic.table.KeyedTable;
import com.netease.arctic.table.TableProperties;
import com.netease.arctic.table.UnkeyedTable;
import com.netease.arctic.utils.CompatiblePropertyUtil;
import com.netease.arctic.utils.ManifestEntryFields;
import com.netease.arctic.utils.TableFileUtil;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.Path;
import org.apache.iceberg.ContentFile;
import org.apache.iceberg.ContentScanTask;
import org.apache.iceberg.DataFile;
import org.apache.iceberg.DeleteFile;
import org.apache.iceberg.DeleteFiles;
import org.apache.iceberg.FileContent;
import org.apache.iceberg.FileScanTask;
import org.apache.iceberg.ManifestFile;
import org.apache.iceberg.ReachableFileUtil;
import org.apache.iceberg.RewriteFiles;
import org.apache.iceberg.Snapshot;
import org.apache.iceberg.StructLike;
import org.apache.iceberg.Table;
import org.apache.iceberg.TableScan;
import org.apache.iceberg.exceptions.ValidationException;
import org.apache.iceberg.expressions.Expression;
import org.apache.iceberg.expressions.Expressions;
import org.apache.iceberg.expressions.Literal;
import org.apache.iceberg.io.CloseableIterable;
import org.apache.iceberg.io.FileInfo;
import org.apache.iceberg.io.SupportsPrefixOperations;
import org.apache.iceberg.relocated.com.google.common.annotations.VisibleForTesting;
import org.apache.iceberg.relocated.com.google.common.collect.Iterables;
import org.apache.iceberg.types.Conversions;
import org.apache.iceberg.types.Type;
import org.apache.iceberg.types.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/** Table maintainer for iceberg tables. */
public class IcebergTableMaintainer implements TableMaintainer {

  private static final Logger LOG = LoggerFactory.getLogger(IcebergTableMaintainer.class);

  public static final String METADATA_FOLDER_NAME = "metadata";
  public static final String DATA_FOLDER_NAME = "data";
  public static final String FLINK_JOB_ID = "flink.job-id";

  public static final String FLINK_MAX_COMMITTED_CHECKPOINT_ID =
      "flink.max-committed-checkpoint-id";

  public static final String EXPIRE_TIMESTAMP_MS = "TIMESTAMP_MS";
  public static final String EXPIRE_TIMESTAMP_S = "TIMESTAMP_S";

  protected Table table;

  public IcebergTableMaintainer(Table table) {
    this.table = table;
  }

  @Override
  public void cleanOrphanFiles(TableRuntime tableRuntime) {
    TableConfiguration tableConfiguration = tableRuntime.getTableConfiguration();

    if (!tableConfiguration.isCleanOrphanEnabled()) {
      return;
    }

    long keepTime = tableConfiguration.getOrphanExistingMinutes() * 60 * 1000;

    cleanContentFiles(System.currentTimeMillis() - keepTime);

    // refresh
    table.refresh();

    // clear metadata files
    cleanMetadata(System.currentTimeMillis() - keepTime);

    if (!tableConfiguration.isDeleteDanglingDeleteFilesEnabled()) {
      return;
    }

    // refresh
    table.refresh();

    // clear dangling delete files
    cleanDanglingDeleteFiles();
  }

  public void expireSnapshots(TableRuntime tableRuntime) {
    TableConfiguration tableConfiguration = tableRuntime.getTableConfiguration();
    if (!tableConfiguration.isExpireSnapshotEnabled()) {
      return;
    }
    expireSnapshots(
        olderThanSnapshotNeedToExpire(tableRuntime), expireSnapshotNeedToExcludeFiles());
  }

  @Override
  public void expireData(TableRuntime tableRuntime) {
    try {
      DataExpirationConfig expirationConfig =
          tableRuntime.getTableConfiguration().getExpiringDataConfig();
      if (!expirationConfig.isEnabled()
          || expirationConfig.getRetentionTime() <= 0
          || !validateExpirationField(expirationConfig.getExpirationField())) {
        return;
      }

      purgeTableFrom(
          expirationConfig,
          Instant.now()
              .atZone(
                  getDefaultZoneId(
                      table.schema().findField(expirationConfig.getExpirationField())))
              .toInstant());
    } catch (Throwable t) {
      LOG.error("Unexpected purge error for table {} ", tableRuntime.getTableIdentifier(), t);
    }
  }

  public void expireSnapshots(long mustOlderThan) {
    expireSnapshots(
        olderThanSnapshotNeedToExpire(mustOlderThan), expireSnapshotNeedToExcludeFiles());
  }

  @VisibleForTesting
  public void expireSnapshots(long olderThan, Set<String> exclude) {
    LOG.debug("start expire snapshots older than {}, the exclude is {}", olderThan, exclude);
    final AtomicInteger toDeleteFiles = new AtomicInteger(0);
    final AtomicInteger deleteFiles = new AtomicInteger(0);
    Set<String> parentDirectory = new HashSet<>();
    table
        .expireSnapshots()
        .retainLast(1)
        .expireOlderThan(olderThan)
        .deleteWith(
            file -> {
              try {
                String filePath = TableFileUtil.getUriPath(file);
                if (!exclude.contains(filePath)
                    && !exclude.contains(new Path(filePath).getParent().toString())) {
                  arcticFileIO().deleteFile(file);
                }
                parentDirectory.add(new Path(file).getParent().toString());
                deleteFiles.incrementAndGet();
              } catch (Throwable t) {
                LOG.warn("failed to delete file " + file, t);
              } finally {
                toDeleteFiles.incrementAndGet();
              }
            })
        .cleanExpiredFiles(true)
        .commit();
    if (arcticFileIO().supportFileSystemOperations()) {
      parentDirectory.forEach(
          parent -> TableFileUtil.deleteEmptyDirectory(arcticFileIO(), parent, exclude));
    }
    LOG.info("to delete {} files, success delete {} files", toDeleteFiles.get(), deleteFiles.get());
  }

  protected void cleanContentFiles(long lastTime) {
    // For clean data files, should getRuntime valid files in the base store and the change store,
    // so acquire in advance
    // to prevent repeated acquisition
    Set<String> validFiles = orphanFileCleanNeedToExcludeFiles();
    LOG.info("{} start clean content files of change store", table.name());
    int deleteFilesCnt = clearInternalTableContentsFiles(lastTime, validFiles);
    LOG.info("{} total delete {} files from change store", table.name(), deleteFilesCnt);
  }

  protected void cleanMetadata(long lastTime) {
    LOG.info("{} start clean metadata files", table.name());
    int deleteFilesCnt = clearInternalTableMetadata(lastTime);
    LOG.info("{} total delete {} metadata files", table.name(), deleteFilesCnt);
  }

  protected void cleanDanglingDeleteFiles() {
    LOG.info("{} start delete dangling delete files", table.name());
    int danglingDeleteFilesCnt = clearInternalTableDanglingDeleteFiles();
    LOG.info("{} total delete {} dangling delete files", table.name(), danglingDeleteFilesCnt);
  }

  protected long olderThanSnapshotNeedToExpire(TableRuntime tableRuntime) {
    long optimizingSnapshotTime = fetchOptimizingSnapshotTime(table, tableRuntime);
    return olderThanSnapshotNeedToExpire(optimizingSnapshotTime);
  }

  protected long olderThanSnapshotNeedToExpire(long mustOlderThan) {
    long baseSnapshotsKeepTime =
        CompatiblePropertyUtil.propertyAsLong(
                table.properties(),
                TableProperties.BASE_SNAPSHOT_KEEP_MINUTES,
                TableProperties.BASE_SNAPSHOT_KEEP_MINUTES_DEFAULT)
            * 60
            * 1000;
    // Latest checkpoint of flink need retain. If Flink does not continuously commit new snapshots,
    // it can lead to issues with table partitions not expiring.
    long latestFlinkCommitTime = fetchLatestFlinkCommittedSnapshotTime(table);
    long olderThan = System.currentTimeMillis() - baseSnapshotsKeepTime;
    return min(latestFlinkCommitTime, mustOlderThan, olderThan);
  }

  protected Set<String> expireSnapshotNeedToExcludeFiles() {
    return Collections.emptySet();
  }

  protected Set<String> orphanFileCleanNeedToExcludeFiles() {
    return IcebergTableUtil.getAllContentFilePath(table);
  }

  protected ArcticFileIO arcticFileIO() {
    return (ArcticFileIO) table.io();
  }

  private int clearInternalTableContentsFiles(long lastTime, Set<String> exclude) {
    String dataLocation = table.location() + File.separator + DATA_FOLDER_NAME;

    try (ArcticFileIO io = arcticFileIO()) {
      // listPrefix will not return the directory and the orphan file clean should clean the empty
      // dir.
      if (io.supportFileSystemOperations()) {
        SupportsFileSystemOperations fio = io.asFileSystemIO();
        return deleteInvalidFilesInFs(fio, dataLocation, lastTime, exclude);
      } else if (io.supportPrefixOperations()) {
        SupportsPrefixOperations pio = io.asPrefixFileIO();
        return deleteInvalidFilesByPrefix(pio, dataLocation, lastTime, exclude);
      } else {
        LOG.warn(
            String.format(
                "Table %s doesn't support a fileIo with listDirectory or listPrefix, so skip clear files.",
                table.name()));
      }
    }

    return 0;
  }

  private int clearInternalTableMetadata(long lastTime) {
    Set<String> validFiles = getValidMetadataFiles(table);
    LOG.info("{} table getRuntime {} valid files", table.name(), validFiles.size());
    Pattern excludeFileNameRegex = getExcludeFileNameRegex(table);
    LOG.info(
        "{} table getRuntime exclude file name pattern {}", table.name(), excludeFileNameRegex);
    String metadataLocation = table.location() + File.separator + METADATA_FOLDER_NAME;
    LOG.info("start orphan files clean in {}", metadataLocation);

    try (ArcticFileIO io = arcticFileIO()) {
      if (io.supportPrefixOperations()) {
        SupportsPrefixOperations pio = io.asPrefixFileIO();
        return deleteInvalidMetadataFile(
            pio, metadataLocation, lastTime, validFiles, excludeFileNameRegex);
      } else {
        LOG.warn(
            String.format(
                "Table %s doesn't support a fileIo with listDirectory or listPrefix, so skip clear files.",
                table.name()));
      }
    }
    return 0;
  }

  private int clearInternalTableDanglingDeleteFiles() {
    Set<DeleteFile> danglingDeleteFiles = IcebergTableUtil.getDanglingDeleteFiles(table);
    if (danglingDeleteFiles.isEmpty()) {
      return 0;
    }
    RewriteFiles rewriteFiles = table.newRewrite();
    rewriteFiles.rewriteFiles(
        Collections.emptySet(),
        danglingDeleteFiles,
        Collections.emptySet(),
        Collections.emptySet());
    try {
      rewriteFiles.commit();
    } catch (ValidationException e) {
      LOG.warn("Iceberg RewriteFiles commit failed on clear danglingDeleteFiles, but ignore", e);
      return 0;
    }
    return danglingDeleteFiles.size();
  }

  /**
   * When committing a snapshot, Flink will write a checkpoint id into the snapshot summary. The
   * latest snapshot with checkpoint id should not be expired or the flink job can't recover from
   * state.
   *
   * @param table -
   * @return commit time of snapshot with the latest flink checkpointId in summary
   */
  public static long fetchLatestFlinkCommittedSnapshotTime(Table table) {
    long latestCommitTime = Long.MAX_VALUE;
    for (Snapshot snapshot : table.snapshots()) {
      if (snapshot.summary().containsKey(FLINK_MAX_COMMITTED_CHECKPOINT_ID)) {
        latestCommitTime = snapshot.timestampMillis();
      }
    }
    return latestCommitTime;
  }

  /**
   * When optimizing tasks are not committed, the snapshot with which it planned should not be
   * expired, since it will use the snapshot to check conflict when committing.
   *
   * @param table - table
   * @return commit time of snapshot for optimizing
   */
  public static long fetchOptimizingSnapshotTime(Table table, TableRuntime tableRuntime) {
    if (tableRuntime.getOptimizingStatus().isProcessing()) {
      long fromSnapshotId = tableRuntime.getOptimizingProcess().getTargetSnapshotId();

      for (Snapshot snapshot : table.snapshots()) {
        if (snapshot.snapshotId() == fromSnapshotId) {
          return snapshot.timestampMillis();
        }
      }
    }
    return Long.MAX_VALUE;
  }

  private static int deleteInvalidFilesInFs(
      SupportsFileSystemOperations fio, String location, long lastTime, Set<String> excludes) {
    if (!fio.exists(location)) {
      return 0;
    }

    int deleteCount = 0;
    for (PathInfo p : fio.listDirectory(location)) {
      String uriPath = TableFileUtil.getUriPath(p.location());
      if (p.isDirectory()) {
        int deleted = deleteInvalidFilesInFs(fio, p.location(), lastTime, excludes);
        deleteCount += deleted;
        if (!p.location().endsWith(METADATA_FOLDER_NAME)
            && !p.location().endsWith(DATA_FOLDER_NAME)
            && p.createdAtMillis() < lastTime
            && fio.isEmptyDirectory(p.location())) {
          TableFileUtil.deleteEmptyDirectory(fio, p.location(), excludes);
        }
      } else {
        String parentLocation = TableFileUtil.getParent(p.location());
        String parentUriPath = TableFileUtil.getUriPath(parentLocation);
        if (!excludes.contains(uriPath)
            && !excludes.contains(parentUriPath)
            && p.createdAtMillis() < lastTime) {
          fio.deleteFile(p.location());
          deleteCount += 1;
        }
      }
    }
    return deleteCount;
  }

  private static int deleteInvalidFilesByPrefix(
      SupportsPrefixOperations pio, String prefix, long lastTime, Set<String> excludes) {
    int deleteCount = 0;
    for (FileInfo fileInfo : pio.listPrefix(prefix)) {
      String uriPath = TableFileUtil.getUriPath(fileInfo.location());
      if (!excludes.contains(uriPath) && fileInfo.createdAtMillis() < lastTime) {
        pio.deleteFile(fileInfo.location());
        deleteCount += 1;
      }
    }
    return deleteCount;
  }

  private static Set<String> getValidMetadataFiles(Table internalTable) {
    String tableName = internalTable.name();
    Set<String> validFiles = new HashSet<>();
    Iterable<Snapshot> snapshots = internalTable.snapshots();
    int size = Iterables.size(snapshots);
    LOG.info("{} getRuntime {} snapshots to scan", tableName, size);
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

      LOG.info(
          "{} scan snapshot {}: {} and getRuntime {} files, complete {}/{}",
          tableName,
          snapshot.snapshotId(),
          formatTime(snapshot.timestampMillis()),
          validFiles.size() - before,
          cnt,
          size);
    }
    Stream.of(
            ReachableFileUtil.metadataFileLocations(internalTable, false).stream(),
            ReachableFileUtil.statisticsFilesLocations(internalTable).stream(),
            Stream.of(ReachableFileUtil.versionHintLocation(internalTable)))
        .reduce(Stream::concat)
        .orElse(Stream.empty())
        .map(TableFileUtil::getUriPath)
        .forEach(validFiles::add);

    return validFiles;
  }

  private static Pattern getExcludeFileNameRegex(Table table) {
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

  private static int deleteInvalidMetadataFile(
      SupportsPrefixOperations pio,
      String location,
      long lastTime,
      Set<String> exclude,
      Pattern excludeRegex) {
    int count = 0;
    for (FileInfo fileInfo : pio.listPrefix(location)) {
      String uriPath = TableFileUtil.getUriPath(fileInfo.location());
      if (!exclude.contains(uriPath)
          && fileInfo.createdAtMillis() < lastTime
          && (excludeRegex == null
              || !excludeRegex.matcher(TableFileUtil.getFileName(fileInfo.location())).matches())) {
        pio.deleteFile(fileInfo.location());
        count += 1;
      }
    }
    return count;
  }

  private static String formatTime(long timestamp) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
        .toString();
  }

  private boolean validateExpirationField(String expirationField) {
    Types.NestedField field = table.schema().findField(expirationField);

    if (StringUtils.isBlank(expirationField) || null == field) {
      LOG.warn(
          String.format(
              "Field(%s) used to determine data expiration is illegal for table(%s)",
              expirationField, table.name()));
      return false;
    }
    Type.TypeID typeID = field.type().typeId();
    if (!DataExpirationConfig.FIELD_TYPES.contains(typeID)) {
      LOG.warn(
          String.format(
              "Table(%s) field(%s) type(%s) is not supported for data expiration, please use the "
                  + "following types: %s",
              table.name(),
              expirationField,
              typeID.name(),
              StringUtils.join(DataExpirationConfig.FIELD_TYPES, ", ")));
      return false;
    }

    return true;
  }

  /**
   * Purge data older than the specified UTC timestamp
   *
   * @param expirationConfig expiration configs
   * @param instant timestamp/timestampz/long field type uses UTC, others will use the local time
   *     zone
   */
  protected void purgeTableFrom(DataExpirationConfig expirationConfig, Instant instant) {
    long expireTimestamp = instant.minusMillis(expirationConfig.getRetentionTime()).toEpochMilli();
    LOG.info(
        "Expiring data older than {} in table {} ",
        Instant.ofEpochMilli(expireTimestamp)
            .atZone(
                getDefaultZoneId(table.schema().findField(expirationConfig.getExpirationField())))
            .toLocalDateTime(),
        table.name());

    purgeTableData(expirationConfig, expireTimestamp);
  }

  protected static ZoneId getDefaultZoneId(Types.NestedField expireField) {
    Type type = expireField.type();
    if (type.typeId() == Type.TypeID.STRING) {
      return ZoneId.systemDefault();
    }
    return ZoneOffset.UTC;
  }

  CloseableIterable<IcebergFileEntry> fileScan(Table table, Expression partitionFilter, Snapshot snapshot) {
    TableScan tableScan = table.newScan().filter(partitionFilter).includeColumnStats();

    CloseableIterable<FileScanTask> tasks;
    long snapshotId = IcebergTableUtil.getSnapshotId(table, false);
    if (snapshotId == ArcticServiceConstants.INVALID_SNAPSHOT_ID) {
      tasks = tableScan.planFiles();
    } else {
      tasks = tableScan.useSnapshot(snapshotId).planFiles();
    }
    CloseableIterable<DataFile> dataFiles =
        CloseableIterable.transform(tasks, ContentScanTask::file);
    Set<DeleteFile> deleteFiles =
        StreamSupport.stream(tasks.spliterator(), false)
            .flatMap(e -> e.deletes().stream())
            .collect(Collectors.toSet());

    return CloseableIterable.transform(
        CloseableIterable.withNoopClose(com.google.common.collect.Iterables.concat(dataFiles, deleteFiles)),
        contentFile ->
            new IcebergFileEntry(
                snapshotId,
                contentFile.dataSequenceNumber(),
                ManifestEntryFields.Status.EXISTING,
                contentFile));
  }

  public MaintainStrategy createMaintainStrategy() {
    return new IcebergMaintainStrategy(this);
  }

  protected void purgeTableData(DataExpirationConfig expirationConfig, long expireTimestamp) {
    Expression dataFilter = getDataExpression(expirationConfig, expireTimestamp);
    Map<StructLike, DataFileFreshness> partitionFreshness = Maps.newHashMap();

    MaintainStrategy maintainStrategy = createMaintainStrategy();
    List<ExpireFiles> expiredFiles =
        maintainStrategy.entryFileScan(expirationConfig, dataFilter, expireTimestamp, partitionFreshness);
    maintainStrategy.doExpireFiles(expiredFiles, expireTimestamp);
  }

  /**
   * Create a filter expression for expired files for the `FILE` level. For the `PARTITION` level,
   * we need to collect the oldest files to determine if the partition is obsolete, so we will not
   * filter for expired files at the scanning stage
   *
   * @param expirationConfig expiration configuration
   * @param expireTimestamp expired timestamp
   * @return filter expression
   */
  protected Expression getDataExpression(DataExpirationConfig expirationConfig, long expireTimestamp) {
    if (expirationConfig.getExpirationLevel().equals(DataExpirationConfig.ExpireLevel.PARTITION)) {
      return Expressions.alwaysTrue();
    }

    Types.NestedField field = table.schema().findField(expirationConfig.getExpirationField());
    Type.TypeID typeID = field.type().typeId();
    switch (typeID) {
      case TIMESTAMP:
        return Expressions.lessThanOrEqual(field.name(), expireTimestamp * 1000);
      case LONG:
        if (expirationConfig.getNumberDateFormat().equals(EXPIRE_TIMESTAMP_MS)) {
          return Expressions.lessThanOrEqual(field.name(), expireTimestamp);
        } else if (expirationConfig.getNumberDateFormat().equals(EXPIRE_TIMESTAMP_S)) {
          return Expressions.lessThanOrEqual(field.name(), expireTimestamp / 1000);
        } else {
          return Expressions.alwaysTrue();
        }
      case STRING:
        String expireDateTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(expireTimestamp), getDefaultZoneId(field))
                .format(
                    DateTimeFormatter.ofPattern(
                        expirationConfig.getDateTimePattern(), Locale.getDefault()));
        return Expressions.lessThanOrEqual(field.name(), expireDateTime);
      default:
        return Expressions.alwaysTrue();
    }
  }

  void expireFiles(long snapshotId, ExpireFiles expiredFiles, long expireTimestamp) {
    List<DataFile> dataFiles = expiredFiles.dataFiles;
    List<DeleteFile> deleteFiles = expiredFiles.deleteFiles;
    if (dataFiles.isEmpty() && deleteFiles.isEmpty()) {
      return;
    }
    // expire data files
    DeleteFiles delete = table.newDelete();
    dataFiles.forEach(delete::deleteFile);
    delete.set(SnapshotSummary.SNAPSHOT_PRODUCER, "DATA_EXPIRATION");
    delete.commit();
    // expire delete files
    if (!deleteFiles.isEmpty()) {
      RewriteFiles rewriteFiles = table.newRewrite().validateFromSnapshot(snapshotId);
      deleteFiles.forEach(rewriteFiles::deleteFile);
      rewriteFiles.set(SnapshotSummary.SNAPSHOT_PRODUCER, "DATA_EXPIRATION");
      rewriteFiles.commit();
    }

    // TODO: persistent table expiration record. Contains some meta information such as table_id,
    // snapshotId,
    //  file_infos(file_content, path, recordCount, fileSizeInBytes, equalityFieldIds,
    // partitionPath,
    //  sequenceNumber) and expireTimestamp...

    LOG.info(
        "Expired {} files older than {}, {} data files[{}] and {} delete files[{}]",
        table.name(),
        expireTimestamp,
        dataFiles.size(),
        dataFiles.stream().map(ContentFile::path).collect(Collectors.joining(",")),
        deleteFiles.size(),
        deleteFiles.stream().map(ContentFile::path).collect(Collectors.joining(",")));
  }

  public static class ExpireFiles {
    List<DataFile> dataFiles;
    List<DeleteFile> deleteFiles;

    ExpireFiles() {
      this.dataFiles = new LinkedList<>();
      this.deleteFiles = new LinkedList<>();
    }

    void addFile(IcebergFileEntry entry) {
      ContentFile<?> file = entry.getFile();
      switch (file.content()) {
        case DATA:
          dataFiles.add((DataFile) file.copyWithoutStats());
          break;
        case EQUALITY_DELETES:
        case POSITION_DELETES:
          deleteFiles.add((DeleteFile) file.copyWithoutStats());
          break;
        default:
          throw new IllegalArgumentException(file.content().name() + "cannot be expired");
      }
    }
  }

  public static class DataFileFreshness {
    long latestExpiredSeq;
    long latestUpdateMillis;
    long expiredDataFileCount;
    long totalDataFileCount;

    DataFileFreshness(long sequenceNumber, long latestUpdateMillis) {
      this.latestExpiredSeq = sequenceNumber;
      this.latestUpdateMillis = latestUpdateMillis;
    }

    DataFileFreshness updateLatestMillis(long ts) {
      this.latestUpdateMillis = ts;
      return this;
    }

    DataFileFreshness updateExpiredSeq(Long seq) {
      this.latestExpiredSeq = seq;
      return this;
    }

    DataFileFreshness incTotalCount() {
      totalDataFileCount++;
      return this;
    }

    DataFileFreshness incExpiredCount() {
      expiredDataFileCount++;
      return this;
    }
  }

  boolean mayExpired(
      IcebergFileEntry fileEntry,
      DataExpirationConfig expirationConfig,
      Map<StructLike, DataFileFreshness> partitionFreshness,
      Long expireTimestamp) {
    ContentFile<?> contentFile = fileEntry.getFile();
    StructLike partition = contentFile.partition();

    boolean expired = true;
    Types.NestedField field = table.schema().findField(expirationConfig.getExpirationField());
    if (contentFile.content().equals(FileContent.DATA)) {
      Literal<Long> literal =
          getExpireTimestampLiteral(
              contentFile,
              field,
              DateTimeFormatter.ofPattern(
                  expirationConfig.getDateTimePattern(), Locale.getDefault()),
              expirationConfig.getNumberDateFormat());
      if (partitionFreshness.containsKey(partition)) {
        DataFileFreshness freshness = partitionFreshness.get(partition).incTotalCount();
        if (freshness.latestUpdateMillis <= literal.value()) {
          partitionFreshness.put(partition, freshness.updateLatestMillis(literal.value()));
        }
      } else {
        partitionFreshness.putIfAbsent(
            partition,
            new DataFileFreshness(fileEntry.getSequenceNumber(), literal.value()).incTotalCount());
      }
      expired = literal.comparator().compare(expireTimestamp, literal.value()) >= 0;
      if (expired) {
        partitionFreshness.computeIfPresent(
            partition,
            (k, v) -> v.updateExpiredSeq(fileEntry.getSequenceNumber()).incExpiredCount());
      }
    }

    return expired;
  }

  boolean willNotRetain(
      IcebergFileEntry fileEntry,
      DataExpirationConfig expirationConfig,
      Map<StructLike, DataFileFreshness> partitionFreshness) {
    ContentFile<?> contentFile = fileEntry.getFile();

    switch (expirationConfig.getExpirationLevel()) {
      case PARTITION:
        // if only partial expired files in a partition, all the files in that partition should be
        // preserved
        return partitionFreshness.containsKey(contentFile.partition())
            && partitionFreshness.get(contentFile.partition()).expiredDataFileCount
            == partitionFreshness.get(contentFile.partition()).totalDataFileCount;
      case FILE:
        if (!contentFile.content().equals(FileContent.DATA)) {
          long seqUpperBound =
              partitionFreshness.getOrDefault(
                  contentFile.partition(),
                  new DataFileFreshness(Long.MIN_VALUE, Long.MAX_VALUE))
                  .latestExpiredSeq;
          // only expire delete files with sequence-number less or equal to expired data file
          // there may be some dangling delete files, they will be cleaned by
          // OrphanFileCleaningExecutor
          return fileEntry.getSequenceNumber() <= seqUpperBound;
        } else {
          return true;
        }
      default:
        return false;
    }
  }

  private static Literal<Long> getExpireTimestampLiteral(
      ContentFile<?> contentFile,
      Types.NestedField field,
      DateTimeFormatter formatter,
      String numberDateFormatter) {
    Type type = field.type();
    Object upperBound =
        Conversions.fromByteBuffer(type, contentFile.upperBounds().get(field.fieldId()));
    Literal<Long> literal = Literal.of(Long.MAX_VALUE);
    if (null == upperBound) {
      return literal;
    } else if (upperBound instanceof Long) {
      switch (type.typeId()) {
        case TIMESTAMP:
          // nanosecond -> millisecond
          literal = Literal.of((Long) upperBound / 1000);
          break;
        default:
          if (numberDateFormatter.equals(EXPIRE_TIMESTAMP_MS)) {
            literal = Literal.of((Long) upperBound);
          } else if (numberDateFormatter.equals(EXPIRE_TIMESTAMP_S)) {
            // second -> millisecond
            literal = Literal.of((Long) upperBound * 1000);
          }
      }
    } else if (type.typeId().equals(Type.TypeID.STRING)) {
      literal =
          Literal.of(
              LocalDate.parse(upperBound.toString(), formatter)
                  .atStartOfDay()
                  .atZone(getDefaultZoneId(field))
                  .toInstant()
                  .toEpochMilli());
    }
    return literal;
  }

  protected static class FileEntry extends IcebergFileEntry {

    private final boolean isChange;

    FileEntry(IcebergFileEntry fileEntry, boolean isChange) {
      super(
          fileEntry.getSnapshotId(),
          fileEntry.getSequenceNumber(),
          fileEntry.getStatus(),
          fileEntry.getFile());
      this.isChange = isChange;
    }

    public boolean isChange() {
      return isChange;
    }
  }

  public Table getTable() {
    return table;
  }
}
