package com.yfan.demomysqlmybatis.service;

import com.yfan.demomysqlmybatis.entity.User;
import com.yfan.demomysqlmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demomysqlmybatis.service
 * @Author: YANF
 * @CreateTime: 2024-12-01  21:53
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listAll() {
        List<User> users = userMapper.listAll();
        User user = users.get(0);
        user.setName("测试100");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User queryById(Integer id) {
        User user = userMapper.queryById(id);
        user.setName("TEST");

        return user;
    }
}
