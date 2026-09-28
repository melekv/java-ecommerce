package com.melek.ecommerce.shared.messaging.event;

import java.util.List;
import java.util.UUID;

public record OrderCreatedEvent(
    UUID orderId,
    UUID customerId,
    List<OrderCreatedItem> items
) {
}
