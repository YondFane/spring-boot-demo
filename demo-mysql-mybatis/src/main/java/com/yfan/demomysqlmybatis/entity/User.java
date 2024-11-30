package com.yfan.demomysqlmybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demomysqlmybatis.entity
 * @Author: YANF
 * @CreateTime: 2024-11-30  13:57
 * @Description: TODO
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
