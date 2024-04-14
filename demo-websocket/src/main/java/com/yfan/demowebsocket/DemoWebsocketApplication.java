package com.yfan.demowebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebsocketApplication.class, args);
    }

}
