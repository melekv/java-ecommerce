package com.melek.ecommerce.checkout.application;

import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.checkout.application.port.CartCleaner;
import com.melek.ecommerce.checkout.application.port.CartProvider;
import com.melek.ecommerce.checkout.application.port.OrderCreator;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import com.melek.ecommerce.order.domain.model.Order;

public class CheckoutCartUseCase {

    private final CartProvider cartProvider;

    private final OrderCreator orderCreator;

    protected final CartCleaner cartCleaner;

    public CheckoutCartUseCase(
        CartProvider cartProvider,
        OrderCreator orderCreator,
        CartCleaner cartCleaner
    ) {
        this.cartProvider = cartProvider;
        this.orderCreator = orderCreator;
        this.cartCleaner = cartCleaner;
    }

    public Order execute(CustomerId customerId) {
        Cart cart = cartProvider.findByCustomerId(customerId);

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot checkout empty cart");
        }

        Order order = orderCreator.create(
            customerId,
            cart.getItems()
        );

        cartCleaner.clear(customerId);

        return order;
    }
}
