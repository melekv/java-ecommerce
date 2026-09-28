package com.melek.ecommerce.order.application;

import com.melek.ecommerce.order.application.exception.OrderNotFoundException;
import com.melek.ecommerce.order.application.port.OrderEventPublisher;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import com.melek.ecommerce.order.domain.repository.OrderRepository;
import com.melek.ecommerce.shared.messaging.event.OrderCancelledEvent;
import com.melek.ecommerce.shared.messaging.event.OrderCancelledItem;

public class CancelOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    public CancelOrderUseCase(
        OrderRepository orderRepository,
        OrderEventPublisher orderEventPublisher
    ) {
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    public Order execute(OrderId id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);

        order.cancel();

        Order savedOrder = orderRepository.save(order);

        orderEventPublisher.publishCancelled(
            new OrderCancelledEvent(
                savedOrder.getId().value(),
                savedOrder.getItems()
                    .stream()
                    .map(item -> new OrderCancelledItem(
                        item.getProductId().value(),
                        item.getQuantity()
                    ))
                    .toList()
            )
        );

        return savedOrder;
    }
}
