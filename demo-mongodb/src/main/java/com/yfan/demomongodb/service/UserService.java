package com.yfan.demomongodb.service;

import com.yfan.demomongodb.dao.UserDao;
import com.yfan.demomongodb.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-26  21:42
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDao userDao;

    public User save(User user) {
        return userDao.save(user);
    }

    public List<User> list() {
        return userDao.findAll();
    }
}
