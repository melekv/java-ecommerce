package com.melek.ecommerce.customer.application.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(

    @NotBlank
    String firstName,

    @NotBlank
    String lastName,

    @NotBlank
    String email
) {
}
