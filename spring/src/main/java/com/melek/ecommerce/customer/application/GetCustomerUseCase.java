package com.melek.ecommerce.customer.application;

import com.melek.ecommerce.customer.application.exception.CustomerNotFoundException;
import com.melek.ecommerce.customer.domain.model.Customer;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import com.melek.ecommerce.customer.domain.repository.CustomerRepository;

public class GetCustomerUseCase {

    private final CustomerRepository customerRepository;

    public GetCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(CustomerId id) {
        return customerRepository.findById(id)
            .orElseThrow(CustomerNotFoundException::new);
    }
}
