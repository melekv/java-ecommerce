package com.melek.ecommerce.payment.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRabbitMqConfiguration {

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange("payment.exchange");
    }

    @Bean
    public Queue paymentOrderConfirmedQueue() {
        return new Queue("payment.order-confirmed");
    }

    @Bean
    public Binding paymentOrderConfirmedBinding(
        Queue paymentOrderConfirmedQueue,
        TopicExchange ordersExchange
    ) {
        return BindingBuilder
            .bind(paymentOrderConfirmedQueue)
            .to(ordersExchange)
            .with("orders.confirmed");
    }
}
