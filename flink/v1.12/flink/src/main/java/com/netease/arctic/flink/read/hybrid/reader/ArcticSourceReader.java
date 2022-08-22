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

package com.netease.arctic.flink.read.hybrid.reader;

import com.netease.arctic.flink.read.ArcticSource;
import com.netease.arctic.flink.read.hybrid.enumerator.InitializationFinishedEvent;
import com.netease.arctic.flink.read.hybrid.split.ArcticSplit;
import com.netease.arctic.flink.read.hybrid.split.ArcticSplitState;
import com.netease.arctic.flink.read.hybrid.split.SplitRequestEvent;
import org.apache.flink.api.common.eventtime.Watermark;
import org.apache.flink.api.connector.source.ReaderOutput;
import org.apache.flink.api.connector.source.SourceEvent;
import org.apache.flink.api.connector.source.SourceReaderContext;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.base.source.reader.SingleThreadMultiplexSourceReaderBase;
import org.apache.flink.core.io.InputStatus;
import org.apache.iceberg.relocated.com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Arctic source reader that is created by a {@link ArcticSource#createReader(SourceReaderContext)}.
 */
public class ArcticSourceReader<T> extends
    SingleThreadMultiplexSourceReaderBase<ArcticRecordWithOffset<T>, T, ArcticSplit, ArcticSplitState> {

  public static final Logger LOGGER = LoggerFactory.getLogger(ArcticSourceReader.class);

  public ReaderOutput<T> output;
  /**
   * SourceEvents may be received before this#pollNext.
   */
  private volatile boolean maxWatermarkToBeEmitted = false;

  public ArcticSourceReader(
      ReaderFunction<T> readerFunction,
      Configuration config,
      SourceReaderContext context,
      boolean populateRowTime) {
    super(
        () -> new HybridSplitReader<>(
            readerFunction,
            context
        ),
        new ArcticRecordEmitter<>(populateRowTime),
        config,
        context);
  }

  @Override
  public void start() {
    // We request a split only if we did not get splits during the checkpoint restore.
    // Otherwise, reader restarts will keep requesting more and more splits.
    if (getNumberOfCurrentlyAssignedSplits() == 0) {
      requestSplit(Collections.emptyList());
    }
  }

  @Override
  protected void onSplitFinished(Map<String, ArcticSplitState> finishedSplitIds) {
    requestSplit(Lists.newArrayList(finishedSplitIds.keySet()));
  }

  @Override
  protected ArcticSplitState initializedState(ArcticSplit split) {
    return new ArcticSplitState(split);
  }

  @Override
  protected ArcticSplit toSplitType(String splitId, ArcticSplitState splitState) {
    return splitState.toSourceSplit();
  }

  private void requestSplit(Collection<String> finishedSplitIds) {
    context.sendSourceEventToCoordinator(new SplitRequestEvent(finishedSplitIds));
  }

  @Override
  public void handleSourceEvents(SourceEvent sourceEvent) {
    if (!(sourceEvent instanceof InitializationFinishedEvent)) {
      return;
    }
    LOGGER.info("receive StartWatermarkEvent");
    maxWatermarkToBeEmitted = true;
    emitWatermarkIfNeeded();
  }

  private void emitWatermarkIfNeeded() {
    if (this.output == null || !maxWatermarkToBeEmitted) {
      return;
    }
    LOGGER.info("emit watermark");
    output.emitWatermark(new Watermark(Long.MAX_VALUE));
    maxWatermarkToBeEmitted = false;
  }

  @Override
  public InputStatus pollNext(ReaderOutput<T> output) throws Exception {
    this.output = output;
    emitWatermarkIfNeeded();
    return super.pollNext(output);
  }

}
