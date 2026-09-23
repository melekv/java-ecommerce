package com.melek.ecommerce.customer.infrastructure.persistence;

import com.melek.ecommerce.customer.domain.model.Customer;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import com.melek.ecommerce.customer.domain.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JpaCustomerRepository repository;

    private final CustomerPersistenceMapper mapper;

    public CustomerRepositoryImpl(
        JpaCustomerRepository repository,
        CustomerPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Customer save(Customer customer) {
        return mapper.toDomain(
            repository.save(
                mapper.toEntity(customer)
            )
        );
    }

    @Override
    public Optional<Customer> findById(CustomerId id) {
        return repository.findById(id.value())
            .map(mapper::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public void deleteById(CustomerId id) {
        repository.deleteById(id.value());
    }
}
