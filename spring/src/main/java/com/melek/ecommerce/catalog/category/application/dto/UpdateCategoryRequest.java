package com.melek.ecommerce.catalog.category.application.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(

    @NotBlank
    String name
) {
}
