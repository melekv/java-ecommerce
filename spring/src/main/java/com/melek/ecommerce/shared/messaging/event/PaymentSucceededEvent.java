package com.melek.ecommerce.shared.messaging.event;

import java.util.UUID;

public record PaymentSucceededEvent(
    UUID paymentId,
    UUID orderId
) {
}
