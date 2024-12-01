package com.yfan.demomysqlmybatis.mapper;

import com.yfan.demomysqlmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @BelongsProject: spring-boot-demo
 * @BelongsPackage: com.yfan.demomysqlmybatis.mapper
 * @Author: YANF
 * @CreateTime: 2024-11-30  13:59
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    List<User> listAll();

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User queryById(Integer id);
}
