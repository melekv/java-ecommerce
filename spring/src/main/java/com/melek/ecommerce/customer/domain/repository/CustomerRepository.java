package com.melek.ecommerce.customer.domain.repository;

import com.melek.ecommerce.customer.domain.model.Customer;
import com.melek.ecommerce.customer.domain.model.CustomerId;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findById(CustomerId id);

    List<Customer> findAll();

    void deleteById(CustomerId id);
}
