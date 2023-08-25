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

import com.netease.arctic.data.IcebergContentFile;
import com.netease.arctic.data.IcebergDataFile;
import com.netease.arctic.server.optimizing.OptimizingType;

import java.util.List;
import java.util.Map;

/**
 * PartitionEvaluator is used to evaluate whether a partition is necessary to be optimized.
 */
public interface PartitionEvaluator {

  /**
   * Weight determines the priority of partition execution, with higher weights having higher priority.
   */
  interface Weight extends Comparable<Weight> {

  }

  /**
   * Get the partition name.
   *
   * @return the partition name
   */
  String getPartition();

  /**
   * Add a Data file and its related Delete files to this evaluator
   *
   * @param dataFile - Data file
   * @param deletes  - Delete files
   * @return true if the file is added successfully, false if the file will not be optimized
   */
  boolean addFile(IcebergDataFile dataFile, List<IcebergContentFile<?>> deletes);

  /**
   * Add some properties of this partition. It should be noted that properties should be added before adding any files.
   *
   * @param properties - properties of this partition
   */
  void addPartitionProperties(Map<String, String> properties);

  /**
   * Whether this partition is necessary to optimize.
   *
   * @return true for is necessary to optimize, false for not necessary
   */
  boolean isNecessary();

  /**
   * Get the cost of optimizing for this partition.
   *
   * @return the cost of optimizing
   */
  long getCost();

  /**
   * Get the weight of this partition which determines the priority of partition execution.
   *
   * @return the weight of this partition
   */
  Weight getWeight();

  /**
   * Get the optimizing type of this partition.
   *
   * @return the OptimizingType
   */
  OptimizingType getOptimizingType();

  /**
   * Get the count of fragment files involved in optimizing.
   */
  int getFragmentFileCount();

  /**
   * Get the total size of fragment files involved in optimizing.
   */
  long getFragmentFileSize();

  /**
   * Get the count of segment files involved in optimizing.
   */
  int getSegmentFileCount();

  /**
   * Get the total size of segment files involved in optimizing.
   */
  long getSegmentFileSize();

  /**
   * Get the count of equality delete files involved in optimizing.
   */
  int getEqualityDeleteFileCount();

  /**
   * Get the total size of equality delete files involved in optimizing.
   */
  long getEqualityDeleteFileSize();

  /**
   * Get the count of positional delete files involved in optimizing.
   */
  int getPosDeleteFileCount();

  /**
   * Get the total size of positional delete files involved in optimizing.
   */
  long getPosDeleteFileSize();

}
