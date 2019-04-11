package com.swz.rabbitmq.fanout;

import com.swz.rabbitmq.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Package: com.swz.rabbitmq.fanout
 * @Description: Fanout接收方C
 * @author: swz
 * @date: 2019/4/9 14:36
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_FANOUTC)
public class FanoutReceiverC {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String message) {
        logger.info("######fanout Receiver C  :{}###### ", message);
    }
}
