package com.yfan.demomysqlhibernate.service;

import com.yfan.demomysqlhibernate.entity.User;
import com.yfan.demomysqlhibernate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demomysqlhibernate.service
 * @Author: YANF
 * @CreateTime: 2025-04-02  20:04
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

}
