package com.yfan.demomqrabbitmq.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 测试消息体
 * </p>
 *
 * @author YondFane
 * @date 2018-12-29 16:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageStruct implements Serializable {
    private static final long serialVersionUID = 392365881428311040L;

    private String message;
}
