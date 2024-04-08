package com.yfan.demomqkafka.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yfan.demomqkafka.constants.KafkaConsts;
import com.yfan.demomqkafka.vo.MessageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: YondFane
 * @CreateTime: 2024-04-07  23:43
 * @Description: 消息控制器
 * @Version: 1.0
 */
@Slf4j
@RestController()
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("send")
    @ResponseBody
    public Object send() throws ExecutionException, InterruptedException {
        CompletableFuture<SendResult<String, String>> completableFuture = kafkaTemplate.send(KafkaConsts.TOPIC_TEST, "key", System.currentTimeMillis() + "");
        SendResult<String, String> sendResult = completableFuture.get();
        ProducerRecord<String, String> producerRecord = sendResult.getProducerRecord();
        RecordMetadata recordMetadata = sendResult.getRecordMetadata();

        log.info("producerRecord:{}", producerRecord);
        log.info("recordMetadata:{}", recordMetadata);

        return "success";
    }

    @GetMapping("sendMessage")
    @ResponseBody
    public Object sendMessage() throws ExecutionException, InterruptedException {

        MessageVo messageVo = MessageVo.builder().id(1000).name("测试").build();

        CompletableFuture<SendResult<String, String>> completableFuture = kafkaTemplate.send(KafkaConsts.TOPIC_MESSAGE, messageVo.getId().toString(), JSONUtil.toJsonStr(messageVo));
        SendResult<String, String> sendResult = completableFuture.get();
        ProducerRecord<String, String> producerRecord = sendResult.getProducerRecord();
        RecordMetadata recordMetadata = sendResult.getRecordMetadata();

        log.info("producerRecord:{}", producerRecord);
        log.info("recordMetadata:{}", recordMetadata);

        return "success";
    }
}
