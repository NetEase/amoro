package com.netease.arctic.table.blocker;

import com.netease.arctic.TableTestHelpers;
import com.netease.arctic.ams.api.BlockableOperation;
import com.netease.arctic.ams.api.OperationConflictException;
import com.netease.arctic.ams.api.properties.TableFormat;
import com.netease.arctic.catalog.TableTestBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BaseTableBlockerManagerTest extends TableTestBase {

  private static final List<BlockableOperation> OPERATIONS = new ArrayList<>();

  static {
    OPERATIONS.add(BlockableOperation.OPTIMIZE);
    OPERATIONS.add(BlockableOperation.BATCH_WRITE);
  }

  public BaseTableBlockerManagerTest() {
    super(TableFormat.MIXED_ICEBERG, true, true);
  }

  @Test
  public void testBlockAndRelease() throws OperationConflictException {
    TableBlockerManager tableBlockerManager = getCatalog().getTableBlockerManager(TableTestHelpers.TEST_TABLE_ID);
    Assert.assertTrue(tableBlockerManager instanceof BaseTableBlockerManager);
    BaseTableBlockerManager blockerManager = (BaseTableBlockerManager) tableBlockerManager;
    assertStopped(blockerManager);

    Blocker block = blockerManager.block(OPERATIONS);
    assertRunning(blockerManager);

    blockerManager.release(block);
    assertStopped(blockerManager);
  }

  public void assertRunning(BaseTableBlockerManager blockerManager) {
    Assert.assertNotNull(blockerManager.getRenewWorker());
    Assert.assertFalse(blockerManager.isStop());
    Assert.assertFalse(blockerManager.getToRenewBlockers().isEmpty());
  }

  public void assertStopped(BaseTableBlockerManager blockerManager) {
    Assert.assertNull(blockerManager.getRenewWorker());
    Assert.assertTrue(blockerManager.isStop());
    Assert.assertTrue(blockerManager.getToRenewBlockers().isEmpty());
  }
}