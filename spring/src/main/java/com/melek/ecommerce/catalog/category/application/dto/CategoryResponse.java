package com.melek.ecommerce.catalog.category.application.dto;

import java.util.UUID;

public record CategoryResponse(
    UUID id,
    String name
) {
}
