/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *  *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netease.arctic.ams.server.mapper;

import com.netease.arctic.ams.server.model.TableMetricsStatistic;
import com.netease.arctic.ams.server.mybatis.Long2TsConvertor;
import com.netease.arctic.ams.server.mybatis.TableIdentifier2StringConverter;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TableMetricsStatisticMapper {
  String TABLE_NAME = "table_metric_statistics";

  @Insert("insert into " + TABLE_NAME + " (table_identifier, inner_table, metric_name, metric_value) values(" +
      "#{metricsStatistic.tableIdentifier, typeHandler=com.netease.arctic.ams.server.mybatis" +
      ".TableIdentifier2StringConverter}, #{metricsStatistic.innerTable}," +
      "#{metricsStatistic.metricName}, #{metricsStatistic.metricValue})")
  void insertMetricsStatistic(@Param("metricsStatistic") TableMetricsStatistic metricsStatistic);

  @Update("update " + TABLE_NAME + " set metric_value = #{metricsStatistic.metricValue} where metric_name = " +
      "#{metricsStatistic.metricName} and table_identifier = #{metricsStatistic.tableIdentifier, typeHandler=com" +
      ".netease.arctic.ams.server.mybatis.TableIdentifier2StringConverter} and inner_table = #{metricsStatistic" +
      ".innerTable}")
  void updateMetricsStatistic(@Param("metricsStatistic") TableMetricsStatistic metricsStatistic);

  @Select("select table_identifier, inner_table, metric_name, metric_value, commit_time from " + TABLE_NAME + "  " +
      "where metric_name = #{metricsStatistic.metricName} and table_identifier = #{metricsStatistic.tableIdentifier," +
      " typeHandler=com.netease.arctic.ams.server.mybatis.TableIdentifier2StringConverter} and inner_table = " +
      "#{metricsStatistic.innerTable}")
  @Results({
      @Result(column = "table_identifier", property = "tableIdentifier",
          typeHandler = TableIdentifier2StringConverter.class),
      @Result(column = "inner_table", property = "innerTable"),
      @Result(column = "metric_name", property = "metricName"),
      @Result(column = "metric_value", property = "metricValue"),
      @Result(column = "commit_time", property = "commitTime",
          typeHandler = Long2TsConvertor.class)
  })
  List<TableMetricsStatistic> getMetricsStatistic(@Param("metricsStatistic") TableMetricsStatistic metricsStatistic);

  @Insert("insert into metric_statistics_summary (metric_name, metric_value,commit_time) select metric_name, sum(CAST" +
      "(metric_value AS SIGNED)), #{commitTime, typeHandler=com.netease.arctic.ams" +
      ".server.mybatis.Long2TsConvertor} from " + TABLE_NAME + " where metric_name = #{metricName}")
  void summaryMetrics(
      @Param("metricName") String metricName,
      @Param("commitTime") long commitTime);
}
