package com.yfan.demosecurity.controller;

import com.yfan.demosecurity.entity.User;
import com.yfan.demosecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demosecurity.controller
 * @Author: YANF
 * @CreateTime: 2025-03-27  13:06
 * @Description: 用户控制层
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
