package com.yfan.demosecurity.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demosecurity.entity
 * @Author: YANF
 * @CreateTime: 2025-03-27  13:02
 * @Description: 用户信息
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

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "权限类型")
    private String role;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别，1男，0女")
    private Integer sex;

    @ApiModelProperty(value = "存款")
    private Long deposit;

    @Version
    private Long version;

}
