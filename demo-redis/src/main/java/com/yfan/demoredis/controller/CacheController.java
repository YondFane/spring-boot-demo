package com.yfan.demoredis.controller;

import com.yfan.demoredis.dto.User;
import com.yfan.demoredis.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/get")
    public User get(@RequestParam("id") Long id) {
        return cacheService.get(id);
    }

    @PostMapping("/set")
    public User set(@RequestBody User user) {
        return cacheService.set(user);
    }
}
