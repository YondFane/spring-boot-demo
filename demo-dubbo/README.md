# spring-boot-demo-dubbo

> 此 demo 主要演示了 Spring Boot 如何集成 Dubbo，demo 分了3个module，分别为公共模块 `spring-boot-demo-dubbo-common`、服务提供方`spring-boot-demo-dubbo-provider`、服务调用方`spring-boot-demo-dubbo-consumer`

## 注意

本例注册中心使用的是 zookeeper，作者编写本demo时，采用docker方式运行 zookeeper

1. 下载镜像：`docker pull wurstmeister/zookeeper`

2. 运行容器：`docker run -d -p 2181:2181 -p 2888:2888 -p 2222:22 -p 3888:3888 --name zk wurstmeister/zookeeper`

3. 停止容器：`docker stop zk`

4. 启动容器：`docker start zk`

## windows安装zookeeper
zookeeper下载地址
https://archive.apache.org/dist/zookeeper/

1、config目录的 zoo_sample.cfg 改为 zoo.cfg
2、bin目录启动 zkServer.cmd

## 运行步骤

1. 进入服务提供方 `spring-boot-demo-dubbo-provider` 目录，运行 `SpringBootDemoDubboProviderApplication.java`
2. 进入服务调用方 `spring-boot-demo-dubbo-consumer` 目录，运行 `SpringBootDemoDubboConsumerApplication.java`
3. 打开浏览器输入 http://localhost:8080/demo/sayHello ，观察浏览器输出，以及服务提供方和服务调用方的控制台输出日志情况

## pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.yfan</groupId>
        <artifactId>spring-boot-demo</artifactId>
        <version>1.0.0</version>
    </parent>

    <groupId>com.yfan</groupId>
    <artifactId>demo-dubbo</artifactId>
    <version>1.0.0</version>
    <name>demo-dubbo</name>
    <description>demo-dubbo</description>
    <packaging>pom</packaging>

    <modules>
        <module>demo-dubbo-common</module>
        <module>demo-dubbo-provider</module>
        <module>demo-dubbo-consumer</module>
    </modules>

    <properties>
        <java.version>8</java.version>
        <dubbo.starter.version>3.2.11</dubbo.starter.version>
        <dubbo.version>3.2.11</dubbo.version>
    </properties>

</project>

```

## 参考

1. dubbo 官网：http://dubbo.apache.org/zh-cn/
2. https://cn.dubbo.apache.org/zh-cn/overview/quickstart/java/brief/

