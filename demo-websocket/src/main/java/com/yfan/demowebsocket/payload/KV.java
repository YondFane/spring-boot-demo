package com.yfan.demowebsocket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 键值匹配
 * </p>
 *
 * @author YondFane
 * @date 2024-04-14 17:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KV {
    /**
     * 键
     */
    private String key;

    /**
     * 值
     */
    private Object value;
}
