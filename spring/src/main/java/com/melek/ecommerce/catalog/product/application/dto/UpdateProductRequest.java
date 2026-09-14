package com.melek.ecommerce.catalog.product.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record UpdateProductRequest(

    @NotBlank
    String name,

    String description,

    @NotNull
    @PositiveOrZero
    BigDecimal price,

    @NotNull
    Currency currency,

    @NotNull
    UUID categoryId
) {
}
