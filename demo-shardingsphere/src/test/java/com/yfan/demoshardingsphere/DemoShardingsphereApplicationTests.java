package com.yfan.demoshardingsphere;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.RandomUtil;
import com.yfan.demoshardingsphere.entity.Order;
import com.yfan.demoshardingsphere.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
//@RunWith(SpringRunner.class)
@SpringBootTest
class DemoShardingsphereApplicationTests {


    @Autowired
    private OrderMapper orderMapper;


    @Test
    void contextLoads() {
    }


    /**
     * 测试新增
     */
    @Test
    public void testInsert() {
        SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();
        for (long i = 1; i < 10; i++) {
            for (long j = 1; j < 20; j++) {
                Order order = Order.builder()
                        .id(snowflakeGenerator.next())
                        .userId(i)
                        .orderId(j)
                        .createTime(System.currentTimeMillis())
                        .remark(RandomUtil.randomString(20))
                        .createTimeTxt(DateUtil.formatLocalDateTime(LocalDateTime.now()))
                        .build();
                log.info(order.toString());
                orderMapper.insert(order);
            }
        }
    }

    @Test
    public void test1() {
        String s = DateUtil.formatLocalDateTime(LocalDateTime.now());
        System.out.println(s);
    }


}
