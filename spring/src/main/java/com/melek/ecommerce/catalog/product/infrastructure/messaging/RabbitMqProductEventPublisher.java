package com.melek.ecommerce.catalog.product.infrastructure.messaging;

import com.melek.ecommerce.catalog.product.application.port.ProductEventPublisher;
import com.melek.ecommerce.shared.messaging.event.ProductCreatedEvent;
import com.melek.ecommerce.shared.messaging.event.ProductDeletedEvent;
import com.melek.ecommerce.shared.messaging.event.ProductUpdatedEvent;
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
            "products.created",
            event
        );
    }

    @Override
    public void publish(ProductUpdatedEvent event) {
        rabbitTemplate.convertAndSend(
            "products.exchange",
            "products.updated",
            event
        );
    }

    @Override
    public void publish(ProductDeletedEvent event) {
        rabbitTemplate.convertAndSend(
            "products.exchange",
            "products.deleted",
            event
        );
    }
}
