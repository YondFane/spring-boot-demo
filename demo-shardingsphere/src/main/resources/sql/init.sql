-- test.`user` definition
CREATE DATABASE `test1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE DATABASE `test2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE test1.`t_order` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
    `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
    `remark` varchar(100) DEFAULT NULL COMMENT '备注',
    `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
    `create_time_txt` varchar(50) DEFAULT NULL COMMENT '创建时间文本',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE test2.`t_order` (
      `id` bigint(20) NOT NULL COMMENT '主键',
      `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
      `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
      `remark` varchar(100) DEFAULT NULL COMMENT '备注',
      `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
      `create_time_txt` varchar(50) DEFAULT NULL COMMENT '创建时间文本',
      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;