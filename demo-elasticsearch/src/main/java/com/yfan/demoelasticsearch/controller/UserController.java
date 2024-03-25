package com.yfan.demoelasticsearch.controller;

import com.yfan.demoelasticsearch.entity.UserEntity;
import com.yfan.demoelasticsearch.service.UserEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-25  21:04
 * @Description: User控制层
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/list")
    public List<UserEntity> list() {
        return userEntityService.queryAll();
    }

    @PostMapping("/save")
    public Object save(@RequestBody UserEntity user) {
        return userEntityService.save(user);
    }

}
