package com.melek.ecommerce.order.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponse(
    UUID id,
    UUID productId,
    String productName,
    BigDecimal unitPrice,
    String currency,
    int quantity
) {
}
