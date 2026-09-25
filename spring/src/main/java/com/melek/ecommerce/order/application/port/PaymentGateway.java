package com.melek.ecommerce.order.application.port;

import com.melek.ecommerce.order.domain.model.Order;

public interface PaymentGateway {

    PaymentResult pay(
        Order order,
        String idempotencyKey
    );
}
