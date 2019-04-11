package com.swz.rabbitmq.topic;

import com.swz.rabbitmq.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Package: com.swz.rabbitmq.topic
 * @Description: Topic发送方类
 * @author: swz
 * @date: 2019/4/9 15:28
 */
@Component
public class TopicSender implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public TopicSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    public void sendTopic(String content) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC, "topic.A", content, correlationData);

    }

    public void sendTopic1(String content) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC, RabbitConfig.ROUTINGKEY_TOPIC1, content, correlationData);

    }

    public void sendTopic2(String content) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC, RabbitConfig.ROUTINGKEY_TOPIC2, content, correlationData);

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info("######回调id:{}######", correlationData);
        if (b) {
            logger.info("######消息成功消费######");
        } else {
            logger.info("######消息消费失败:{}######", s);
        }
    }
}
