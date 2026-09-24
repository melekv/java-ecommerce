package com.melek.ecommerce.customer.infrastructure.persistence;

import com.melek.ecommerce.customer.domain.model.Customer;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerPersistenceMapper {

    public CustomerEntity toEntity(Customer customer) {
        return new CustomerEntity(
            customer.getId().value(),
            customer.getFirstName(),
            customer.getLastName(),
            customer.getEmail()
        );
    }

    public Customer toDomain(CustomerEntity entity) {
        return new Customer(
            new CustomerId(entity.getId()),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getEmail()
        );
    }
}
