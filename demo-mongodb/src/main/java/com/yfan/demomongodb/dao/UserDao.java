package com.yfan.demomongodb.dao;

import com.yfan.demomongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-26  21:41
 * @Description: TODO
 * @Version: 1.0
 */
@Repository
public interface UserDao extends MongoRepository<User, String> {

}
