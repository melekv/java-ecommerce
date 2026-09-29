package com.melek.ecommerce.payment.domain.model;

import java.util.UUID;

public record PaymentId(UUID value) {

    public PaymentId {
        if (value == null) {
            throw new IllegalArgumentException("Payment id cannot be null");
        }
    }

    public static PaymentId generate() {
        return new PaymentId(UUID.randomUUID());
    }
}
