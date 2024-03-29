package com.yfan.demodubboprovider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yfan.demodubbocommon.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-30  00:21
 * @Description: Hello服务实现
 * @Version: 1.0
 */
@Slf4j
@Service
@Component
public class HelloServiceImpl implements HelloService {

    /**
     * 问好
     *
     * @param name 姓名
     * @return 问好
     */
    @Override
    public String sayHello(String name) {
        log.info("someone is calling me......");
        return "say hello to: " + name;
    }

}
