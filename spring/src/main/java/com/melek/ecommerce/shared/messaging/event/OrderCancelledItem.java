package com.melek.ecommerce.shared.messaging.event;

import java.util.UUID;

public record OrderCancelledItem(
    UUID productId,
    int quantity
) {
}
