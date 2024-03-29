package com.yfan.demodubboconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yfan.demodubbocommon.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-30  00:25
 * @Description: Hello服务API
 * @Version: 1.0
 */
@Slf4j
@RestController
public class HelloController {

    @Reference
    private HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello() {
        log.info("i'm ready to call someone......");
        return helloService.sayHello("测试");
    }
}
