package com.yfan.demodubboprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class DemoDubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDubboProviderApplication.class, args);
    }

}
