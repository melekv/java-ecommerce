package com.melek.ecommerce.customer.application;

import com.melek.ecommerce.customer.application.exception.CustomerNotFoundException;
import com.melek.ecommerce.customer.domain.model.Customer;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import com.melek.ecommerce.customer.domain.repository.CustomerRepository;

public class UpdateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public UpdateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(
        CustomerId id,
        String firstName,
        String lastName,
        String email
    ) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(CustomerNotFoundException::new);

        customer.changeFirstName(firstName);
        customer.changeLastName(lastName);
        customer.changeEmail(email);

        return customerRepository.save(customer);
    }
}
