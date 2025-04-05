package com.yfan.demoredisson.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demoredisson.controller
 * @Author: YANF
 * @CreateTime: 2024-12-01  23:01
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@Slf4j
public class TestController {


    @GetMapping("/test")
    public String test(){
        log.info("test-{}", Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello World";
    }

}
