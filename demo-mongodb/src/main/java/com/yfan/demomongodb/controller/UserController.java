package com.yfan.demomongodb.controller;

import com.yfan.demomongodb.entity.User;
import com.yfan.demomongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-26  21:40
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "success";
    }

    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }
}
