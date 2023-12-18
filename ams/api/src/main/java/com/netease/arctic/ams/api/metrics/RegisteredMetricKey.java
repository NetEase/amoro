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
package com.netease.arctic.ams.api.metrics;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.iceberg.relocated.com.google.common.base.Joiner;
import org.apache.iceberg.relocated.com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** MapKey define of registered metric */
public class RegisteredMetricKey {

  private final MetricDefine define;
  private final Map<String, String> valueOfTags;
  private final String explicitTagValues;

  public static RegisteredMetricKey buildRegisteredMetricKey(
      MetricDefine define, List<String> tags) {
    Preconditions.checkNotNull(define);
    Preconditions.checkNotNull(tags, "Tags cannot be null");
    Preconditions.checkArgument(
        define.getTags().size() == tags.size(),
        "The number of tags is not equal to the number of defined tags.");

    Map<String, String> tagValues =
        IntStream.range(0, define.getTags().size())
            .boxed()
            .collect(Collectors.toMap(define.getTags()::get, tags::get));
    return new RegisteredMetricKey(define, ImmutableMap.copyOf(tagValues));
  }

  public RegisteredMetricKey(MetricDefine define, Map<String, String> tagValues) {
    Preconditions.checkNotNull(define);
    this.valueOfTags = tagValues == null ? ImmutableMap.of() : tagValues;
    if (define.getTags().size() != this.valueOfTags.size()) {
      throw new IllegalArgumentException(
          "Tag value is miss-match with metric define. MetricDefine{"
              + define
              + "} given tags:"
              + Joiner.on(",").join(this.valueOfTags.keySet().stream().sorted().iterator()));
    }

    define
        .getTags()
        .forEach(
            t ->
                Preconditions.checkArgument(
                    this.valueOfTags.containsKey(t), "The value of tag: %s is missed", t));
    this.define = define;

    explicitTagValues =
        Joiner.on(",")
            .join(define.getTags().stream().sorted().map(this.valueOfTags::get).iterator());
  }

  public MetricDefine getDefine() {
    return define;
  }

  public String valueOfTag(String tag) {
    return valueOfTags.getOrDefault(tag, "");
  }

  public List<String> valueOfTags() {
    List<String> valueOfTags = Lists.newArrayList();
    this.getDefine().getTags().forEach(t -> valueOfTags.add(valueOfTag(t)));
    return Collections.unmodifiableList(valueOfTags);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisteredMetricKey that = (RegisteredMetricKey) o;
    return Objects.equals(this.define, that.define)
        && Objects.equals(this.explicitTagValues, that.explicitTagValues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.define, this.explicitTagValues);
  }
}
