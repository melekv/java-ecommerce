package com.melek.ecommerce.order.infrastructure.web;

import com.melek.ecommerce.order.application.*;
import com.melek.ecommerce.order.application.dto.CreateOrderRequest;
import com.melek.ecommerce.order.application.dto.OrderResponse;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final ConfirmOrderUseCase confirmOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final PayOrderUseCase payOrderUseCase;
    private final ShipOrderUseCase shipOrderUseCase;
    private final DeliverOrderUseCase deliverOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final OrderResponseMapper mapper;

    public OrderController(
        CreateOrderUseCase createOrderUseCase,
        ConfirmOrderUseCase confirmOrderUseCase,
        CancelOrderUseCase cancelOrderUseCase,
        PayOrderUseCase payOrderUseCase,
        ShipOrderUseCase shipOrderUseCase,
        DeliverOrderUseCase deliverOrderUseCase,
        GetOrderUseCase getOrderUseCase,
        OrderResponseMapper mapper
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.confirmOrderUseCase = confirmOrderUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.payOrderUseCase = payOrderUseCase;
        this.shipOrderUseCase = shipOrderUseCase;
        this.deliverOrderUseCase = deliverOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(
        @Valid @RequestBody CreateOrderRequest request
    ) {
        Order order = createOrderUseCase.execute(
            request.customerId(),
            request.items()
        );

        return mapper.map(order);
    }

    @PostMapping("/{id}/confirm")
    public OrderResponse confirm(@PathVariable UUID id) {
        Order order = confirmOrderUseCase.execute(
            new OrderId(id)
        );

        return mapper.map(order);
    }

    @PostMapping("/{id}/cancel")
    public OrderResponse cancel(@PathVariable UUID id) {
        Order order = cancelOrderUseCase.execute(
            new OrderId(id)
        );

        return mapper.map(order);
    }

    @PostMapping("/{id}/pay")
    public OrderResponse pay(@PathVariable UUID id) {
        Order order = payOrderUseCase.execute(
            new OrderId(id)
        );

        return mapper.map(order);
    }

    @PostMapping("/{id}/ship")
    public OrderResponse ship(@PathVariable UUID id) {
        Order order = shipOrderUseCase.execute(
            new OrderId(id)
        );

        return mapper.map(order);
    }

    @PostMapping("/{id}/deliver")
    public OrderResponse deliver(@PathVariable UUID id) {
        Order order = deliverOrderUseCase.execute(
            new OrderId(id)
        );

        return mapper.map(order);
    }

    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable UUID id) {
        Order order = getOrderUseCase.execute(
            new OrderId(id)
        );

        return mapper.map(order);
    }
}
