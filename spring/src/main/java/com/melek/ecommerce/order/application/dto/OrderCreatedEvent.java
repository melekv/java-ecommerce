package com.melek.ecommerce.order.application.dto;

import java.util.UUID;

public record OrderCreatedEvent(
    UUID orderId,
    UUID customerId
) {
}
