package com.yfan.demosecurity.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * JWT 配置
 * </p>
 *
 * @author YondFane
 * @date Created in 2025-04-06 13:42
 */
@ConfigurationProperties(prefix = "jwt.config")
@Component
@Data
public class JwtConfig {
    /**
     * jwt 加密 key，默认值：key.
     */
    private String key = "key";

    /**
     * jwt 过期时间，默认值：600000 {@code 10 分钟}.
     */
    private Long ttl = 600000L;

    /**
     * 开启 记住我 之后 jwt 过期时间，默认值 604800000 {@code 7 天}
     */
    private Long remember = 604800000L;
}
