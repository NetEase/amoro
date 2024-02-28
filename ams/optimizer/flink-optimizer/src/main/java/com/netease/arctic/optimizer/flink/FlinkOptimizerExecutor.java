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

package com.netease.arctic.optimizer.flink;

import com.google.common.base.Strings;
import com.netease.arctic.ams.api.OptimizingTask;
import com.netease.arctic.ams.api.OptimizingTaskResult;
import com.netease.arctic.optimizer.common.OptimizerConfig;
import com.netease.arctic.optimizer.common.OptimizerExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/** @Auth: hzwangtao6 @Time: 2024/2/28 14:19 @Description: */
public class FlinkOptimizerExecutor extends OptimizerExecutor {
  private static final Logger LOG = LoggerFactory.getLogger(OptimizerExecutor.class);
  private Map<String, String> runtimeContext = new ConcurrentHashMap<String, String>();
  private Consumer<Integer> metricsReporter = null;

  public FlinkOptimizerExecutor(OptimizerConfig config, int threadId) {
    super(config, threadId);
  }

  public void addRuntimeContext(String key, String value) {
    runtimeContext.put(key, value);
  }

  public void setMetricReporter(Consumer<Integer> metricsReporter) {
    this.metricsReporter = metricsReporter;
  }

  @Override
  public void callBeforeTaskComplete(OptimizingTaskResult result) {
    if (metricsReporter != null) {
      // reporter metrics by flink, counter the number of tasks consumed
      metricsReporter.accept(1);
    }
  }

  @Override
  protected OptimizingTaskResult executeTask(OptimizingTask task) {
    OptimizingTaskResult result = executeTask(getConfig(), getThreadId(), task, LOG);
    // add optimizer flink runtime info, including application_id, tm_id
    StringBuilder sb = new StringBuilder();
    if (!Strings.isNullOrEmpty(result.getErrorMessage())) {
      if (runtimeContext != null && runtimeContext.size() > 0) {
        runtimeContext.forEach((k, v) -> sb.append(k).append("=").append(v).append("\n"));
      }
      result.setErrorMessage(sb.toString() + result.getErrorMessage());
    }
    return result;
  }
}
