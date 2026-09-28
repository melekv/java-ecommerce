package com.melek.ecommerce.inventory.infrastructure.messaging;

import com.melek.ecommerce.inventory.application.ReleaseStockUseCase;
import com.melek.ecommerce.shared.messaging.event.OrderCancelledEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCancelledEventListener {

    private final ReleaseStockUseCase releaseStockUseCase;

    public OrderCancelledEventListener(ReleaseStockUseCase releaseStockUseCase) {
        this.releaseStockUseCase = releaseStockUseCase;
    }

    @RabbitListener(queues = "inventory.order-cancelled")
    public void handle(OrderCancelledEvent event) {
        event.items()
            .forEach(
                item ->releaseStockUseCase.release(
                    item.productId(),
                    item.quantity()
                )
            );
    }
}
