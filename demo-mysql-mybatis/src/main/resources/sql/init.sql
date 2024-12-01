CREATE TABLE `user` (
    `id` bigint(20) NOT null COMMENT '主键',
    `name` varchar(100) DEFAULT null COMMENT '名称',
    `sex` tinyint(4) DEFAULT null COMMENT '性别 0 女 1 男',
    `age` tinyint(4) DEFAULT null COMMENT '年龄',
    `deposit` bigint(20) DEFAULT null COMMENT '存款',
    `version` bigint(20) default null COMMENT '版本号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;