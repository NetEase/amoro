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

package com.netease.arctic.ams.server.service.impl;

import com.netease.arctic.ams.api.BlockableOperation;
import com.netease.arctic.ams.api.OperationConflictException;
import com.netease.arctic.ams.server.config.ArcticMetaStoreConf;
import com.netease.arctic.ams.server.config.Configuration;
import com.netease.arctic.ams.server.mapper.TableBlockerMapper;
import com.netease.arctic.ams.server.model.TableBlocker;
import com.netease.arctic.ams.server.service.IJDBCService;
import com.netease.arctic.table.TableIdentifier;
import com.netease.arctic.table.blocker.BaseBlocker;
import org.apache.ibatis.session.SqlSession;
import org.apache.iceberg.relocated.com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class TableBlockerService extends IJDBCService {
  private static final Logger LOG = LoggerFactory.getLogger(TableBlockerService.class);
  private final long blockerTimeout;

  private final ConcurrentHashMap<TableIdentifier, ReentrantLock> TABLE_LOCK_MAP = new ConcurrentHashMap<>();

  public TableBlockerService(Configuration conf) {
    this.blockerTimeout = conf.getLong(ArcticMetaStoreConf.BLOCKER_TIMEOUT);
    LOG.info("table blocker service init with blockerTimeout {} ms", blockerTimeout);
  }

  /**
   * Get all valid blockers.
   *
   * @param tableIdentifier - table
   * @return all valid blockers
   */
  public List<BaseBlocker> getBlockers(TableIdentifier tableIdentifier) {
    Lock lock = getLock(tableIdentifier);
    lock.lock();
    try (SqlSession sqlSession = getSqlSession(true)) {
      TableBlockerMapper mapper = getMapper(sqlSession, TableBlockerMapper.class);
      List<TableBlocker> tableBlockers = mapper.selectBlockers(tableIdentifier, System.currentTimeMillis());
      return tableBlockers.stream()
          .map(this::buildBaseBlocker)
          .collect(Collectors.toList());
    } catch (Exception e) {
      LOG.error("failed to get blockers for {}", tableIdentifier, e);
      throw new IllegalStateException("failed to get blockers for " + tableIdentifier, e);
    } finally {
      lock.unlock();
    }
  }

  /**
   * Block some operations for table.
   *
   * @param tableIdentifier - table
   * @param operations      - operations to be blocked
   * @return BaseBlocker if success
   * @throws OperationConflictException when operations have been blocked
   */
  public BaseBlocker block(TableIdentifier tableIdentifier, List<BlockableOperation> operations)
      throws OperationConflictException {
    Preconditions.checkNotNull(operations, "operations should not be null");
    Preconditions.checkArgument(!operations.isEmpty(), "operations should not be empty");
    Lock lock = getLock(tableIdentifier);
    lock.lock();
    try (SqlSession sqlSession = getSqlSession(true)) {
      TableBlockerMapper mapper = getMapper(sqlSession, TableBlockerMapper.class);

      long now = System.currentTimeMillis();
      List<TableBlocker> tableBlockers = mapper.selectBlockers(tableIdentifier, now);
      if (conflict(operations, tableBlockers)) {
        throw new OperationConflictException(operations + " is conflict with " + tableBlockers);
      }
      TableBlocker tableBlocker = buildTableBlocker(tableIdentifier, operations, now);
      mapper.insertBlocker(tableBlocker);
      return buildBaseBlocker(tableBlocker);
    } catch (OperationConflictException operationConflictException) {
      throw operationConflictException;
    } catch (Exception e) {
      LOG.error("failed to block {} for {}", operations, tableIdentifier, e);
      throw new IllegalStateException("failed to block for " + tableIdentifier, e);
    } finally {
      lock.unlock();
    }
  }

  /**
   * Release blocker, succeed when blocker not exist.
   *
   * @param tableIdentifier - table
   * @param blockerId       - blockerId
   */
  public void release(TableIdentifier tableIdentifier, String blockerId) {
    Lock lock = getLock(tableIdentifier);
    lock.lock();
    try (SqlSession sqlSession = getSqlSession(true)) {
      TableBlockerMapper mapper = getMapper(sqlSession, TableBlockerMapper.class);
      mapper.deleteBlocker(Long.parseLong(blockerId));
    } catch (Exception e) {
      LOG.error("failed to release blocker {} for {}", blockerId, tableIdentifier, e);
      throw new IllegalStateException("failed to release blocker " + blockerId + " for " + tableIdentifier, e);
    } finally {
      lock.unlock();
    }
  }

  /**
   * Renew blocker.
   *
   * @param tableIdentifier - table
   * @param blockerId       - blockerId
   * @throws IllegalStateException if blocker not exist
   */
  public void renew(TableIdentifier tableIdentifier, String blockerId) {
    Lock lock = getLock(tableIdentifier);
    lock.lock();
    try (SqlSession sqlSession = getSqlSession(true)) {
      TableBlockerMapper mapper = getMapper(sqlSession, TableBlockerMapper.class);
      long now = System.currentTimeMillis();
      TableBlocker tableBlocker = mapper.selectBlocker(Long.parseLong(blockerId), now);
      if (tableBlocker == null) {
        throw new IllegalArgumentException("illegal blockerId " + blockerId + ", it may be released or expired");
      }
      mapper.updateBlockerExpirationTime(Long.parseLong(blockerId), now + blockerTimeout);
    } catch (Exception e) {
      LOG.error("failed to renew blocker {} for {}", blockerId, tableIdentifier, e);
      throw new IllegalStateException("failed to renew blockers for " + tableIdentifier, e);
    } finally {
      lock.unlock();
    }
  }

  /**
   * Check if operation are blocked now.
   *
   * @param tableIdentifier - table
   * @param operation       - operation to check
   * @return true if blocked
   */
  public boolean isBlocked(TableIdentifier tableIdentifier, BlockableOperation operation) {
    Lock lock = getLock(tableIdentifier);
    lock.lock();
    try (SqlSession sqlSession = getSqlSession(true)) {
      TableBlockerMapper mapper = getMapper(sqlSession, TableBlockerMapper.class);

      long now = System.currentTimeMillis();
      List<TableBlocker> tableBlockers = mapper.selectBlockers(tableIdentifier, now);
      return conflict(operation, tableBlockers);
    } catch (Exception e) {
      LOG.error("failed to check is blocked for {} {}", tableIdentifier, operation, e);
      throw new IllegalStateException("failed to check blocked for " + tableIdentifier + " " + operation, e);
    } finally {
      lock.unlock();
    }
  }

  private boolean conflict(List<BlockableOperation> blockableOperations, List<TableBlocker> blockers) {
    return blockableOperations.stream()
        .anyMatch(operation -> conflict(operation, blockers));
  }

  private boolean conflict(BlockableOperation blockableOperation, List<TableBlocker> blockers) {
    return blockers.stream()
        .anyMatch(blocker -> blocker.getOperations().contains(blockableOperation));
  }

  private BaseBlocker buildBaseBlocker(TableBlocker tableBlocker) {
    return new BaseBlocker(tableBlocker.getBlockerId() + "", tableBlocker.getOperations(), tableBlocker.getCreateTime(),
        tableBlocker.getExpirationTime(), tableBlocker.getProperties());
  }

  private TableBlocker buildTableBlocker(TableIdentifier tableIdentifier, List<BlockableOperation> operations,
                                         long now) {
    TableBlocker tableBlocker = new TableBlocker();
    tableBlocker.setTableIdentifier(tableIdentifier);
    tableBlocker.setCreateTime(now);
    tableBlocker.setExpirationTime(now + blockerTimeout);
    tableBlocker.setOperations(operations);
    return tableBlocker;
  }

  private Lock getLock(TableIdentifier tableIdentifier) {
    return TABLE_LOCK_MAP.computeIfAbsent(tableIdentifier, s -> new ReentrantLock());
  }
}
