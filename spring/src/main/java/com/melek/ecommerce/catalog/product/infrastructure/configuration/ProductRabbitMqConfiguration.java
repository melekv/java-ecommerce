package com.melek.ecommerce.catalog.product.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductRabbitMqConfiguration {

    @Bean
    public TopicExchange productsExchange() {
        return new TopicExchange("products.exchange");
    }

    @Bean
    public Queue productCreatedEventQueue() {
        return new Queue("products.created");
    }

    @Bean
    public Binding productCreatedBinding(
        Queue productCreatedEventQueue,
        TopicExchange productsExchange
    ) {
        return BindingBuilder
            .bind(productCreatedEventQueue)
            .to(productsExchange)
            .with("products.created");
    }
}
