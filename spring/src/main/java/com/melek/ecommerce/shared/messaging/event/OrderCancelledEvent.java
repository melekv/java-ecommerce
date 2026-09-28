package com.melek.ecommerce.shared.messaging.event;

import java.util.List;
import java.util.UUID;

public record OrderCancelledEvent(
    UUID orderId,
    List<OrderCancelledItem> items
) {
}
