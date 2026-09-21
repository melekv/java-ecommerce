package com.melek.ecommerce.order.infrastructure.web;

import com.melek.ecommerce.order.application.ConfirmOrderUseCase;
import com.melek.ecommerce.order.application.dto.OrderResponse;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final ConfirmOrderUseCase confirmOrderUseCase;
    private final OrderResponseMapper mapper;

    public OrderController(
        ConfirmOrderUseCase confirmOrderUseCase,
        OrderResponseMapper mapper
    ) {
        this.confirmOrderUseCase = confirmOrderUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/{id}/confirm")
    public OrderResponse confirm(@PathVariable UUID id) {
        Order order = confirmOrderUseCase.execute(
            new OrderId(id)
        );

        return mapper.map(order);
    }
}
