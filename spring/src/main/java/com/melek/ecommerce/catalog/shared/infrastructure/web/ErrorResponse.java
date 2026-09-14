package com.melek.ecommerce.catalog.shared.infrastructure.web;

public record ErrorResponse(
    String code,
    String message
) {
}
