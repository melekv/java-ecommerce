package com.melek.ecommerce.order.infrastructure.messaging;

import com.melek.ecommerce.shared.messaging.event.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventConsumer {

    @RabbitListener(queues = "orders.created")
    public void handle(OrderCreatedEvent event) {
        System.out.println("Received OrderCreatedEvent: " + event);
    }
}
