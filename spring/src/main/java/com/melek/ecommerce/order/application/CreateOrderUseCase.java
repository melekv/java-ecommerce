package com.melek.ecommerce.order.application;

import com.melek.ecommerce.order.application.dto.OrderItemRequest;
import com.melek.ecommerce.order.application.port.ProductCatalog;
import com.melek.ecommerce.order.application.port.ProductData;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import com.melek.ecommerce.order.domain.model.OrderItem;
import com.melek.ecommerce.order.domain.model.OrderItemId;
import com.melek.ecommerce.order.domain.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final ProductCatalog productCatalog;

    public CreateOrderUseCase(
        OrderRepository orderRepository,
        ProductCatalog productCatalog
    ) {
        this.orderRepository = orderRepository;
        this.productCatalog = productCatalog;
    }

    public Order execute(
        UUID customerId,
        List<OrderItemRequest> items
    ) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest item : items) {
            ProductData product = productCatalog.findById(item.id())
                    .orElseThrow();

            orderItems.add(new OrderItem(
                OrderItemId.generate(),
                product.id(),
                product.name(),
                product.price(),
                item.quantity()
            ));
        }

        Order order = new Order(
            OrderId.generate(),
            customerId,
            orderItems
        );

        return orderRepository.save(order);
    }
}
