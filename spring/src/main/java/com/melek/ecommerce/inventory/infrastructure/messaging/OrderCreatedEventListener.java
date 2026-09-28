package com.melek.ecommerce.inventory.infrastructure.messaging;

import com.melek.ecommerce.inventory.application.ReserveStockUseCase;
import com.melek.ecommerce.shared.messaging.event.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedEventListener {

    private final ReserveStockUseCase reserveStockUseCase;

    public OrderCreatedEventListener(ReserveStockUseCase reserveStockUseCase) {
        this.reserveStockUseCase = reserveStockUseCase;
    }

    @RabbitListener(queues = "inventory.order-created")
    public void handle(OrderCreatedEvent event) {
        event.items()
            .forEach(
                item -> reserveStockUseCase.reserve(
                    item.productId(),
                    item.quantity()
                )
            );
    }
}
