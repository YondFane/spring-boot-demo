# spring-boot-demo-dubbo-common

> 此 module 主要是用于公共部分，主要存放工具类，实体，以及服务提供方/调用方的接口定义


## HelloService.java

```java
/**
 * <p>
 * Hello服务接口
 * </p>
 */
public interface HelloService {
    /**
     * 问好
     *
     * @param name 姓名
     * @return 问好
     */
    String sayHello(String name);
}
```

