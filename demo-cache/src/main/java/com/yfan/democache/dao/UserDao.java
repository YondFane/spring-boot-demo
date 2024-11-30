package com.yfan.democache.dao;

import com.yfan.democache.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class UserDao {

    private Map<Long, User> data = new HashMap<>();

    {
        data.put(1L, new User(1L, "用户1"));
        data.put(2L, new User(2L, "用户2"));
        data.put(3L, new User(3L, "用户3"));
    }

    public User queryById(Long id) {
        if (id == null) {
            return null;
        }
        log.info("模拟数据库查询-id:{}", id);
        User user = data.get(id);
        log.info("模拟数据库查询到数据-user:{}", user);
        return user;
    }

    public User setUser(User user) {
        if (user == null || user.getId() == null) {
            log.error("user信息保存异常, user:{}", user);
            return null;
        }
        log.info("模拟数据库保存-user:{}", user);
        data.put(user.getId(), user);
        return user;
    }

}
