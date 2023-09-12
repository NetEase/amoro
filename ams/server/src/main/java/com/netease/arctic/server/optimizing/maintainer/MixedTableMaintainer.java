package com.netease.arctic.server.optimizing.maintainer;

import com.netease.arctic.IcebergFileEntry;
import com.netease.arctic.data.FileNameRules;
import com.netease.arctic.hive.utils.TableTypeUtil;
import com.netease.arctic.scan.TableEntriesScan;
import com.netease.arctic.server.table.TableRuntime;
import com.netease.arctic.server.utils.HiveLocationUtil;
import com.netease.arctic.server.utils.IcebergTableUtil;
import com.netease.arctic.table.ArcticTable;
import com.netease.arctic.table.KeyedTable;
import com.netease.arctic.table.TableProperties;
import com.netease.arctic.table.UnkeyedTable;
import com.netease.arctic.utils.CompatiblePropertyUtil;
import com.netease.arctic.utils.TablePropertyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.iceberg.DataFile;
import org.apache.iceberg.DeleteFiles;
import org.apache.iceberg.FileContent;
import org.apache.iceberg.Snapshot;
import org.apache.iceberg.io.CloseableIterable;
import org.apache.iceberg.relocated.com.google.common.primitives.Longs;
import org.apache.iceberg.util.StructLikeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MixedTableMaintainer implements TableMaintainer {

  private static final Logger LOG = LoggerFactory.getLogger(MixedTableMaintainer.class);

  private ArcticTable arcticTable;

  private ChangeTableMaintainer changeMaintainer;

  private BaseTableMaintainer baseMaintainer;

  private Set<String> changeFiles;

  private Set<String> baseFiles;

  private Set<String> hiveFiles;

  public MixedTableMaintainer(ArcticTable arcticTable) {
    this.arcticTable = arcticTable;
    if (arcticTable.isKeyedTable()) {
      changeMaintainer = new ChangeTableMaintainer(arcticTable.asKeyedTable().changeTable());
      baseMaintainer = new BaseTableMaintainer(arcticTable.asKeyedTable().baseTable());
      changeFiles = IcebergTableUtil.getAllContentFilePath(arcticTable.asKeyedTable().changeTable());
      baseFiles = IcebergTableUtil.getAllContentFilePath(arcticTable.asKeyedTable().baseTable());
    } else {
      changeFiles = new HashSet<>();
      baseMaintainer = new BaseTableMaintainer(arcticTable.asUnkeyedTable());
    }

    if (TableTypeUtil.isHive(arcticTable)) {
      hiveFiles = HiveLocationUtil.getHiveLocation(arcticTable);
    } else {
      hiveFiles = new HashSet<>();
    }
  }

  @Override
  public void cleanContentFiles(long lastTime) {
    if (changeMaintainer != null) {
      changeMaintainer.cleanContentFiles(lastTime);
    }
    baseMaintainer.cleanContentFiles(lastTime);
  }

  @Override
  public void cleanMetadata(long lastTime) {
    if (changeMaintainer != null) {
      changeMaintainer.cleanMetadata(lastTime);
    }
    baseMaintainer.cleanMetadata(lastTime);
  }

  @Override
  public void cleanDanglingDeleteFiles() {
    if (changeMaintainer != null) {
      changeMaintainer.cleanDanglingDeleteFiles();
    }
    baseMaintainer.cleanDanglingDeleteFiles();
  }

  @Override
  public void expireSnapshots(TableRuntime tableRuntime) {
    if (changeMaintainer != null) {
      changeMaintainer.expireSnapshots(tableRuntime);
    }
    baseMaintainer.expireSnapshots(tableRuntime);
  }

  @Override
  public void expireSnapshots(long mustOlderThan) {
    if (changeMaintainer != null) {
      changeMaintainer.expireSnapshots(mustOlderThan);
    }
    baseMaintainer.expireSnapshots(mustOlderThan);
  }

  public ChangeTableMaintainer getChangeMaintainer() {
    return changeMaintainer;
  }

  public BaseTableMaintainer getBaseMaintainer() {
    return baseMaintainer;
  }

  private Set<String> mergeSets(Set<String>... sets) {
    Set<String> result = new HashSet<>();
    for (Set<String> set : sets) {
      result.addAll(set);
    }
    return result;
  }

  public class ChangeTableMaintainer extends IcebergTableMaintainer {

    private static final int DATA_FILE_LIST_SPLIT = 3000;

    private UnkeyedTable unkeyedTable;

    public ChangeTableMaintainer(UnkeyedTable unkeyedTable) {
      super(unkeyedTable);
      this.unkeyedTable = unkeyedTable;
    }

    @Override
    public Set<String> orphanFileCleanNeedToExcludeFiles() {
      return mergeSets(changeFiles, baseFiles, hiveFiles);
    }

    public void expireSnapshots(TableRuntime tableRuntime) {
      expireSnapshots(Long.MAX_VALUE);
    }

    @Override
    public void expireSnapshots(long mustOlderThan) {
      long changeDataTTL = getChangeDataTTL();
      expireFiles(Longs.min(System.currentTimeMillis() - changeDataTTL, mustOlderThan));
      super.expireSnapshots(mustOlderThan);
    }

    protected long olderThanSnapshotNeedToExpire(long mustOlderThan) {
      long changeDataTTL = getChangeDataTTL();
      long latestChangeFlinkCommitTime = fetchLatestFlinkCommittedSnapshotTime(unkeyedTable);
      long changeTTLPoint = System.currentTimeMillis() - changeDataTTL;
      return Longs.min(latestChangeFlinkCommitTime, mustOlderThan, changeTTLPoint);
    }

    @Override
    protected Set<String> expireSnapshotNeedToExcludeFiles() {
      return mergeSets(baseFiles, hiveFiles);
    }

    public void expireFiles(long ttlPoint) {
      List<IcebergFileEntry> expiredDataFileEntries = getExpiredDataFileEntries(ttlPoint);
      deleteChangeFile(expiredDataFileEntries);
    }

    private long getChangeDataTTL() {
      long changeDataTTL = CompatiblePropertyUtil.propertyAsLong(
          unkeyedTable.properties(),
          TableProperties.CHANGE_DATA_TTL,
          TableProperties.CHANGE_DATA_TTL_DEFAULT) * 60 * 1000;
      return changeDataTTL;
    }

    private List<IcebergFileEntry> getExpiredDataFileEntries(long ttlPoint) {
      TableEntriesScan entriesScan = TableEntriesScan.builder(unkeyedTable)
          .includeFileContent(FileContent.DATA)
          .build();
      List<IcebergFileEntry> changeTTLFileEntries = new ArrayList<>();

      try (CloseableIterable<IcebergFileEntry> entries = entriesScan.entries()) {
        entries.forEach(entry -> {
          Snapshot snapshot = unkeyedTable.snapshot(entry.getSnapshotId());
          if (snapshot == null || snapshot.timestampMillis() < ttlPoint) {
            changeTTLFileEntries.add(entry);
          }
        });
      } catch (IOException e) {
        throw new UncheckedIOException("Failed to close manifest entry scan of " + table.name(), e);
      }
      return changeTTLFileEntries;
    }

    private void deleteChangeFile(List<IcebergFileEntry> expiredDataFileEntries) {
      KeyedTable keyedTable = arcticTable.asKeyedTable();
      if (CollectionUtils.isEmpty(expiredDataFileEntries)) {
        return;
      }

      StructLikeMap<Long> partitionMaxTransactionId = TablePropertyUtil.getPartitionOptimizedSequence(keyedTable);
      if (MapUtils.isEmpty(partitionMaxTransactionId)) {
        LOG.info("table {} not contains max transaction id", keyedTable.id());
        return;
      }

      Map<String, List<IcebergFileEntry>> partitionDataFileMap = expiredDataFileEntries.stream()
          .collect(Collectors.groupingBy(entry ->
              keyedTable.spec().partitionToPath(entry.getFile().partition()), Collectors.toList()));

      List<DataFile> changeDeleteFiles = new ArrayList<>();
      if (keyedTable.spec().isUnpartitioned()) {
        List<IcebergFileEntry> partitionDataFiles =
            partitionDataFileMap.get(keyedTable.spec().partitionToPath(
                expiredDataFileEntries.get(0).getFile().partition()));

        Long optimizedSequence = partitionMaxTransactionId.get(TablePropertyUtil.EMPTY_STRUCT);
        if (optimizedSequence != null && CollectionUtils.isNotEmpty(partitionDataFiles)) {
          changeDeleteFiles.addAll(partitionDataFiles.stream()
              .filter(entry -> FileNameRules.parseChangeTransactionId(
                  entry.getFile().path().toString(), entry.getSequenceNumber()) <= optimizedSequence)
              .map(entry -> (DataFile) entry.getFile())
              .collect(Collectors.toList()));
        }
      } else {
        partitionMaxTransactionId.forEach((key, value) -> {
          List<IcebergFileEntry> partitionDataFiles =
              partitionDataFileMap.get(keyedTable.spec().partitionToPath(key));

          if (CollectionUtils.isNotEmpty(partitionDataFiles)) {
            changeDeleteFiles.addAll(partitionDataFiles.stream()
                .filter(entry -> FileNameRules.parseChangeTransactionId(
                    entry.getFile().path().toString(), entry.getSequenceNumber()) <= value)
                .map(entry -> (DataFile) entry.getFile())
                .collect(Collectors.toList()));
          }
        });
      }
      tryClearChangeFiles(changeDeleteFiles);
    }

    private void tryClearChangeFiles(List<DataFile> changeFiles) {
      if (CollectionUtils.isEmpty(changeFiles)) {
        return;
      }
      try {
        for (int startIndex = 0; startIndex < changeFiles.size(); startIndex += DATA_FILE_LIST_SPLIT) {
          int end = Math.min(startIndex + DATA_FILE_LIST_SPLIT, changeFiles.size());
          List<DataFile> tableFiles = changeFiles.subList(startIndex, end);
          LOG.info("{} delete {} change files", unkeyedTable.name(), tableFiles.size());
          if (!tableFiles.isEmpty()) {
            DeleteFiles changeDelete = unkeyedTable.newDelete();
            changeFiles.forEach(changeDelete::deleteFile);
            changeDelete.commit();
          }
          LOG.info("{} change committed, delete {} files, complete {}/{}", unkeyedTable.name(),
              tableFiles.size(), end, changeFiles.size());
        }
      } catch (Throwable t) {
        LOG.error(unkeyedTable.name() + " failed to delete change files, ignore", t);
      }
    }
  }

  public class BaseTableMaintainer extends IcebergTableMaintainer {

    private UnkeyedTable unkeyedTable;

    public BaseTableMaintainer(UnkeyedTable unkeyedTable) {
      super(unkeyedTable);
      this.unkeyedTable = unkeyedTable;
    }

    @Override
    public Set<String> orphanFileCleanNeedToExcludeFiles() {
      return mergeSets(changeFiles, baseFiles, hiveFiles);
    }

    @Override
    protected Set<String> expireSnapshotNeedToExcludeFiles() {
      return mergeSets(changeFiles, hiveFiles);
    }
  }
}
