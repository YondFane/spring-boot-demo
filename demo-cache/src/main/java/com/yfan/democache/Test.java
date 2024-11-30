package com.yfan.democache;

public class Test {

    public static void main(String[] args) {

        // 计算当前时间
        long currentTimeMillis = System.currentTimeMillis();

        // 获取并格式化当前时间
        String currentTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTimeMillis);

        // 打印结果
        System.out.println("Current time: " + currentTime);
    }
}
