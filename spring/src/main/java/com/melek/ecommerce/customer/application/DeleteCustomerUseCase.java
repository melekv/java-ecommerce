package com.melek.ecommerce.customer.application;

import com.melek.ecommerce.customer.application.exception.CustomerNotFoundException;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import com.melek.ecommerce.customer.domain.repository.CustomerRepository;

public class DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;

    public DeleteCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(CustomerId id) {
        customerRepository.findById(id)
            .orElseThrow(CustomerNotFoundException::new);

        customerRepository.deleteById(id);
    }
}
