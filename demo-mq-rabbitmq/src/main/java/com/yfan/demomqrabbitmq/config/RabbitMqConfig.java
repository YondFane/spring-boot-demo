package com.yfan.demomqrabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: YondFane
 * @CreateTime: 2024-03-30  23:13
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Configuration
public class RabbitMqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
        cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.SIMPLE);
        cachingConnectionFactory.setPublisherReturns(true);

        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
        });
        rabbitTemplate.setReturnsCallback(message -> {
            log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", message.getExchange(), message.getRoutingKey(), message.getReplyCode(), message.getReplyText(), message.getMessage());
        });
        return rabbitTemplate;
    }

}
