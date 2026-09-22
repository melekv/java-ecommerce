package com.melek.ecommerce.shared.infrastructure.web;

public record ErrorResponse(
    String code,
    String message
) {
}
