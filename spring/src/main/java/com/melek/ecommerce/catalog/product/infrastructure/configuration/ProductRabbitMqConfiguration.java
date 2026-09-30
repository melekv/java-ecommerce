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
    public Queue productCreatedForSearchEventQueue() {
        return new Queue("products.elasticsearch.created");
    }

    @Bean
    public Queue productUpdatedForSearchEventQueue() {
        return new Queue("products.elasticsearch.updated");
    }

    @Bean
    public Queue productDeletedForSearchEventQueue() {
        return new Queue("products.elasticsearch.deleted");
    }

    @Bean
    public Binding productCreatedForSearchBinding(
        Queue productCreatedForSearchEventQueue,
        TopicExchange productsExchange
    ) {
        return BindingBuilder
            .bind(productCreatedForSearchEventQueue)
            .to(productsExchange)
            .with("products.created");
    }

    @Bean
    public Binding productUpdatedForSearchBinding(
        Queue productUpdatedForSearchEventQueue,
        TopicExchange productsExchange
    ) {
        return BindingBuilder
            .bind(productUpdatedForSearchEventQueue)
            .to(productsExchange)
            .with("products.updated");
    }

    @Bean
    public Binding productDeletedForSearchBinding(
        Queue productDeletedForSearchEventQueue,
        TopicExchange productsExchange
    ) {
        return BindingBuilder
            .bind(productDeletedForSearchEventQueue)
            .to(productsExchange)
            .with("products.deleted");
    }
}
