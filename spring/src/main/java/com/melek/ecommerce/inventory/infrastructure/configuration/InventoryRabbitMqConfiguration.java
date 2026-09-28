package com.melek.ecommerce.inventory.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryRabbitMqConfiguration {

    @Bean
    public Queue inventoryOrderCreatedQueue() {
        return new Queue("inventory.order-created");
    }

    @Bean
    public Queue inventoryProductCreatedQueue() {
        return new Queue("inventory.product-created");
    }

    @Bean
    public Queue inventoryOrderCancelledQueue() {
        return new Queue("inventory.order-cancelled");
    }

    @Bean
    public Binding inventoryOrderCreatedBinding(
        Queue inventoryOrderCreatedQueue,
        TopicExchange ordersExchange
    ) {
        return BindingBuilder
            .bind(inventoryOrderCreatedQueue)
            .to(ordersExchange)
            .with("orders.created");
    }

    @Bean
    public Binding inventoryOrderCancelledBinding(
        Queue inventoryOrderCancelledQueue,
        TopicExchange ordersExchange
    ) {
        return BindingBuilder
            .bind(inventoryOrderCancelledQueue)
            .to(ordersExchange)
            .with("orders.cancelled");
    }

    @Bean
    public Binding inventoryProductCreatedBinding(
        Queue inventoryProductCreatedQueue,
        TopicExchange productsExchange
    ) {
        return BindingBuilder
            .bind(inventoryProductCreatedQueue)
            .to(productsExchange)
            .with("inventory.product-created");
    }
}
