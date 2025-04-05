package com.yfan.demosecurity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demosecurity.request
 * @Author: YANF
 * @CreateTime: 2025-04-05  22:00
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class LoginRequest {

    /**
     * 用户名或邮箱或手机号
     */
    @NotBlank(message = "用户名不能为空")
    private String usernameOrEmailOrPhone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe = false;

}