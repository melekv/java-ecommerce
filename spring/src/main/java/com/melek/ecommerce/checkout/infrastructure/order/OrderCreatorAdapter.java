package com.melek.ecommerce.checkout.infrastructure.order;

import com.melek.ecommerce.cart.domain.model.CartItem;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.checkout.application.port.OrderCreator;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import com.melek.ecommerce.order.domain.model.OrderItem;
import com.melek.ecommerce.order.domain.model.OrderItemId;
import com.melek.ecommerce.order.domain.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderCreatorAdapter implements OrderCreator {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    public OrderCreatorAdapter(
        ProductRepository productRepository,
        OrderRepository orderRepository
    ) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(CustomerId customerId, List<CartItem> items) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item : items) {
            Product product = productRepository.findById(item.getProductId())
                .orElseThrow();

            orderItems.add(
                new OrderItem(
                    OrderItemId.generate(),
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    item.getQuantity()
                )
            );
        }

        Order order = new Order(
            OrderId.generate(),
            customerId.value(),
            orderItems
        );

        return orderRepository.save(order);
    }
}
