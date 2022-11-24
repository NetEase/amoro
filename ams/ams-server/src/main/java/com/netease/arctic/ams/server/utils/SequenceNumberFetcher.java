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

package com.netease.arctic.ams.server.utils;

import org.apache.iceberg.Table;
import org.apache.iceberg.relocated.com.google.common.collect.Maps;

import java.util.Map;

public class SequenceNumberFetcher {
  private final Table table;
  private final long snapshotId;
  private volatile Map<String, Long> cached;

  public static SequenceNumberFetcher with(Table table, long snapshotId) {
    return new SequenceNumberFetcher(table, snapshotId);
  }

  public SequenceNumberFetcher(Table table, long snapshotId) {
    this.table = table;
    this.snapshotId = snapshotId;
  }

  /**
   * Get Sequence Number of file
   * @param filePath path of a file
   * @return sequenceNumber of this file
   */
  public long sequenceNumberOf(String filePath) {
    return getCached().get(filePath);
  }
  
  private Map<String, Long> getCached() {
    if (cached == null) {
      // TODO wangtao3 init
      cached = Maps.newHashMap();
    }
    return cached;
  }
}
