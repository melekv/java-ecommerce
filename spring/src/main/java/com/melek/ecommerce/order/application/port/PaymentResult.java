package com.melek.ecommerce.order.application.port;

import com.melek.ecommerce.payment.domain.model.PaymentStatus;

public record PaymentResult(
    PaymentStatus status,
    String transactionId
) {
}
