package com.melek.ecommerce.order.application.port;

import com.melek.ecommerce.shared.messaging.event.OrderCancelledEvent;
import com.melek.ecommerce.shared.messaging.event.OrderCreatedEvent;

public interface OrderEventPublisher {

    void publishCreated(OrderCreatedEvent event);

    void publishCancelled(OrderCancelledEvent event);
}
