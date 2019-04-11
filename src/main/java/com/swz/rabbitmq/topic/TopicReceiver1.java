package com.swz.rabbitmq.topic;

import com.swz.rabbitmq.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Package: com.swz.rabbitmq.topic
 * @Description: Topic接收方1
 * @author: swz
 * @date: 2019/4/9 15:43
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_TOPIC1)
public class TopicReceiver1 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String message) {
        logger.info("######Topic Receiver 1  :{}###### ", message);
    }

}
