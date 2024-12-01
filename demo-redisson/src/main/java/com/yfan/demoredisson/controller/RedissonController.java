package com.yfan.demoredisson.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demoredisson.controller
 * @Author: YANF
 * @CreateTime: 2024-12-01  22:24
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/redisson")
@Slf4j
public class RedissonController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/lock")
    public String lock() {
        log.info("get-lock:{}", Thread.currentThread().getName());
        RLock lock = redissonClient.getLock("myLock");
        try {
            // 尝试加锁，最多等待0秒，锁定后最多持有锁10秒
            boolean isLocked = lock.tryLock(0, 10, TimeUnit.SECONDS);
            log.info("lock:{}", isLocked);
            if (isLocked) {
                Thread.sleep(10000);
                // 业务逻辑
                return "Lock acquired";
            }
            return "Lock not acquired";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Error";
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
