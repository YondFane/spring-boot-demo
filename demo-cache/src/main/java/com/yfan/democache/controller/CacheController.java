package com.yfan.democache.controller;

import com.yfan.democache.dto.User;
import com.yfan.democache.service.CacheServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheServiceI cacheServiceI;

    @GetMapping("/get")
    public User get(@RequestParam("id") Long id) {
        return cacheServiceI.get(id);
    }

    @PostMapping("/set")
    public User set(@RequestBody User user) {
        return cacheServiceI.set(user);
    }
}
