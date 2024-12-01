package com.yfan.demomysqlmybatis.controller;

import com.yfan.demomysqlmybatis.entity.User;
import com.yfan.demomysqlmybatis.mapper.UserMapper;
import com.yfan.demomysqlmybatis.service.UserService;
import io.swagger.annotations.ApiOperation;
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
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/listAll")
    @ApiOperation("查询所有用户")
    public List<User> listAll(){
        return userService.listAll();
    }

    @GetMapping("/listAll2")
    @ApiOperation("查询所有用户2")
    public List<User> listAll2(){
        return userMapper.listAll();
    }

    @GetMapping("/queryById")
    @ApiOperation("根据ID查询用户")
    public User query(Integer id){
        return userService.queryById(id);
    }

}
