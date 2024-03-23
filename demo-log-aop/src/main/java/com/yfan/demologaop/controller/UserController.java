package com.yfan.demologaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-23  14:56
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
