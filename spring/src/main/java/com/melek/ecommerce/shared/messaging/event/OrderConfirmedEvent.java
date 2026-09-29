package com.melek.ecommerce.shared.messaging.event;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderConfirmedEvent(
    UUID orderId,
    UUID customerId,
    BigDecimal amount,
    String currency
) {
}
