package com.melek.ecommerce.order.infrastructure.messaging;

import com.melek.ecommerce.shared.messaging.event.OrderCreatedEvent;
import com.melek.ecommerce.order.application.port.OrderEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqOrderEventPublisher implements OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqOrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(OrderCreatedEvent event) {
        rabbitTemplate.convertAndSend(
            "orders.exchange",
            "orders.created",
            event
        );
    }
}
