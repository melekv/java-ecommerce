package com.melek.ecommerce.order.application;

import com.melek.ecommerce.order.application.exception.OrderNotFoundException;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import com.melek.ecommerce.order.domain.repository.OrderRepository;

public class GetOrderUseCase {

    private final OrderRepository orderRepository;

    public GetOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(OrderId id) {
        return orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);
    }
}
