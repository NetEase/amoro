package com.netease.arctic.server.optimizing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netease.arctic.optimizing.RewriteFilesInput;
import org.apache.iceberg.ContentFile;
import org.apache.iceberg.DataFile;
import org.apache.iceberg.FileContent;
import org.apache.iceberg.relocated.com.google.common.base.MoreObjects;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricsSummary {
  private long rewriteDataSize = 0;
  private int rewriteDataFileCnt = 0;
  private long rewriteDataRecordCnt = 0;
  private long rewritePosDataSize = 0;
  private int rewritePosDataFileCnt = 0;
  /** @deprecated since 0.7.0, will be removed in 0.8.0 */
  @Deprecated private int reRowDeletedDataFileCnt = 0;

  private long rewritePosDataRecordCnt = 0;
  private long equalityDeleteSize = 0;
  private int eqDeleteFileCnt = 0;
  private long eqDeleteRecordCnt = 0;
  private long positionDeleteSize = 0;
  /** @deprecated since 0.7.0, will be removed in 0.8.0 */
  @Deprecated private long positionalDeleteSize = 0;

  private int posDeleteFileCnt = 0;
  private long posDeleteRecordCnt = 0;

  /** @deprecated since 0.7.0, will be removed in 0.8.0 */
  @Deprecated long newFileSize = 0;

  @Deprecated int newFileCnt = 0;
  private long newDataSize = 0;
  private int newDataFileCnt = 0;
  private long newDataRecordCnt = 0;
  /** Only position delete files will be generated */
  private long newDeleteSize = 0;

  private int newDeleteFileCnt = 0;
  private long newDeleteRecordCnt = 0;

  public MetricsSummary() {}

  protected MetricsSummary(RewriteFilesInput input) {
    rewriteDataFileCnt = input.rewrittenDataFiles().length;
    rewritePosDataFileCnt = input.rePosDeletedDataFiles().length;
    reRowDeletedDataFileCnt = input.rePosDeletedDataFiles().length;
    for (DataFile rewriteFile : input.rewrittenDataFiles()) {
      rewriteDataSize += rewriteFile.fileSizeInBytes();
      rewriteDataRecordCnt += rewriteFile.recordCount();
    }
    for (DataFile rewritePosDataFile : input.rePosDeletedDataFiles()) {
      rewritePosDataSize += rewritePosDataFile.fileSizeInBytes();
      rewritePosDataRecordCnt += rewritePosDataFile.recordCount();
    }
    for (ContentFile<?> delete : input.deleteFiles()) {
      if (delete.content() == FileContent.POSITION_DELETES) {
        positionDeleteSize += delete.fileSizeInBytes();
        positionalDeleteSize += delete.fileSizeInBytes();
        posDeleteRecordCnt += delete.recordCount();
        posDeleteFileCnt++;
      } else {
        equalityDeleteSize += delete.fileSizeInBytes();
        eqDeleteRecordCnt += delete.recordCount();
        eqDeleteFileCnt++;
      }
    }
  }

  public MetricsSummary(Collection<TaskRuntime> taskRuntimes) {
    taskRuntimes.stream()
        .map(TaskRuntime::getMetricsSummary)
        .forEach(
            metrics -> {
              newDataFileCnt += metrics.getNewDataFileCnt();
              newDataSize += metrics.getNewDataSize();
              newDataRecordCnt += metrics.getNewDataRecordCnt();
              newDeleteSize += metrics.getNewDeleteSize();
              newDeleteFileCnt += metrics.getNewDeleteFileCnt();
              newDeleteRecordCnt += metrics.getNewDeleteRecordCnt();
              rewriteDataFileCnt += metrics.getRewriteDataFileCnt();
              // to be compatible with old metrics name when calculating total metrics of all tasks
              reRowDeletedDataFileCnt +=
                  Math.max(
                      metrics.getReRowDeletedDataFileCnt(), metrics.getRewritePosDataFileCnt());
              rewritePosDataFileCnt +=
                  Math.max(
                      metrics.getReRowDeletedDataFileCnt(), metrics.getRewritePosDataFileCnt());
              rewriteDataSize += metrics.getRewriteDataSize();
              rewritePosDataSize += metrics.getRewritePosDataSize();
              posDeleteFileCnt += metrics.getPosDeleteFileCnt();
              positionalDeleteSize +=
                  Math.max(metrics.getPositionalDeleteSize(), metrics.getPositionDeleteSize());
              positionDeleteSize +=
                  Math.max(metrics.getPositionalDeleteSize(), metrics.getPositionDeleteSize());
              eqDeleteFileCnt += metrics.getEqDeleteFileCnt();
              equalityDeleteSize += metrics.getEqualityDeleteSize();
              rewriteDataRecordCnt += metrics.getRewriteDataRecordCnt();
              rewritePosDataRecordCnt += metrics.getRewritePosDataRecordCnt();
              eqDeleteRecordCnt += metrics.getEqDeleteRecordCnt();
              posDeleteRecordCnt += metrics.getPosDeleteRecordCnt();
              newFileCnt += metrics.getNewFileCnt();
              newFileSize += metrics.getNewFileSize();
            });
  }

  public long getNewFileSize() {
    return newFileSize;
  }

  public int getNewFileCnt() {
    return newFileCnt;
  }

  public long getNewDataSize() {
    return newDataSize;
  }

  protected void setNewDataSize(long newDataSize) {
    this.newDataSize = newDataSize;
  }

  public int getNewDataFileCnt() {
    return newDataFileCnt;
  }

  protected void setNewDataFileCnt(int newDataFileCnt) {
    this.newDataFileCnt = newDataFileCnt;
  }

  public long getNewDataRecordCnt() {
    return newDataRecordCnt;
  }

  protected void setNewDataRecordCnt(long newDataRecordCnt) {
    this.newDataRecordCnt = newDataRecordCnt;
  }

  public void setNewDeleteSize(long newDeleteSize) {
    this.newDeleteSize = newDeleteSize;
  }

  public void setNewDeleteFileCnt(int newDeleteFileCnt) {
    this.newDeleteFileCnt = newDeleteFileCnt;
  }

  public long getNewDeleteSize() {
    return newDeleteSize;
  }

  public int getNewDeleteFileCnt() {
    return newDeleteFileCnt;
  }

  public long getNewDeleteRecordCnt() {
    return newDeleteRecordCnt;
  }

  protected void setNewDeleteRecordCnt(long newDeleteRecordCnt) {
    this.newDeleteRecordCnt = newDeleteRecordCnt;
  }

  public void setNewFileSize(long newFileSize) {
    this.newFileSize = newFileSize;
  }

  public void setNewFileCnt(int newFileCnt) {
    this.newFileCnt = newFileCnt;
  }

  public long getRewriteDataSize() {
    return rewriteDataSize;
  }

  public long getRewritePosDataSize() {
    return rewritePosDataSize;
  }

  public long getEqualityDeleteSize() {
    return equalityDeleteSize;
  }

  public long getPositionalDeleteSize() {
    return positionalDeleteSize;
  }

  public long getPositionDeleteSize() {
    return positionDeleteSize;
  }

  public int getRewriteDataFileCnt() {
    return rewriteDataFileCnt;
  }

  public int getReRowDeletedDataFileCnt() {
    return reRowDeletedDataFileCnt;
  }

  public int getRewritePosDataFileCnt() {
    return rewritePosDataFileCnt;
  }

  public int getEqDeleteFileCnt() {
    return eqDeleteFileCnt;
  }

  public int getPosDeleteFileCnt() {
    return posDeleteFileCnt;
  }

  public long getRewriteDataRecordCnt() {
    return rewriteDataRecordCnt;
  }

  public long getRewritePosDataRecordCnt() {
    return rewritePosDataRecordCnt;
  }

  public long getEqDeleteRecordCnt() {
    return eqDeleteRecordCnt;
  }

  public long getPosDeleteRecordCnt() {
    return posDeleteRecordCnt;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("rewriteDataSize", rewriteDataSize)
        .add("rewriteDataFileCnt", rewriteDataFileCnt)
        .add("rewriteDataRecordCnt", rewriteDataRecordCnt)
        .add("rewritePosDataSize", rewritePosDataSize)
        .add("rewritePosDataFileCnt", rewritePosDataFileCnt)
        .add("reRowDeletedDataFileCnt", reRowDeletedDataFileCnt)
        .add("rewritePosDataRecordCnt", rewritePosDataRecordCnt)
        .add("equalityDeleteSize", equalityDeleteSize)
        .add("eqDeleteFileCnt", eqDeleteFileCnt)
        .add("eqDeleteRecordCnt", eqDeleteRecordCnt)
        .add("positionDeleteSize", positionDeleteSize)
        .add("positionalDeleteSize", positionalDeleteSize)
        .add("posDeleteFileCnt", posDeleteFileCnt)
        .add("posDeleteRecordCnt", posDeleteRecordCnt)
        .add("newFileSize", newFileSize)
        .add("newFileCnt", newFileCnt)
        .add("newDataSize", newDataSize)
        .add("newDataFileCnt", newDataFileCnt)
        .add("newDataRecordCnt", newDataRecordCnt)
        .add("newDeleteSize", newDeleteSize)
        .add("newDeleteFileCnt", newDeleteFileCnt)
        .add("newDeleteRecordCnt", newDeleteRecordCnt)
        .toString();
  }
}
