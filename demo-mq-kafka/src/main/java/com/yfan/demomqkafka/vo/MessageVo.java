package com.yfan.demomqkafka.vo;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: YondFane
 * @CreateTime: 2024-04-08  21:40
 * @Description: 消息VO
 * @Version: 1.0
 */
@Setter
@Getter
@Builder
public class MessageVo {

    private Integer id;

    private String name;

}
