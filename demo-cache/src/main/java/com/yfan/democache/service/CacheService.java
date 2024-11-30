package com.yfan.democache.service;


import com.yfan.democache.dao.UserDao;
import com.yfan.democache.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheService implements CacheServiceI {

    @Autowired
    private UserDao userDao;

    @Cacheable(value = "myCache")
    public User get(Long id) {
        return userDao.queryById(id);
    }

    @CachePut(value = "myCache", key = "#user.id")
    public User set(User user) {
        log.info("user:{}", user);
        return userDao.setUser(user);
    }


}
