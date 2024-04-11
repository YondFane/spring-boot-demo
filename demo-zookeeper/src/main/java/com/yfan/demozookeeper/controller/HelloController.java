package com.yfan.demozookeeper.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: YondFane
 * @CreateTime: 2024-04-11  22:05
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/demo")
public class HelloController {


    @Autowired
    private CuratorFramework zkClient;

    @GetMapping("/hello")
    public String hello() throws Exception {
        InterProcessMutex lock = new InterProcessMutex(zkClient, "/hello");
        try {
            // 假设上锁成功，以后拿到的都是
            if (lock.acquire(100, TimeUnit.MILLISECONDS)) {
                log.info("获取锁成功1-----");
                Thread.sleep(10000);
                log.info("获取锁成功2-----");
            } else {
                throw new RuntimeException("请勿重复提交");
            }
        } catch (Exception e) {
            log.error("获取锁异常", e);
        } finally {
            lock.release();
            log.info("释放锁成功-----");
        }
        return "hello";
    }

}
