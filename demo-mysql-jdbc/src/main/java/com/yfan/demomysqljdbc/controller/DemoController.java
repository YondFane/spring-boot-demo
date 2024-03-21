package com.yfan.demomysqljdbc.controller;

import com.yfan.demomysqljdbc.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-21  23:07
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/mysql-jdbc")
public class DemoController {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/query")
    @Operation(summary = "简单JDBC查询")
    public Object query(User user){
        // 简单JDBC查询
        String sql = "SELECT * FROM test where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User result = jdbcTemplate.queryForObject(sql, new Object[]{user.getId()}, rowMapper);
        return result;
    }

    @GetMapping("/queryList")
    @Operation(summary = "简单JDBC查询列表")
    public Object queryList(){
        // 简单JDBC查询列表
        String sql = "SELECT * FROM test";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        List<User> result = jdbcTemplate.query(sql, rowMapper);
        return result;
    }

    @GetMapping("/insert")
    @Operation(summary = "简单JDBC插入")
    public Object insert(User user){
        // 简单JDBC插入
        String sql = "INSERT INTO test " +
                "(id, name, cname) " +
                "VALUES(?, ?, ?);";
        int update = jdbcTemplate.update(sql, new Object[]{user.getId(), user.getName(), user.getCname()});
        return update;
    }

    @GetMapping("/update")
    @Operation(summary = "简单JDBC更新")
    public Object update(User user){
        // 简单JDBC更新
        String sql = "UPDATE test SET name= ? WHERE id = ?;";
        int update = jdbcTemplate.update(sql, new Object[]{user.getId(), user.getName()});
        return update;
    }

    @GetMapping("/delete")
    @Operation(summary = "简单JDBC删除")
    public Object delete(User user){
        // 简单JDBC删除
        String sql = "delete FROM test where id = ?";
        int update = jdbcTemplate.update(sql, new Object[]{user.getId()});
        return update;
    }

}
