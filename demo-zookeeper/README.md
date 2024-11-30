# demo-zookeeper

> 此 demo 主要演示了如何使用 Spring Boot 集成 Zookeeper 结合AOP实现分布式锁。

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
    <artifactId>demo-zookeeper</artifactId>
    <version>1.0.0</version>
    <name>demo-zookeeper</name>
    <description>demo-zookeeper</description>

    <properties>
        <java.version>8</java.version>
        <zookeeper-discovery.version>4.1.1</zookeeper-discovery.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
            <version>${zookeeper-discovery.version}</version>
        </dependency>

        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
        </dependency>

        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-openapi3-jakarta-spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>

```

## ZkProps.java

```java
@Data
@ConfigurationProperties(prefix = "zk")
public class ZkProps {
    /**
     * 连接地址
     */
    private String url;

    /**
     * 超时时间(毫秒)，默认1000
     */
    private int timeout = 1000;

    /**
     * 重试次数，默认3
     */
    private int retry = 3;
}
```

## application.yml

```yaml
server:
  port: 8080
  servlet:
    context-path: /demo

zk:
  url: 127.0.0.1:2181
  timeout: 1000
  retry: 3
```

## ZkConfig.java

```java
@Configuration
@EnableConfigurationProperties(ZkProps.class)
public class ZkConfig {
    private final ZkProps zkProps;

    @Autowired
    public ZkConfig(ZkProps zkProps) {
        this.zkProps = zkProps;
    }

    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zkProps.getTimeout(), zkProps.getRetry());
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkProps.getUrl(), retryPolicy);
        client.start();
        return client;
    }
}
```

## HelloController.java

```java
@RestController
@Slf4j
@RequestMapping("/demo")
public class HelloController {


    @Autowired
    private CuratorFramework zkClient;

    @GetMapping("/hello")
    public String hello() throws Exception {
        InterProcessMutex lock = new InterProcessMutex(zkClient, "/hello");
        try {
            // 假设上锁成功，以后拿到的都是
            if (lock.acquire(100, TimeUnit.MILLISECONDS)) {
                log.info("获取锁成功1-----");
                Thread.sleep(10000);
                log.info("获取锁成功2-----");
            } else {
                throw new RuntimeException("请勿重复提交");
            }
        } catch (Exception e) {
            log.error("获取锁异常", e);
        } finally {
            lock.release();
            log.info("释放锁成功-----");
        }
        return "hello";
    }

}

```

