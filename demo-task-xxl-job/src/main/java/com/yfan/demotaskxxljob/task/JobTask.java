package com.yfan.demotaskxxljob.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-24  17:21
 * @Description: 定时任务
 * @Version: 1.0
 */
@Slf4j
@Component
@RestController
public class JobTask {

    @XxlJob("test")
    @GetMapping("/test")
    public ReturnT<String> test(String param) {
        log.info("定时任务执行------{}", param);
        return ReturnT.SUCCESS;
    }

}

