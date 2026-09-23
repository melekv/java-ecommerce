package com.melek.ecommerce.customer.infrastructure.configuration;

import com.melek.ecommerce.customer.application.CreateCustomerUseCase;
import com.melek.ecommerce.customer.application.DeleteCustomerUseCase;
import com.melek.ecommerce.customer.application.GetCustomerUseCase;
import com.melek.ecommerce.customer.application.UpdateCustomerUseCase;
import com.melek.ecommerce.customer.domain.repository.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(
        CustomerRepository customerRepository
    ) {
        return new CreateCustomerUseCase(customerRepository);
    }

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(
        CustomerRepository customerRepository
    ) {
        return new UpdateCustomerUseCase(customerRepository);
    }

    @Bean
    public GetCustomerUseCase getCustomerUseCase(
        CustomerRepository customerRepository
    ) {
        return new GetCustomerUseCase(customerRepository);
    }

    @Bean
    public DeleteCustomerUseCase deleteCustomerUseCase(
        CustomerRepository customerRepository
    ) {
        return new DeleteCustomerUseCase(customerRepository);
    }
}
