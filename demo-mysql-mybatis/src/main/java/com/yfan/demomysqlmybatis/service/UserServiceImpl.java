package com.yfan.demomysqlmybatis.service;

import com.yfan.demomysqlmybatis.entity.User;
import com.yfan.demomysqlmybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listAll() {
        List<User> users = userMapper.listAll();
        User user = users.get(0);
        user.setName("测试100");
        try {
            log.info("{}-sleep-start", Thread.currentThread().getName());
            Thread.sleep(3000);
            log.info("{}-sleep-end", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("listAll-users:{}", users);
        return users;
    }

    @Override
    public User queryById(Integer id) {
        User user = userMapper.queryById(id);
        user.setName("TEST");

        return user;
    }
}
