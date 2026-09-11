package com.melek.ecommerce.catalog.application.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(

    @NotBlank
    String name
) {
}
