package com.yfan.demoelasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-25  21:06
 * @Description: User实体
 * @Version: 1.0
 */
@Document(indexName = ("user"))
@Data
public class UserEntity {

    @Id
    private Integer id;

    private String name;

}
