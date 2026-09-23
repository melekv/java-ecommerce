package com.melek.ecommerce.customer.infrastructure.web;

import com.melek.ecommerce.catalog.product.application.dto.CustomerResponse;
import com.melek.ecommerce.customer.application.CreateCustomerUseCase;
import com.melek.ecommerce.customer.application.DeleteCustomerUseCase;
import com.melek.ecommerce.customer.application.GetCustomerUseCase;
import com.melek.ecommerce.customer.application.UpdateCustomerUseCase;
import com.melek.ecommerce.customer.application.dto.CreateCustomerRequest;
import com.melek.ecommerce.customer.application.dto.UpdateCustomerRequest;
import com.melek.ecommerce.customer.domain.model.Customer;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final CustomerResponseMapper mapper;

    public CustomerController(
        CreateCustomerUseCase createCustomerUseCase,
        UpdateCustomerUseCase updateCustomerUseCase,
        GetCustomerUseCase getCustomerUseCase,
        DeleteCustomerUseCase deleteCustomerUseCase,
        CustomerResponseMapper mapper
    ) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(
        @Valid @RequestBody CreateCustomerRequest request
    ) {
        Customer customer = createCustomerUseCase.execute(
            request.firstName(),
            request.lastName(),
            request.email()
        );

        return mapper.map(customer);
    }

    @PutMapping("/{id}")
    public CustomerResponse create(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateCustomerRequest request
    ) {
        Customer customer = updateCustomerUseCase.execute(
            new CustomerId(id),
            request.firstName(),
            request.lastName(),
            request.email()
        );

        return mapper.map(customer);
    }

    @GetMapping("/{id}")
    public CustomerResponse create(
        @PathVariable UUID id
    ) {
        Customer customer = getCustomerUseCase.execute(
            new CustomerId(id)
        );

        return mapper.map(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable UUID id
    ) {
        deleteCustomerUseCase.execute(
            new CustomerId(id)
        );
    }
}
