package com.yfan.demologaop.aop;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.yfan.demologapp.entity.Log;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-23  14:56
 * @Description: TODO
 * @Version: 1.0
 */
@Component
@Aspect
@Slf4j
public class LogAop {

    @Pointcut("execution(public * com.yfan.demologaop.controller..*(..))")
    public void log(){}



    @Before("log()")
    public void cutProcess(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("注解方式AOP开始拦截, 当前拦截的方法名: " + method.getName());
    }

    @After("log()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("注解方式AOP执行的方法 :" + method.getName() + " 执行完了");
    }


    @Around("log()")
    public Object testCutAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("注解方式AOP拦截开始进入环绕通知.......");
        Object proceed = joinPoint.proceed();
        log.info("准备退出环绕......");
        return proceed;
    }

    /**
     * returning属性指定连接点方法返回的结果放置在result变量中
     *
     * @param joinPoint 连接点
     * @param result    返回结果
     */
    @AfterReturning(value = "log()", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("注解方式AOP拦截的方法执行成功, 进入返回通知拦截, 方法名为: " + method.getName() + ", 返回结果为: " + result.toString());
    }

    @AfterThrowing(value = "log()", throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Exception e) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("注解方式AOP进入方法异常拦截, 方法名为: " + method.getName() + ", 异常信息为: " + e.getMessage());
    }

    /**
     * 环绕操作
     *
     * @param point 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        // 打印请求相关参数
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);

        Log l = Log.builder()
                .threadId(Long.toString(Thread.currentThread().getId()))
                .threadName(Thread.currentThread().getName())
                .ip(getIp(request))
                .url(request.getRequestURL().toString())
                .classMethod(String.format("%s.%s", point.getSignature().getDeclaringTypeName(),
                        point.getSignature().getName()))
                .httpMethod(request.getMethod())
                .requestParams(getNameAndValue(point))
                .result(result)
                .timeCost(System.currentTimeMillis() - startTime)
                .userAgent(header)
                .browser(userAgent.getBrowser().toString())
                .os(userAgent.getOperatingSystem().toString())
                .build();

        log.info("Request Log Info : {}", JSONUtil.toJsonStr(l));

        return result;
    }

    /**
     *  获取方法参数名和参数值
     * @param joinPoint
     * @return
     */
    private Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {

        final Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        final String[] names = methodSignature.getParameterNames();
        final Object[] args = joinPoint.getArgs();

        if (ArrayUtil.isEmpty(names) || ArrayUtil.isEmpty(args)) {
            return Collections.emptyMap();
        }
        if (names.length != args.length) {
            log.warn("{}方法参数名和参数值数量不一致", methodSignature.getName());
            return Collections.emptyMap();
        }
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i], args[i]);
        }
        return map;
    }

    private static final String UNKNOWN = "unknown";

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }

}
