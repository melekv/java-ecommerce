package com.melek.ecommerce.order.application.port;

import com.melek.ecommerce.order.application.dto.OrderCreatedEvent;

public interface OrderEventPublisher {

    void publish(OrderCreatedEvent event);
}
