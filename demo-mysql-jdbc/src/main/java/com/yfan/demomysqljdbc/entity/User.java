package com.yfan.demomysqljdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-21  23:18
 * @Description: test实体
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String name;

    private Integer age;

    private Integer sex;

    private Long deposit;

    private Long version;

}