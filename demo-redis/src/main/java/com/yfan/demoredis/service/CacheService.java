package com.yfan.demoredis.service;


import com.yfan.demoredis.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheService {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public User get(Long id) {
        User user = (User) redisTemplate.opsForValue().get(id.toString());
        return user;
    }

    public User set(User user) {
        log.info("user:{}", user);
        redisTemplate.opsForValue().set(user.getId().toString(), user);
        return user;
    }
}
