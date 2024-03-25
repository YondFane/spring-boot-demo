package com.yfan.demoelasticsearch.service;

import com.yfan.demoelasticsearch.dao.UserEntityRespository;
import com.yfan.demoelasticsearch.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-25  21:13
 * @Description: User业务层
 * @Version: 1.0
 */
@Service
@Slf4j
public class UserEntityService {

    @Autowired
    private UserEntityRespository userEntityRespository;


    public UserEntity save(UserEntity user) {
        return userEntityRespository.save(user);
    }

    public List<UserEntity> queryAll() {
        Iterable<UserEntity> entityIterable = userEntityRespository.findAll();
        List<UserEntity> list = new ArrayList<>();
        entityIterable.forEach(user -> list.add(user));
        return list;
    }

}
