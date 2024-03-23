package com.yfan.demoasync.task;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-23  15:47
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@SpringBootTest
public class TestTask {

    @Autowired
    private TaskFactory task;

    /**
     * 测试异步任务
     */
    @Test
    public void asyncTaskTest() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        Future<Boolean> asyncTask1 = task.asyncTask1();
        Future<Boolean> asyncTask2 = task.asyncTask2();
        Future<Boolean> asyncTask3 = task.asyncTask3();

        Future<Boolean> asyncTask4 = task.asyncTask1();
        Future<Boolean> asyncTask5 = task.asyncTask2();
        Future<Boolean> asyncTask6 = task.asyncTask3();

        // 调用 get() 阻塞主线程
//        asyncTask1.get();
//        asyncTask2.get();
//        asyncTask3.get();
        long end = System.currentTimeMillis();

        log.info("异步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }

    /**
     * 测试同步任务
     */
    @Test
    public void taskTest() throws InterruptedException {
        long start = System.currentTimeMillis();
        task.task1();
        task.task2();
        task.task3();
        long end = System.currentTimeMillis();

        log.info("同步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }
}
