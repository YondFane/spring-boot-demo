package com.yfan.demoasync.controller;

import com.yfan.demoasync.task.TaskFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-23  15:57
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private TaskFactory taskFactory;


    @GetMapping("/asyncTask")
    public String asyncTask() throws InterruptedException {
        long start = System.currentTimeMillis();
        Future<Boolean> asyncTask1 = taskFactory.asyncTask1();
        Future<Boolean> asyncTask2 = taskFactory.asyncTask2();
        Future<Boolean> asyncTask3 = taskFactory.asyncTask3();

        Future<Boolean> asyncTask4 = taskFactory.asyncTask1();
        Future<Boolean> asyncTask5 = taskFactory.asyncTask2();
        Future<Boolean> asyncTask6 = taskFactory.asyncTask3();

        // 调用 get() 阻塞主线程
//        asyncTask1.get();
//        asyncTask2.get();
//        asyncTask3.get();
        long end = System.currentTimeMillis();

        log.info("异步任务全部执行结束，总耗时：{} 毫秒", (end - start));
        return "异步任务全部执行结束，总耗时："+(end - start)+"毫秒";
    }

    @GetMapping("/task")
    public String task() throws InterruptedException {
        long start = System.currentTimeMillis();
        taskFactory.task1();
        taskFactory.task2();
        taskFactory.task3();
        long end = System.currentTimeMillis();

        log.info("同步任务全部执行结束，总耗时：{} 毫秒", (end - start));

        return "同步任务全部执行结束，总耗时："+(end - start)+"毫秒";
    }
}
