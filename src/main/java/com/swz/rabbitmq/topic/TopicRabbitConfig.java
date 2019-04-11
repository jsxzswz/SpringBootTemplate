package com.swz.rabbitmq.topic;

import com.swz.rabbitmq.RabbitConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.swz.rabbitmq.topic
 * @Description: Topic Exchange（广播模式）配置类
 * @author: swz
 * @date: 2019/4/9 15:10
 */
@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue queueTopic1() {
        return new Queue(RabbitConfig.QUEUE_TOPIC1);
    }

    @Bean
    public Queue queueTopic2() {
        return new Queue(RabbitConfig.QUEUE_TOPIC2);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(RabbitConfig.EXCHANGE_TOPIC);
    }

    @Bean
    Binding bindingExchange1(Queue queueTopic1, TopicExchange exchange) {
        return BindingBuilder.bind(queueTopic1).to(exchange).with(RabbitConfig.ROUTINGKEY_TOPIC1);
    }

    @Bean
    Binding bindingExchange2(Queue queueTopic2, TopicExchange exchange) {
        return BindingBuilder.bind(queueTopic2).to(exchange).with("topic.#");
    }
}
