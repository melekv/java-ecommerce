package com.melek.ecommerce.order.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record OrderItemRequest(

    @NotNull
    UUID id,

    @Positive
    int quantity
) {
}
