package com.melek.ecommerce.customer.infrastructure.web;

import com.melek.ecommerce.catalog.product.application.dto.CustomerResponse;
import com.melek.ecommerce.customer.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerResponseMapper {

    public CustomerResponse map(Customer customer) {
        return new CustomerResponse(
            customer.getId().value(),
            customer.getFirstName(),
            customer.getLastName(),
            customer.getEmail()
        );
    }
}
