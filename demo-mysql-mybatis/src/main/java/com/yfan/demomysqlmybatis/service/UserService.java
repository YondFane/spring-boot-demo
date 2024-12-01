package com.yfan.demomysqlmybatis.service;

import com.yfan.demomysqlmybatis.entity.User;

import java.util.List;

public interface UserService {

    List<User> listAll();

    User queryById(Integer id);
}
