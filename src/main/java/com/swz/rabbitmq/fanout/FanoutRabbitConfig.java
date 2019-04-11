package com.swz.rabbitmq.fanout;

import com.swz.rabbitmq.RabbitConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: com.swz.rabbitmq.fanout
 * @Description: Fanout Exchange（广播模式）配置类
 * @author: swz
 * @date: 2019/4/9 14:18
 */
@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue queueFanoutA() {
        return new Queue(RabbitConfig.QUEUE_FANOUTA);
    }

    @Bean
    public Queue queueFanoutB() {
        return new Queue(RabbitConfig.QUEUE_FANOUTB);
    }

    @Bean
    public Queue queueFanoutC() {
        return new Queue(RabbitConfig.QUEUE_FANOUTC);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitConfig.EXCHANGE_FANOUT);
    }

    @Bean
    Binding bindingExchangeA(Queue queueFanoutA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanoutA).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue queueFanoutB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanoutB).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue queueFanoutC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanoutC).to(fanoutExchange);
    }

}
