package com.yfan.demomysqlmybatis.controller;

import com.yfan.demomysqlmybatis.entity.User;
import com.yfan.demomysqlmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demomysqlmybatis.controller
 * @Author: YANF
 * @CreateTime: 2024-11-30  14:07
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/listAll")
    public List<User> listAll(){
        return userMapper.listAll();
    }

}
