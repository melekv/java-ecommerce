package com.melek.ecommerce.order.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRabbitMqConfiguration {

    @Bean
    public TopicExchange ordersExchange() {
        return new TopicExchange("orders.exchange");
    }

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue("orders.created");
    }

    @Bean
    public Binding orderCreatedBinding(
        Queue orderCreatedQueue,
        TopicExchange ordersExchange
    ) {
        return BindingBuilder
            .bind(orderCreatedQueue)
            .to(ordersExchange)
            .with("orders.created");
    }
}
