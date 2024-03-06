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

package com.netease.arctic.ams.api.process;

import org.apache.iceberg.relocated.com.google.common.base.MoreObjects;
import org.apache.iceberg.relocated.com.google.common.collect.Sets;

import java.util.Set;

/** Pending input for optimizing processes. */
public class PendingInput {

  private final Set<String> partitions = Sets.newHashSet();

  private int dataFileCount = 0;
  private long dataFileSize = 0;
  private int equalityDeleteFileCount = 0;
  private long equalityDeleteFileSize = 0L;
  private int positionalDeleteFileCount = 0;
  private long positionalDeleteFileSize = 0L;
  private long currentSnapshotId;
  private long currentChangeSnapshotId;
  private long currentWatermark;
  private long currentChangeWatermark;
  private boolean needMinorOptimizing = false;
  private boolean needMajorOptimizing = false;

  public PendingInput() {}

  public PendingInput(
      Set<String> partitions,
      int dataFileCount,
      long dataFileSize,
      int equalityDeleteFileCount,
      int positionalDeleteFileCount,
      long positionalDeleteFileSize,
      long equalityDeleteFileSize,
      boolean needMinorOptimizing,
      boolean needMajorOptimizing) {
    this.partitions.addAll(partitions);
    this.dataFileCount = dataFileCount;
    this.dataFileSize = dataFileSize;
    this.equalityDeleteFileCount = equalityDeleteFileCount;
    this.positionalDeleteFileCount = positionalDeleteFileCount;
    this.positionalDeleteFileSize = positionalDeleteFileSize;
    this.equalityDeleteFileSize = equalityDeleteFileSize;
    this.needMinorOptimizing = needMinorOptimizing;
    this.needMajorOptimizing = needMajorOptimizing;
  }

  public int getInputFileCount() {
    return dataFileCount + equalityDeleteFileCount + positionalDeleteFileCount;
  }

  public long getInputFileSize() {
    return dataFileSize + equalityDeleteFileSize + positionalDeleteFileSize;
  }

  public Set<String> getPartitions() {
    return partitions;
  }

  public int getDataFileCount() {
    return dataFileCount;
  }

  public long getDataFileSize() {
    return dataFileSize;
  }

  public int getEqualityDeleteFileCount() {
    return equalityDeleteFileCount;
  }

  public int getPositionalDeleteFileCount() {
    return positionalDeleteFileCount;
  }

  public long getPositionalDeleteFileSize() {
    return positionalDeleteFileSize;
  }

  public long getEqualityDeleteFileSize() {
    return equalityDeleteFileSize;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("partitions", partitions)
        .add("dataFileCount", dataFileCount)
        .add("dataFileSize", dataFileSize)
        .add("equalityDeleteFileCount", equalityDeleteFileCount)
        .add("positionalDeleteFileCount", positionalDeleteFileCount)
        .add("positionalDeleteBytes", positionalDeleteFileSize)
        .add("equalityDeleteBytes", equalityDeleteFileSize)
        .toString();
  }

  public long getCurrentSnapshotId() {
    return currentSnapshotId;
  }

  public long getCurrentChangeSnapshotId() {
    return currentChangeSnapshotId;
  }

  public void setCurrentSnapshotId(long currentSnapshotId) {
    this.currentSnapshotId = currentSnapshotId;
  }

  public void setCurrentChangeSnapshotId(long currentChangeSnapshotId) {
    this.currentChangeSnapshotId = currentChangeSnapshotId;
  }

  public boolean needMinorOptimizing() {
    return needMinorOptimizing;
  }

  public boolean needMajorOptimizing() {
    return needMajorOptimizing;
  }

  public int getFragmentFileCount() {
    return 0;
  }

  public long getFragmentFileSize() {
    return 0;
  }

  public int getSegmentFileCount() {
    return 0;
  }

  public long getSegmentFileSize() {
    return 0;
  }

  public long getWatermark() {
    return currentWatermark;
  }

  public long getChangeWatermark() {
    return currentChangeWatermark;
  }
}
