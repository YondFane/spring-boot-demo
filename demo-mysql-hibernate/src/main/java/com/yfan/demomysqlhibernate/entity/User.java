package com.yfan.demomysqlhibernate.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-21  23:18
 * @Description: 用户实体
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    private Long id;

    private String name;

    private Integer age;

    private Integer sex;

    private Long deposit;

    private Long version;

}