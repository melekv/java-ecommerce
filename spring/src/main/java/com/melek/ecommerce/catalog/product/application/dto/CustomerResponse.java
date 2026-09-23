package com.melek.ecommerce.catalog.product.application.dto;

import java.util.UUID;

public record CustomerResponse(
    UUID id,
    String firstName,
    String lastName,
    String email
) {
}
