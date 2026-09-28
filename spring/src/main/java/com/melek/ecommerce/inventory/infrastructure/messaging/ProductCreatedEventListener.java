package com.melek.ecommerce.inventory.infrastructure.messaging;

import com.melek.ecommerce.inventory.application.CreateStockUseCase;
import com.melek.ecommerce.shared.messaging.event.ProductCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductCreatedEventListener {

    private final CreateStockUseCase createStockUseCase;

    public ProductCreatedEventListener(CreateStockUseCase createStockUseCase) {
        this.createStockUseCase = createStockUseCase;
    }

    @RabbitListener(queues = "inventory.product-created")
    public void handle(ProductCreatedEvent event) {
        createStockUseCase.create(
            event.productId()
        );
    }
}
