package com.yfan.democache.service;

import com.yfan.democache.dto.User;
import org.springframework.cache.annotation.Cacheable;

public interface CacheServiceI {

    @Cacheable(value = "myCache", key = "#id")
    User get(Long id);

    User set(User user);
}
