package com.melek.ecommerce.catalog.infrastructure.web;

public record ErrorResponse(
    String code,
    String message
) {
}
