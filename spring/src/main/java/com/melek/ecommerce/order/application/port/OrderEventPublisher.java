package com.melek.ecommerce.order.application.port;

import com.melek.ecommerce.shared.messaging.event.OrderCreatedEvent;

public interface OrderEventPublisher {

    void publish(OrderCreatedEvent event);
}
