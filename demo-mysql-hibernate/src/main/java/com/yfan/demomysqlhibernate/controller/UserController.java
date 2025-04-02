package com.yfan.demomysqlhibernate.controller;

import com.yfan.demomysqlhibernate.entity.User;
import com.yfan.demomysqlhibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demomysqlhibernate.controller
 * @Author: YANF
 * @CreateTime: 2025-03-27  15:00
 * @Description: 用户控制器
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public User findByName(String name) {
        return userService.findByName(name);
    }

}
