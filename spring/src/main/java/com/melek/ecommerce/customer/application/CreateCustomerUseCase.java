package com.melek.ecommerce.customer.application;

import com.melek.ecommerce.customer.domain.model.Customer;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import com.melek.ecommerce.customer.domain.repository.CustomerRepository;

public class CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(
        String firstName,
        String lastName,
        String email
    ) {
        Customer customer = new Customer(
            CustomerId.generate(),
            firstName,
            lastName,
            email
        );

        return customerRepository.save(customer);
    }
}
