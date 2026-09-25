package com.melek.ecommerce.order.application.port;

public record PaymentResult(
    PaymentStatus status,
    String transactionId
) {
}
