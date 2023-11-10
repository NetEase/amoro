package com.netease.arctic.server;

import com.netease.arctic.BasicTableTestHelper;
import com.netease.arctic.TableTestHelper;
import com.netease.arctic.ams.api.OptimizerRegisterInfo;
import com.netease.arctic.ams.api.OptimizingTask;
import com.netease.arctic.ams.api.OptimizingTaskId;
import com.netease.arctic.ams.api.OptimizingTaskResult;
import com.netease.arctic.ams.api.TableFormat;
import com.netease.arctic.catalog.BasicCatalogTestHelper;
import com.netease.arctic.catalog.CatalogTestHelper;
import com.netease.arctic.io.MixedDataTestHelpers;
import com.netease.arctic.optimizing.RewriteFilesOutput;
import com.netease.arctic.optimizing.TableOptimizing;
import com.netease.arctic.server.exception.IllegalTaskStateException;
import com.netease.arctic.server.exception.PluginRetryAuthException;
import com.netease.arctic.server.optimizing.OptimizingProcess;
import com.netease.arctic.server.optimizing.OptimizingStatus;
import com.netease.arctic.server.optimizing.TaskRuntime;
import com.netease.arctic.server.resource.OptimizerInstance;
import com.netease.arctic.server.table.AMSTableTestBase;
import com.netease.arctic.server.table.TableRuntime;
import com.netease.arctic.server.table.executor.TableRuntimeRefreshExecutor;
import com.netease.arctic.table.ArcticTable;
import com.netease.arctic.table.UnkeyedTable;
import com.netease.arctic.utils.SerializationUtil;
import org.apache.iceberg.AppendFiles;
import org.apache.iceberg.DataFile;
import org.apache.iceberg.data.Record;
import org.apache.iceberg.relocated.com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class TestDefaultOptimizingService extends AMSTableTestBase {

  private final int THREAD_ID = 0;
  private String token;
  private Toucher toucher;

  @Parameterized.Parameters(name = "{0}, {1}")
  public static Object[] parameters() {
    return new Object[][] {
      {new BasicCatalogTestHelper(TableFormat.ICEBERG), new BasicTableTestHelper(false, true)}
    };
  }

  public TestDefaultOptimizingService(
      CatalogTestHelper catalogTestHelper, TableTestHelper tableTestHelper) {
    super(catalogTestHelper, tableTestHelper, false);
  }

  @Before
  public void prepare() {
    token = optimizingService().authenticate(buildRegisterInfo());
    toucher = new Toucher();
    createDatabase();
    createTable();
    initTableWithFiles();
    TableRuntimeRefresher refresher = new TableRuntimeRefresher();
    refresher.refreshPending();
    refresher.dispose();
  }

  @After
  public void clear() {
    try {
      optimizingService()
          .listOptimizers()
          .forEach(
              optimizer ->
                  optimizingService()
                      .deleteOptimizer(optimizer.getGroupName(), optimizer.getResourceId()));
      dropTable();
      dropDatabase();
      if (toucher != null) {
        toucher.stop();
        toucher = null;
      }
    } catch (Exception e) {
      // ignore
    }
  }

  private void initTableWithFiles() {
    ArcticTable arcticTable =
        (ArcticTable) tableService().loadTable(serverTableIdentifier()).originalTable();
    appendData(arcticTable.asUnkeyedTable(), 1);
    appendData(arcticTable.asUnkeyedTable(), 2);
    TableRuntime runtime = tableService().getRuntime(serverTableIdentifier());

    runtime.refresh(tableService().loadTable(serverTableIdentifier()));
  }

  private void appendData(UnkeyedTable table, int id) {
    ArrayList<Record> newRecords =
        Lists.newArrayList(
            MixedDataTestHelpers.createRecord(
                table.schema(), id, "111", 0L, "2022-01-01T12:00:00"));
    List<DataFile> dataFiles = MixedDataTestHelpers.writeBaseStore(table, 0L, newRecords, false);
    AppendFiles appendFiles = table.newAppend();
    dataFiles.forEach(appendFiles::appendFile);
    appendFiles.commit();
  }

  @Test
  public void testPollWithoutAuth() {
    // 1.poll task
    clear();
    Assertions.assertThrows(
        PluginRetryAuthException.class, () -> optimizingService().pollTask("whatever", THREAD_ID));
  }

  @Test
  public void testPollOnce() {
    // 1.poll task
    OptimizingTask task = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertNotNull(task);
    assertTaskStatus(TaskRuntime.Status.SCHEDULED);
    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());
    assertTaskStatus(TaskRuntime.Status.ACKED);

    TaskRuntime taskRuntime =
        optimizingService().listTasks(defaultResourceGroup().getName()).get(0);
    optimizingService().completeTask(token, buildOptimizingTaskResult(task.getTaskId()));
    assertTaskCompleted(taskRuntime);
  }

  @Test
  public void testPollTaskTwice() {
    // 1.poll task
    OptimizingTask task = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertNotNull(task);

    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());
    // 3.fail task
    optimizingService()
        .completeTask(token, buildOptimizingTaskFailResult(task.getTaskId(), "unknown error"));
    assertTaskStatus(TaskRuntime.Status.PLANNED);

    // 4.retry poll task
    OptimizingTask task2 = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertEquals(task2, task);
    assertTaskStatus(TaskRuntime.Status.SCHEDULED);
    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());
    assertTaskStatus(TaskRuntime.Status.ACKED);

    TaskRuntime taskRuntime =
        optimizingService().listTasks(defaultResourceGroup().getName()).get(0);
    optimizingService().completeTask(token, buildOptimizingTaskResult(task.getTaskId()));
    assertTaskCompleted(taskRuntime);
  }

  @Test
  public void testPollTaskThreeTimes() {
    // 1.poll task
    OptimizingTask task = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertNotNull(task);
    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());

    // 3.fail task
    optimizingService()
        .completeTask(token, buildOptimizingTaskFailResult(task.getTaskId(), "unknown error"));

    // 4.retry poll task
    OptimizingTask task2 = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertEquals(task2, task);

    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());
    optimizingService()
        .completeTask(token, buildOptimizingTaskFailResult(task.getTaskId(), "unknown error"));

    // retry again
    OptimizingTask task3 = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertEquals(task3, task);
    assertTaskStatus(TaskRuntime.Status.SCHEDULED);
    // third time would be null
    Assertions.assertNull(optimizingService().pollTask(token, THREAD_ID));
    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());
    assertTaskStatus(TaskRuntime.Status.ACKED);

    TaskRuntime taskRuntime =
        optimizingService().listTasks(defaultResourceGroup().getName()).get(0);
    optimizingService().completeTask(token, buildOptimizingTaskResult(task.getTaskId()));
    assertTaskCompleted(taskRuntime);
  }

  @Test
  public void testTouch() throws InterruptedException {
    OptimizerInstance optimizer = optimizingService().listOptimizers().get(0);
    long oldTouchTime = optimizer.getTouchTime();
    Thread.sleep(1);
    optimizingService().touch(token);
    Assertions.assertTrue(optimizer.getTouchTime() > oldTouchTime);
  }

  @Test
  public void testTouchTimeout() throws InterruptedException {
    toucher.stop();
    toucher = null;
    OptimizingTask task = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertNotNull(task);
    Thread.sleep(1000);
    Assertions.assertThrows(PluginRetryAuthException.class, () -> optimizingService().touch(token));
    Assertions.assertThrows(
        PluginRetryAuthException.class, () -> optimizingService().pollTask(token, THREAD_ID));
    assertTaskStatus(TaskRuntime.Status.PLANNED);
    token = optimizingService().authenticate(buildRegisterInfo());
    OptimizingTask task2 = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertEquals(task2, task);
  }

  @Test
  public void testAckAndCompleteTask() {
    OptimizingTask task = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertNotNull(task);
    Assertions.assertThrows(
        IllegalTaskStateException.class,
        () -> optimizingService().completeTask(token, buildOptimizingTaskResult(task.getTaskId())));

    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());

    TaskRuntime taskRuntime =
        optimizingService().listTasks(defaultResourceGroup().getName()).get(0);
    optimizingService().completeTask(token, buildOptimizingTaskResult(task.getTaskId()));
    assertTaskCompleted(taskRuntime);
  }

  @Test
  public void testReloadScheduledTask() {
    // 1.poll task
    OptimizingTask task = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertNotNull(task);

    reload();
    assertTaskStatus(TaskRuntime.Status.SCHEDULED);
    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());

    TaskRuntime taskRuntime =
        optimizingService().listTasks(defaultResourceGroup().getName()).get(0);
    optimizingService().completeTask(token, buildOptimizingTaskResult(task.getTaskId()));
    assertTaskCompleted(taskRuntime);
  }

  @Test
  public void testReloadAckTask() {
    // 1.poll task
    OptimizingTask task = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertNotNull(task);
    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());

    reload();
    assertTaskStatus(TaskRuntime.Status.ACKED);

    TaskRuntime taskRuntime =
        optimizingService().listTasks(defaultResourceGroup().getName()).get(0);
    optimizingService().completeTask(token, buildOptimizingTaskResult(task.getTaskId()));
    assertTaskCompleted(taskRuntime);
  }

  @Test
  public void testReloadCompletedTask() {
    // THREAD_ID.poll task
    OptimizingTask task = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertNotNull(task);
    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());
    optimizingService().completeTask(token, buildOptimizingTaskResult(task.getTaskId()));

    reload();
    assertTaskCompleted(null);
    Assertions.assertNull(optimizingService().pollTask(token, THREAD_ID));
  }

  @Test
  public void testReloadFailedTask() {
    // 1.poll task
    OptimizingTask task = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertNotNull(task);
    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());
    optimizingService()
        .completeTask(token, buildOptimizingTaskFailResult(task.getTaskId(), "error"));

    reload();
    assertTaskStatus(TaskRuntime.Status.PLANNED);

    OptimizingTask task2 = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertEquals(task2.getTaskId(), task.getTaskId());
    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());
    optimizingService()
        .completeTask(token, buildOptimizingTaskFailResult(task.getTaskId(), "error"));

    reload();
    assertTaskStatus(TaskRuntime.Status.PLANNED);

    OptimizingTask task3 = optimizingService().pollTask(token, THREAD_ID);
    Assertions.assertEquals(task3.getTaskId(), task.getTaskId());
    optimizingService().ackTask(token, THREAD_ID, task.getTaskId());
    optimizingService()
        .completeTask(token, buildOptimizingTaskFailResult(task.getTaskId(), "error"));

    Assertions.assertEquals(
        optimizingService().listTasks(defaultResourceGroup().getName()).get(0).getFailReason(),
        "error");
  }

  private OptimizerRegisterInfo buildRegisterInfo() {
    OptimizerRegisterInfo registerInfo = new OptimizerRegisterInfo();
    registerInfo.setThreadCount(1);
    registerInfo.setMemoryMb(1024);
    registerInfo.setGroupName(defaultResourceGroup().getName());
    registerInfo.setResourceId("1");
    registerInfo.setStartTime(System.currentTimeMillis());
    return registerInfo;
  }

  private OptimizingTaskResult buildOptimizingTaskResult(OptimizingTaskId taskId) {
    TableOptimizing.OptimizingOutput output = new RewriteFilesOutput(null, null, null);
    OptimizingTaskResult optimizingTaskResult = new OptimizingTaskResult(taskId, THREAD_ID);
    optimizingTaskResult.setTaskOutput(SerializationUtil.simpleSerialize(output));
    return optimizingTaskResult;
  }

  private OptimizingTaskResult buildOptimizingTaskFailResult(
      OptimizingTaskId taskId, String errorMessage) {
    TableOptimizing.OptimizingOutput output = new RewriteFilesOutput(null, null, null);
    OptimizingTaskResult optimizingTaskResult = new OptimizingTaskResult(taskId, THREAD_ID);
    optimizingTaskResult.setTaskOutput(SerializationUtil.simpleSerialize(output));
    optimizingTaskResult.setErrorMessage(errorMessage);
    return optimizingTaskResult;
  }

  private void assertTaskStatus(TaskRuntime.Status expectedStatus) {
    Assertions.assertEquals(
        expectedStatus,
        optimizingService().listTasks(defaultResourceGroup().getName()).get(0).getStatus());
  }

  private void assertTaskCompleted(TaskRuntime taskRuntime) {
    if (taskRuntime != null) {
      Assertions.assertEquals(TaskRuntime.Status.SUCCESS, taskRuntime.getStatus());
    }
    Assertions.assertEquals(
        0, optimizingService().listTasks(defaultResourceGroup().getName()).size());
    Assertions.assertEquals(
        OptimizingProcess.Status.RUNNING,
        tableService().getRuntime(serverTableIdentifier()).getOptimizingProcess().getStatus());
    Assertions.assertEquals(
        OptimizingStatus.COMMITTING,
        tableService().getRuntime(serverTableIdentifier()).getOptimizingStatus());
  }

  protected static void reload() {
    disposeTableService();
    initTableService();
  }

  private class TableRuntimeRefresher extends TableRuntimeRefreshExecutor {

    public TableRuntimeRefresher() {
      super(tableService(), 1, Integer.MAX_VALUE);
    }

    void refreshPending() {
      execute(tableService().getRuntime(serverTableIdentifier()));
    }
  }

  private class Toucher implements Runnable {

    private volatile boolean stop = false;
    private final Thread thread = new Thread(this);

    public Toucher() {
      thread.setDaemon(true);
      thread.start();
    }

    public void stop() {
      stop = true;
      thread.interrupt();
    }

    @Override
    public void run() {
      while (!stop) {
        try {
          optimizingService().touch(token);
          Thread.sleep(300);
        } catch (Throwable ignored) {
        }
      }
    }
  }
}