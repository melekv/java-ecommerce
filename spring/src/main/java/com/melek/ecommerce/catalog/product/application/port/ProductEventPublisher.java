package com.melek.ecommerce.catalog.product.application.port;

import com.melek.ecommerce.shared.messaging.event.ProductCreatedEvent;
import com.melek.ecommerce.shared.messaging.event.ProductDeletedEvent;
import com.melek.ecommerce.shared.messaging.event.ProductUpdatedEvent;

public interface ProductEventPublisher {

    void publish(ProductCreatedEvent event);

    void publish(ProductUpdatedEvent event);

    void publish(ProductDeletedEvent event);
}
