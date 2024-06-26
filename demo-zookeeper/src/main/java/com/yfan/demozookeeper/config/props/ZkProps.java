package com.yfan.demozookeeper.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: Zookeeper配置
 * @author: YondFane 
 * @date: 2024/4/11/011 21:13 
 **/
@Data
@ConfigurationProperties(prefix = "zk")
public class ZkProps {
    /**
     * 连接地址
     */
    private String url;

    /**
     * 超时时间(毫秒)，默认1000
     */
    private int timeout = 1000;

    /**
     * 重试次数，默认3
     */
    private int retry = 3;
}
