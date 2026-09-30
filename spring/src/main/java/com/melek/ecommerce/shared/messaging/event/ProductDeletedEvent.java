package com.melek.ecommerce.shared.messaging.event;

import java.util.UUID;

public record ProductDeletedEvent(
    UUID productId
) {
}
