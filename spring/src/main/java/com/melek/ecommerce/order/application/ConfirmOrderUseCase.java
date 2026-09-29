package com.melek.ecommerce.order.application;

import com.melek.ecommerce.order.application.exception.OrderNotFoundException;
import com.melek.ecommerce.order.application.port.OrderEventPublisher;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import com.melek.ecommerce.order.domain.repository.OrderRepository;
import com.melek.ecommerce.shared.domain.model.Money;
import com.melek.ecommerce.shared.messaging.event.OrderConfirmedEvent;

public class ConfirmOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    public ConfirmOrderUseCase(
        OrderRepository orderRepository,
        OrderEventPublisher orderEventPublisher
    ) {
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    public Order execute(OrderId id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);

        order.confirm();

        Order savedOrder = orderRepository.save(order);

        Money total = savedOrder.total();

        orderEventPublisher.publishConfirmed(
            new OrderConfirmedEvent(
                savedOrder.getId().value(),
                savedOrder.getCustomerId(),
                total.amount(),
                total.currency().getCurrencyCode()
            )
        );

        return savedOrder;
    }
}
