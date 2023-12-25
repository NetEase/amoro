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

package com.netease.arctic.server.optimizing.plan;

import com.netease.arctic.optimizing.RewriteFilesInput;

import java.util.Map;

public class TaskDescriptor {
  private final long tableId;
  private final String partition;
  private final RewriteFilesInput input;
  private final Map<String, String> properties;

  TaskDescriptor(
      long tableId, String partition, RewriteFilesInput input, Map<String, String> properties) {
    this.tableId = tableId;
    this.partition = partition;
    this.input = input;
    this.properties = properties;
  }

  public String getPartition() {
    return partition;
  }

  public RewriteFilesInput getInput() {
    return input;
  }

  public Map<String, String> properties() {
    return properties;
  }

  public long getTableId() {
    return tableId;
  }
}
