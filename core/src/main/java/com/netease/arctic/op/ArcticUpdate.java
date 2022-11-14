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

package com.netease.arctic.op;

import com.netease.arctic.AmsClient;
import com.netease.arctic.table.ArcticTable;
import com.netease.arctic.table.TableProperties;
import com.netease.arctic.table.UnkeyedTable;
import com.netease.arctic.table.WatermarkGenerator;
import com.netease.arctic.trace.TableTracer;
import com.netease.arctic.utils.TablePropertyUtil;
import org.apache.iceberg.DataFile;
import org.apache.iceberg.DeleteFile;
import org.apache.iceberg.PendingUpdate;
import org.apache.iceberg.Table;
import org.apache.iceberg.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Abstract implementation of {@link PendingUpdate}, adding arctic logics like tracing and watermark generating for
 * iceberg operations.
 *
 * @param <T> Java class of changes from this update; returned by {@link #apply} for validation.
 */
public abstract class ArcticUpdate<T> implements PendingUpdate<T> {

  private static final Logger LOG = LoggerFactory.getLogger(ArcticUpdate.class);

  private final ArcticTable arcticTable;
  private final TableTracer tracer;
  protected final Transaction transaction;
  protected final boolean autoCommitTransaction;
  protected final WatermarkGenerator watermarkGenerator;

  public ArcticUpdate(ArcticTable arcticTable, TableTracer tracer) {
    this.arcticTable = arcticTable;
    this.tracer = tracer;
    this.transaction = null;
    this.autoCommitTransaction = false;
    this.watermarkGenerator = null;
  }

  public ArcticUpdate(ArcticTable arcticTable, TableTracer tracer, Transaction transaction,
      boolean autoCommitTransaction) {
    this.arcticTable = arcticTable;
    this.tracer = tracer;
    this.transaction = transaction;
    this.autoCommitTransaction = autoCommitTransaction;
    WatermarkGenerator watermarkGenerator = null;
    try {
      watermarkGenerator = WatermarkGenerator.forTable(arcticTable);
    } catch (Exception e) {
      LOG.warn("Failed to initialize watermark generator", e);
    }
    this.watermarkGenerator = watermarkGenerator;
  }

  protected Optional<TableTracer> tracer() {
    if (tracer != null) {
      return Optional.of(tracer);
    } else {
      return Optional.empty();
    }
  }

  protected void addIcebergDataFile(DataFile file) {
    if (tracer != null) {
      tracer.addDataFile(file);
    }
    if (watermarkGenerator != null) {
      watermarkGenerator.addFile(file);
    }
  }

  protected void deleteIcebergDataFile(DataFile file) {
    if (tracer != null) {
      tracer.deleteDataFile(file);
    }
    if (watermarkGenerator != null) {
      watermarkGenerator.addFile(file);
    }
  }

  protected void addIcebergDeleteFile(DeleteFile file) {
    if (tracer != null) {
      tracer.addDeleteFile(file);
    }
    if (watermarkGenerator != null) {
      watermarkGenerator.addFile(file);
    }
  }

  protected void deleteIcebergDeleteFile(DeleteFile file) {
    if (tracer != null) {
      tracer.deleteDeleteFile(file);
    }
    if (watermarkGenerator != null) {
      watermarkGenerator.addFile(file);
    }
  }

  /**
   * Commit the real table operation.
   */
  public abstract void doCommit();

  @Override
  public void commit() {
    doCommit();
    if (transaction != null && watermarkGenerator != null) {
      long currentWatermark = TablePropertyUtil.getTableWatermark(arcticTable.properties());
      long newWatermark = watermarkGenerator.watermark();
      if (newWatermark > currentWatermark) {
        transaction.updateProperties().set(TableProperties.WATERMARK_TABLE, String.valueOf(newWatermark)).commit();
      }
    }
    if (transaction != null && autoCommitTransaction) {
      transaction.commitTransaction();
    }
    tracer.commit();
  }

  public abstract static class Builder<T> {

    protected final ArcticTable table;
    protected Table tableStore;
    protected TableTracer tableTracer;
    protected boolean onChangeStore = false;
    protected Transaction insideTransaction;
    protected boolean generateWatermark = false;

    protected Builder(ArcticTable table) {
      this.table = table;
    }

    public Builder<T> onChange() {
      this.onChangeStore = true;
      return this;
    }

    public Builder<T> onTableStore(Table tableStore) {
      this.tableStore = tableStore;
      return this;
    }

    public Builder<T> inTransaction(Transaction transaction) {
      this.insideTransaction = transaction;
      return this;
    }

    public Builder<T> generateWatermark() {
      this.generateWatermark = true;
      return this;
    }

    public Builder<T> traceTable(TableTracer tableTracer) {
      this.tableTracer = tableTracer;
      return this;
    }

    public abstract Builder<T> traceTable(AmsClient client, UnkeyedTable traceTable);

    protected Table getTableStore() {
      if (tableStore == null) {
        if (table.isKeyedTable()) {
          if (onChangeStore) {
            tableStore = table.asKeyedTable().changeTable();
          } else {
            tableStore = table.asKeyedTable().baseTable();
          }
        } else {
          tableStore = table.asUnkeyedTable();
        }
      }
      return tableStore;
    }

    public T build() {
      Table tableStore = getTableStore();
      if (generateWatermark) {
        if (insideTransaction != null) {
          return updateWithWatermark(tableTracer, insideTransaction, false);
        } else {
          Transaction transaction = tableStore.newTransaction();
          return updateWithWatermark(tableTracer, transaction, true);
        }
      } else {
        return updateWithoutWatermark(tableTracer, tableStore);
      }
    }

    protected abstract T updateWithWatermark(TableTracer tableTracer, Transaction transaction,
        boolean autoCommitTransaction);

    protected abstract T updateWithoutWatermark(TableTracer tableTracer, Table tableStore);
  }

}
