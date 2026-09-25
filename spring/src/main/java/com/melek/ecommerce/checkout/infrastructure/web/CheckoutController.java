package com.melek.ecommerce.checkout.infrastructure.web;

import com.melek.ecommerce.checkout.application.CheckoutCartUseCase;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import com.melek.ecommerce.order.application.dto.OrderResponse;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.infrastructure.web.OrderResponseMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/checkout")
public class CheckoutController {

    private final CheckoutCartUseCase checkoutCartUseCase;

    private final OrderResponseMapper mapper;

    public CheckoutController(
        CheckoutCartUseCase checkoutCartUseCase,
        OrderResponseMapper mapper
    ) {
        this.checkoutCartUseCase = checkoutCartUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/{customerId}")
    public OrderResponse checkout(@PathVariable UUID customerId) {
        Order order = checkoutCartUseCase.execute(new CustomerId(customerId));

        return mapper.map(order);
    }
}
