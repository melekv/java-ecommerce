package com.melek.ecommerce.catalog.product.application.port;

import com.melek.ecommerce.shared.messaging.event.ProductCreatedEvent;

public interface ProductEventPublisher {

    void publish(ProductCreatedEvent productCreatedEvent);
}
