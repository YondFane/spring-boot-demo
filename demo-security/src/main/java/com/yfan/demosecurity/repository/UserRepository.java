package com.yfan.demosecurity.repository;

import com.yfan.demosecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户持久层
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 通过名称查询用户
     * @param name
     * @return
     */
    User findByName(String name);

}
