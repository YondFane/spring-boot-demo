package com.yfan.demodubbocommon.service;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-30  00:23
 * @Description: Hello服务接口
 * @Version: 1.0
 */

public interface HelloService {
    /**
     * 问好
     *
     * @param name 姓名
     * @return 问好
     */
    String sayHello(String name);
}
