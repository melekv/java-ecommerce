package com.melek.ecommerce.cart.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record AddItemToCartRequest(

    @NotNull
    UUID productId,

    @Positive
    int quantity
) {
}
