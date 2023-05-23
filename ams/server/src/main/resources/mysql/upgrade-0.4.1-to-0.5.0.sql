-- container_metadata
ALTER TABLE `container_metadata` CHANGE `name` `container_name` varchar(64);
ALTER TABLE `container_metadata` CHANGE `type` `container_type` varchar(64);

-- catalog_metadata
ALTER TABLE `catalog_metadata` ADD `database_count` INT NOT NULL DEFAULT 0;
ALTER TABLE `catalog_metadata` ADD `table_count` INT NOT NULL DEFAULT 0;
ALTER TABLE `catalog_metadata` CHANGE `catalog_type` `catalog_metastore` VARCHAR(64) NOT NULL;
ALTER TABLE `catalog_metadata` DROP COLUMN `display_name`;

-- database_metadata
ALTER TABLE `database_metadata` ADD `table_count` INT NOT NULL DEFAULT 0;
ALTER TABLE `database_metadata` DROP COLUMN `db_id`;
ALTER TABLE `database_metadata` ADD PRIMARY KEY (`catalog_name`, `db_name`);

-- resource
CREATE TABLE `resource`
(
    `resource_id`               varchar(100) DEFAULT NULL  COMMENT 'optimizer instance id',
    `resource_type`             tinyint(4) DEFAULT 0 COMMENT 'resource type like optimizer/ingestor',
    `container_name`            varchar(100) DEFAULT NULL  COMMENT 'container name',
    `group_name`                varchar(50) DEFAULT NULL COMMENT 'queue name',
    `thread_count`              int(11) DEFAULT NULL COMMENT 'total number of all CPU resources',
    `total_memory`              bigint(30) DEFAULT NULL COMMENT 'optimizer use memory size',
    `start_time`                timestamp not null default CURRENT_TIMESTAMP COMMENT 'optimizer start time',
    `properties`                mediumtext COMMENT 'optimizer instance properties',
    KEY  `resource_group` (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'Optimizer instance info';

-- optimize_group
CREATE TABLE `resource_group`
(
    `group_name`       varchar(50) NOT NULL  COMMENT 'Optimize group name',
    `container_name`   varchar(100) DEFAULT NULL  COMMENT 'Container name',
    `properties`       mediumtext  COMMENT 'Properties',
    PRIMARY KEY (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'Group to divide optimize resources';
INSERT INTO `resource_group` (`group_name`, `container_name`, `properties`) SELECT `name`, `container`, `properties` FROM `optimize_group`;
DROP TABLE `optimize_group`;

-- table_identifier
CREATE TABLE `table_identifier`
(
    `table_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Auto increment id',
    `catalog_name`    varchar(64) NOT NULL COMMENT 'Catalog name',
    `db_name`         varchar(128) NOT NULL COMMENT 'Database name',
    `table_name`      varchar(128) NOT NULL COMMENT 'Table name',
    PRIMARY KEY (`table_id`),
    UNIQUE KEY `table_name_index` (`catalog_name`,`db_name`,`table_name`)
);
INSERT INTO `table_identifier` (`catalog_name`, `db_name`, `table_name`) SELECT `catalog_name`, `db_name`, `table_name` FROM `table_metadata`;

-- table_metadata
ALTER TABLE `table_metadata` ADD `table_id` bigint(20) NOT NULL COMMENT 'table id' FIRST;
ALTER TABLE `table_metadata` drop PRIMARY KEY;
ALTER TABLE `table_metadata` CHANGE `delta_location` `change_location` varchar(256) DEFAULT NULL;
ALTER TABLE `table_metadata` CHANGE `cur_schema_id` `current_schema_id` int(11) NOT NULL DEFAULT 0;
ALTER TABLE `table_metadata` DROP COLUMN `hbase_site`;
ALTER TABLE `table_metadata` DROP COLUMN `current_tx_id`;
UPDATE `table_metadata` JOIN `table_identifier`
ON `table_metadata`.`catalog_name` = `table_identifier`.`catalog_name`
AND `table_metadata`.`db_name` = `table_identifier`.`db_name`
AND `table_metadata`.`table_name` = `table_identifier`.`table_name` SET `table_metadata`.`table_id` = `table_identifier`.`table_id`;
ALTER TABLE `table_metadata` ADD PRIMARY KEY (`table_id`);

-- platform_file
RENAME TABLE `platform_file_info` to `platform_file`;

-- optimizer
RENAME TABLE `optimizer` to `optimizer_temp`;
CREATE TABLE `optimizer`
(
    `token`                      varchar(50) NOT NULL,
    `resource_id`                varchar(100) DEFAULT NULL  COMMENT 'optimizer instance id',
    `group_name`                 varchar(50) DEFAULT NULL COMMENT 'group/queue name',
    `container_name`             varchar(100) DEFAULT NULL  COMMENT 'container name',
    `start_time`                 timestamp not null default CURRENT_TIMESTAMP COMMENT 'optimizer start time',
    `touch_time`                 timestamp not null default CURRENT_TIMESTAMP COMMENT 'update time',
    `thread_count`               int(11) DEFAULT NULL COMMENT 'total number of all CPU resources',
    `total_memory`               bigint(30) DEFAULT NULL COMMENT 'optimizer use memory size',
    `properties`                 mediumtext COMMENT 'optimizer state info, contains like yarn application id and flink job id',
    PRIMARY KEY (`token`),
    KEY  `resource_group` (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'resource table';

DROP TABLE IF EXISTS `optimizer_temp`;


-- optimizer
CREATE TABLE `table_runtime`
(
    `table_id`                      bigint(20) NOT NULL,
    `catalog_name`                  varchar(64) NOT NULL COMMENT 'Catalog name',
    `db_name`                       varchar(128) NOT NULL COMMENT 'Database name',
    `table_name`                    varchar(128) NOT NULL COMMENT 'Table name',
    `current_snapshot_id`           bigint(20) NOT NULL DEFAULT '-1' COMMENT 'Base table current snapshot id',
    `current_change_snapshotId`     bigint(20) DEFAULT NULL COMMENT 'Change table current snapshot id',
    `last_optimized_snapshotId`     bigint(20) NOT NULL DEFAULT '-1' COMMENT 'last optimized snapshot id',
    `last_major_optimizing_time`    timestamp COMMENT 'Latest Major Optimize time for all partitions',
    `last_minor_optimizing_time`    timestamp COMMENT 'Latest Minor Optimize time for all partitions',
    `last_full_optimizing_time`     timestamp COMMENT 'Latest Full Optimize time for all partitions',
    `optimizing_status`             varchar(20) DEFAULT 'Idle' COMMENT 'Table optimize status: MajorOptimizing, MinorOptimizing, Pending, Idle',
    `optimizing_status_start_time`  timestamp default CURRENT_TIMESTAMP COMMENT 'Table optimize status start time',
    `optimizing_process_id`         bigint(20) NOT NULL COMMENT 'optimizing_procedure UUID',
    `optimizer_group`               varchar(64) NOT NULL,
    `table_config`                  mediumtext,
    `optimizing_config`             mediumtext,
    PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'Optimize running information of each table';



-- table `task_runtime`
CREATE TABLE `task_runtime`
(
    `process_id`                bigint(20) NOT NULL,
    `task_id`                   int(11) NOT NULL,
    `retry_num`                 int(11) DEFAULT NULL COMMENT 'Retry times',
    `table_id`                  bigint(20) NOT NULL,
    `partition_data`                 varchar(128)  DEFAULT NULL COMMENT 'Partition data',
    `create_time`               datetime(3) DEFAULT NULL COMMENT 'Task create time',
    `start_time`                datetime(3) DEFAULT NULL COMMENT 'Time when task start waiting to execute',
    `end_time`                  datetime(3) DEFAULT NULL COMMENT 'Time when task finished',
    `cost_time`                 bigint(20) DEFAULT NULL,
    `status`                    varchar(16)   DEFAULT NULL  COMMENT 'Optimize Status: Init, Pending, Executing, Failed, Prepared, Committed',
    `fail_reason`               varchar(4096) DEFAULT NULL COMMENT 'Error message after task failed',
    `optimizer_token`           varchar(50) DEFAULT NULL COMMENT 'Job type',
    `thread_id`                 int(11) DEFAULT NULL COMMENT 'Job id',
    `rewrite_output`            blob DEFAULT NULL COMMENT 'rewrite files input',
    `metrics_summary`           text COMMENT 'metrics summary',
    PRIMARY KEY (`process_id`, `task_id`),
    KEY  `table_index` (`table_id`, `process_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'Optimize task basic information';

-- table `table_optimizing_process`
CREATE TABLE `table_optimizing_process`
(
    `process_id`                    bigint(20) NOT NULL COMMENT 'optimizing_procedure UUID',
    `table_id`                      bigint(20) NOT NULL,
    `catalog_name`                  varchar(64) NOT NULL COMMENT 'Catalog name',
    `db_name`                       varchar(128) NOT NULL COMMENT 'Database name',
    `table_name`                    varchar(128) NOT NULL COMMENT 'Table name',
    `target_snapshot_id`            bigint(20) NOT NULL,
    `target_change_snapshot_id`     bigint(20) NOT NULL,
    `status`                        varchar(10) NOT NULL COMMENT 'Direct to TableOptimizingStatus',
    `optimizing_type`               varchar(10) NOT NULL COMMENT 'Optimize type: Major, Minor',
    `plan_time`                     timestamp default CURRENT_TIMESTAMP COMMENT 'First plan time',
    `end_time`                      timestamp default CURRENT_TIMESTAMP COMMENT 'Execute cost time',
    `fail_reason`                   varchar(4096) DEFAULT NULL COMMENT 'Error message after task failed',
    `rewrite_input`                 mediumblob DEFAULT NULL COMMENT 'rewrite files input',
    `summary`                       mediumtext COMMENT 'Max change transaction id of these tasks',
    `from_sequence`                 mediumtext COMMENT 'from or min sequence of each partition',
    `to_sequence`                   mediumtext COMMENT 'to or max sequence of each partition',
    PRIMARY KEY (`process_id`),
    KEY  `table_index` (`table_id`, `plan_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'History of optimizing after each commit';

-- table `optimizing_task_quota`
CREATE TABLE `optimizing_task_quota`
(
    `process_id`                bigint(20) NOT NULL COMMENT 'optimizing_procedure UUID',
    `task_id`                   int(11) NOT NULL COMMENT 'Optimize task unique id',
    `retry_num`                 int(11) DEFAULT 0 COMMENT 'Retry times',
    `table_id`                  bigint(20) NOT NULL,
    `start_time`                timestamp default CURRENT_TIMESTAMP COMMENT 'Time when task start waiting to execute',
    `end_time`                  timestamp default CURRENT_TIMESTAMP COMMENT 'Time when task finished',
    `fail_reason`               varchar(4096) DEFAULT NULL COMMENT 'Error message after task failed',
    PRIMARY KEY (`process_id`, `task_id`, `retry_num`),
    KEY  `table_index` (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'Optimize task basic information';

-- init table_runtime
insert into table_runtime (table_id, catalog_name, db_name, table_name, current_snapshot_id, current_change_snapshotId,
last_optimized_snapshotId, last_optimized_change_snapshotId,
last_major_optimizing_time, last_minor_optimizing_time, last_full_optimizing_time,optimizing_status,
optimizing_status_start_time, optimizing_process_id, optimizer_group)
select t.table_id,s.catalog_name,s.db_name,s.table_name,s.current_snapshot_id,s.current_change_snapshotId,-1 last_optimized_snapshotId,
-1 last_optimized_change_snapshotId,
FROM_UNIXTIME(JSON_EXTRACT(s.latest_major_optimize_time, '$.""')/1000) last_major_optimizing_time,
FROM_UNIXTIME(JSON_EXTRACT(s.latest_minor_optimize_time, '$.""')/1000) last_minor_optimizing_time,
FROM_UNIXTIME(JSON_EXTRACT(s.latest_full_optimize_time,'$.""')/1000) last_full_optimizing_time,
s.optimize_status,
CASE
	WHEN s.optimize_status_start_time < "1970-01-01 08:00:00.000" THEN "1970-01-01 08:00:01.000"
	ELSE s.optimize_status_start_time
	END AS optimizing_status_start_time
,
-1 optimizing_process_id,"default" optimizer_group from optimize_table_runtime s JOIN table_identifier t
ON s.`catalog_name` = t.`catalog_name`
AND s.`db_name`= t.`db_name`
AND s.`table_name` = t.`table_name`;



DROP TABLE IF EXISTS `optimize_file`;
DROP TABLE IF EXISTS `optimize_history`;
DROP TABLE IF EXISTS `optimize_table_runtime`;
DROP TABLE IF EXISTS `optimize_task`;
DROP TABLE IF EXISTS `optimize_task_history`;
DROP TABLE IF EXISTS `table_transaction_meta`;
DROP TABLE IF EXISTS `snapshot_info_cache`;
DROP TABLE IF EXISTS `ddl_record`;
DROP TABLE IF EXISTS `file_info_cache`;


