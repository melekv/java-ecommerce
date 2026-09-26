package com.melek.ecommerce.order.infrastructure.messaging;

import com.melek.ecommerce.order.application.port.OrderEventPublisher;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMqConfiguration {

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

    @Bean
    public OrderEventPublisher orderEventPublisher(RabbitTemplate rabbitTemplate) {
        return new RabbitMqOrderEventPublisher(rabbitTemplate);
    }

    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
        ConnectionFactory connectionFactory,
        JacksonJsonMessageConverter messageConverter
    ) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }
}
