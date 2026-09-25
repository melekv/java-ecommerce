package com.melek.ecommerce.order.infrastructure.payment;

import com.melek.ecommerce.order.application.port.PaymentGateway;
import com.melek.ecommerce.order.application.port.PaymentResult;
import com.melek.ecommerce.order.application.port.PaymentStatus;
import com.melek.ecommerce.order.domain.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakePaymentGateway implements PaymentGateway {

    private final Map<String, PaymentResult> payments = new HashMap<>();

    @Override
    public PaymentResult pay(
        Order order,
        String idempotencyKey
    ) {
        return payments.computeIfAbsent(
            idempotencyKey,
            key -> new PaymentResult(
                PaymentStatus.SUCCESS,
                UUID.randomUUID().toString()
            )
        );
    }
}
