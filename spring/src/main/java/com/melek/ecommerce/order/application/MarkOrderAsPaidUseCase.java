package com.melek.ecommerce.order.application;

import com.melek.ecommerce.order.application.exception.OrderNotFoundException;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import com.melek.ecommerce.order.domain.repository.OrderRepository;

public class MarkOrderAsPaidUseCase {

    private final OrderRepository orderRepository;

    public MarkOrderAsPaidUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(OrderId id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);

        order.pay();

        return orderRepository.save(order);
    }
}
