package com.melek.ecommerce.catalog.product.infrastructure.messaging;

import com.melek.ecommerce.catalog.product.application.port.ProductEventPublisher;
import com.melek.ecommerce.shared.messaging.event.ProductCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqProductEventPublisher implements ProductEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqProductEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(ProductCreatedEvent event) {
        rabbitTemplate.convertAndSend(
            "products.exchange",
            "inventory.product-created",
            event
        );
    }
}
