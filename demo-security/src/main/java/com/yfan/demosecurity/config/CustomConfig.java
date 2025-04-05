package com.yfan.demosecurity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 自定义配置
 * </p>
 *
 * @author YondFane
 * @date Created in 2018-12-13 10:56
 */
@ConfigurationProperties(prefix = "custom.config")
@Component
@Data
public class CustomConfig {
    /**
     * 不需要拦截的地址
     */
    private IgnoreConfig ignores;
}
