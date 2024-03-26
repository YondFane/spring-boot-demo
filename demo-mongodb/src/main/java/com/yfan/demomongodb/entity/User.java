package com.yfan.demomongodb.entity;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-26  21:39
 * @Description: TODO
 * @Version: 1.0
 */
@Document
@Data
public class User {

    private Integer id;

    private String name;

}
