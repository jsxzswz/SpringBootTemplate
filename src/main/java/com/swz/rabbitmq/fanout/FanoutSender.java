package com.swz.rabbitmq.fanout;

import com.swz.rabbitmq.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Package: com.swz.rabbitmq.fanout
 * @Description: Fanout发送方类
 * @author: swz
 * @date: 2019/4/9 14:27
 */
@Component
public class FanoutSender implements RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public FanoutSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    public void sendFanoutMsg(String content) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_FANOUT, "", content, correlationData);
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
