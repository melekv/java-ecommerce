package com.melek.ecommerce.order.application;

import com.melek.ecommerce.order.application.exception.OrderNotFoundException;
import com.melek.ecommerce.order.application.exception.PaymentFailedException;
import com.melek.ecommerce.order.application.port.PaymentGateway;
import com.melek.ecommerce.order.application.port.PaymentResult;
import com.melek.ecommerce.order.application.port.PaymentStatus;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import com.melek.ecommerce.order.domain.model.OrderStatus;
import com.melek.ecommerce.order.domain.repository.OrderRepository;

import java.util.UUID;

public class PayOrderUseCase {

    private final OrderRepository orderRepository;

    private final PaymentGateway paymentGateway;

    public PayOrderUseCase(
        OrderRepository orderRepository,
        PaymentGateway paymentGateway
    ) {
        this.orderRepository = orderRepository;
        this.paymentGateway = paymentGateway;
    }

    public Order execute(OrderId id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(OrderNotFoundException::new);

        if (order.getStatus() == OrderStatus.PAID) {
            return order;
        }

        PaymentResult paymentResult = paymentGateway.pay(
            order,
            order.getId().value().toString()
        );

        if (paymentResult.status() != PaymentStatus.SUCCESS) {
            throw new PaymentFailedException();
        }

        order.pay();

        return orderRepository.save(order);
    }
}
