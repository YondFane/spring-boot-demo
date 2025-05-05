package com.yfan.demoshardingsphere.config;


import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * 自定义分表算法
 */
public class CustomTableShardingAlgorithm implements StandardShardingAlgorithm<Date> {
    private static final DateTimeFormatter TABLE_FORMAT = DateTimeFormatter.ofPattern("yyyyMM");
    private static final Date MIN_VALUE = Date.from(YearMonth.parse("202503", java.time.format.DateTimeFormatter.ofPattern("yyyyMM"))
            .atDay(1).atStartOfDay(ZoneId.of("UTC"))  // 指定时区（可替换为 ZoneId.systemDefault()）
            .toInstant());
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        // 获取分片键值（create_time）
        Date createTime = shardingValue.getValue();
        Date now = new Date();
        if (createTime.before(MIN_VALUE)) {
            createTime = MIN_VALUE;
        }
        else if (createTime.after(now)) {
            createTime = now;
        }
        // 生成表后缀：_202501
        String tableSuffix = DateUtil.format(createTime, TABLE_FORMAT);
        // 组合逻辑表名 + 后缀
        return shardingValue.getLogicTableName() + "_" + tableSuffix;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        Range<Date> range = rangeShardingValue.getValueRange();
        Date start = range.hasLowerBound() ? range.lowerEndpoint() : MIN_VALUE;
        if (start.before(MIN_VALUE)) {
            start = MIN_VALUE;
        }
        Date end = new Date();// range.hasUpperBound() ? range.upperEndpoint() : new Date();
        Set<String> tables = new HashSet<>();
        while (start.before(end)) {
            tables.add(rangeShardingValue.getLogicTableName() + "_" + DateUtil.format(start, TABLE_FORMAT));
            start = addMonth(start, 1);
        }
        return tables;
    }

    @Override
    public Properties getProps() {
        return new Properties();
    }

    @Override
    public void init(Properties properties) {
        // 初始化逻辑（可留空）
        System.out.println("===============================");
    }


    /**
     * 返回日期加X月后的日期
     */
    public static Date addMonth(Date date, int i) {
        try {
            String dateStr = DateUtil.format(date,sdf);
            GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(dateStr.substring(0, 4)),
                    Integer.parseInt(dateStr.substring(5, 7)) - 1, Integer.parseInt(dateStr.substring(8, 10)));
            gCal.add(GregorianCalendar.MONTH, i);
            return gCal.getTime();
        } catch (Exception e) {
            return null;
        }
    }
}