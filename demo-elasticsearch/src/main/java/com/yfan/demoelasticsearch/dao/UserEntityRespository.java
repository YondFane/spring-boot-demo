package com.yfan.demoelasticsearch.dao;

import com.yfan.demoelasticsearch.entity.UserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-25  21:11
 * @Description: User实体持久化层
 * @Version: 1.0
 */
@Repository
public interface UserEntityRespository extends ElasticsearchRepository<UserEntity, String> {

}
