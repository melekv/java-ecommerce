package com.melek.ecommerce.catalog.application.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(

    @NotBlank
    String name
) {
}
