package com.melek.ecommerce.customer.application.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerRequest(

    @NotBlank
    String firstName,

    @NotBlank
    String lastName,

    @NotBlank
    String email
) {
}
