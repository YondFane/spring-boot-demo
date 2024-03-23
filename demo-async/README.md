# spring-boot-demo-async

> 此 demo 主要演示了 Spring Boot 如何使用原生提供的异步任务支持，实现异步执行任务。


## application.yml

```yaml
spring:
  task:
    execution:
      pool:
        # 最大线程数
        max-size: 16
        # 核心线程数
        core-size: 16
        # 存活时间
        keep-alive: 10s
        # 队列大小
        queue-capacity: 100
        # 是否允许核心线程超时
        allow-core-thread-timeout: true
      # 线程名称前缀
      thread-name-prefix: async-task-
```

## DemoAsyncApplication.java

```java
// 开启异步
@EnableAsync
@SpringBootApplication
public class DemoAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAsyncApplication.class, args);
    }

}

```

## TaskFactory.java

```java
package com.yfan.demoasync.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-23  15:44
 * @Description: 任务工厂
 * @Version: 1.0
 */
@Component
@Slf4j
public class TaskFactory {

    /**
     * 模拟5秒的异步任务
     */
    @Async
    public Future<Boolean> asyncTask1() throws InterruptedException {
        doTask("asyncTask1", 5);
        return new AsyncResult<>(Boolean.TRUE);
    }

    /**
     * 模拟2秒的异步任务
     */
    @Async
    public Future<Boolean> asyncTask2() throws InterruptedException {
        doTask("asyncTask2", 2);
        return new AsyncResult<>(Boolean.TRUE);
    }

    /**
     * 模拟3秒的异步任务
     */
    @Async
    public Future<Boolean> asyncTask3() throws InterruptedException {
        doTask("asyncTask3", 3);
        return new AsyncResult<>(Boolean.TRUE);
    }

    /**
     * 模拟5秒的同步任务
     */
    public void task1() throws InterruptedException {
        doTask("task1", 5);
    }

    /**
     * 模拟2秒的同步任务
     */
    public void task2() throws InterruptedException {
        doTask("task2", 2);
    }

    /**
     * 模拟3秒的同步任务
     */
    public void task3() throws InterruptedException {
        doTask("task3", 3);
    }

    private void doTask(String taskName, Integer time) throws InterruptedException {
        log.info("{}开始执行，当前线程名称【{}】", taskName, Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(time);
        log.info("{}执行成功，当前线程名称【{}】", taskName, Thread.currentThread().getName());
    }

}

```

## TaskFactoryTest.java

```java
package com.yfan.demoasync.controller;

import com.yfan.demoasync.task.TaskFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
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
        return "异步任务全部执行结束，总耗时：%s 毫秒".formatted((end - start));
    }

    @GetMapping("/task")
    public String task() throws InterruptedException {
        long start = System.currentTimeMillis();
        taskFactory.task1();
        taskFactory.task2();
        taskFactory.task3();
        long end = System.currentTimeMillis();

        log.info("同步任务全部执行结束，总耗时：{} 毫秒", (end - start));

        return "同步任务全部执行结束，总耗时：{} 毫秒".formatted((end - start));
    }
}

```

## 运行结果

### 异步任务

```bash
2024-03-23T16:10:19.199+08:00  INFO 18152 --- [nio-8080-exec-1] com.yfan.demoasync.task.TaskFactory      : task1开始执行，当前线程名称【http-nio-8080-exec-1】
2024-03-23T16:10:24.204+08:00  INFO 18152 --- [nio-8080-exec-1] com.yfan.demoasync.task.TaskFactory      : task1执行成功，当前线程名称【http-nio-8080-exec-1】
2024-03-23T16:10:24.204+08:00  INFO 18152 --- [nio-8080-exec-1] com.yfan.demoasync.task.TaskFactory      : task2开始执行，当前线程名称【http-nio-8080-exec-1】
2024-03-23T16:10:26.214+08:00  INFO 18152 --- [nio-8080-exec-1] com.yfan.demoasync.task.TaskFactory      : task2执行成功，当前线程名称【http-nio-8080-exec-1】
2024-03-23T16:10:26.214+08:00  INFO 18152 --- [nio-8080-exec-1] com.yfan.demoasync.task.TaskFactory      : task3开始执行，当前线程名称【http-nio-8080-exec-1】
2024-03-23T16:10:29.228+08:00  INFO 18152 --- [nio-8080-exec-1] com.yfan.demoasync.task.TaskFactory      : task3执行成功，当前线程名称【http-nio-8080-exec-1】
2024-03-23T16:10:29.228+08:00  INFO 18152 --- [nio-8080-exec-1] c.y.demoasync.controller.TestController  : 同步任务全部执行结束，总耗时：10029 毫秒
```

### 同步任务

```bash
2024-03-23T16:10:40.621+08:00  INFO 18152 --- [   async-task-1] com.yfan.demoasync.task.TaskFactory      : asyncTask1开始执行，当前线程名称【async-task-1】
2024-03-23T16:10:40.621+08:00  INFO 18152 --- [   async-task-2] com.yfan.demoasync.task.TaskFactory      : asyncTask2开始执行，当前线程名称【async-task-2】
2024-03-23T16:10:40.621+08:00  INFO 18152 --- [   async-task-3] com.yfan.demoasync.task.TaskFactory      : asyncTask3开始执行，当前线程名称【async-task-3】
2024-03-23T16:10:40.621+08:00  INFO 18152 --- [   async-task-4] com.yfan.demoasync.task.TaskFactory      : asyncTask1开始执行，当前线程名称【async-task-4】
2024-03-23T16:10:40.621+08:00  INFO 18152 --- [nio-8080-exec-2] c.y.demoasync.controller.TestController  : 异步任务全部执行结束，总耗时：2 毫秒
2024-03-23T16:10:40.621+08:00  INFO 18152 --- [   async-task-5] com.yfan.demoasync.task.TaskFactory      : asyncTask2开始执行，当前线程名称【async-task-5】
2024-03-23T16:10:40.621+08:00  INFO 18152 --- [   async-task-6] com.yfan.demoasync.task.TaskFactory      : asyncTask3开始执行，当前线程名称【async-task-6】
2024-03-23T16:10:42.633+08:00  INFO 18152 --- [   async-task-2] com.yfan.demoasync.task.TaskFactory      : asyncTask2执行成功，当前线程名称【async-task-2】
2024-03-23T16:10:42.633+08:00  INFO 18152 --- [   async-task-5] com.yfan.demoasync.task.TaskFactory      : asyncTask2执行成功，当前线程名称【async-task-5】
2024-03-23T16:10:43.632+08:00  INFO 18152 --- [   async-task-6] com.yfan.demoasync.task.TaskFactory      : asyncTask3执行成功，当前线程名称【async-task-6】
2024-03-23T16:10:43.632+08:00  INFO 18152 --- [   async-task-3] com.yfan.demoasync.task.TaskFactory      : asyncTask3执行成功，当前线程名称【async-task-3】
2024-03-23T16:10:45.631+08:00  INFO 18152 --- [   async-task-4] com.yfan.demoasync.task.TaskFactory      : asyncTask1执行成功，当前线程名称【async-task-4】
2024-03-23T16:10:45.631+08:00  INFO 18152 --- [   async-task-1] com.yfan.demoasync.task.TaskFactory      : asyncTask1执行成功，当前线程名称【async-task-1】
```
