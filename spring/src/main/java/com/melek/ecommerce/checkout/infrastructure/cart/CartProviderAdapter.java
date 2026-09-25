package com.melek.ecommerce.checkout.infrastructure.cart;

import com.melek.ecommerce.cart.application.exception.CartNotFoundException;
import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.cart.domain.repository.CartRepository;
import com.melek.ecommerce.checkout.application.port.CartProvider;
import com.melek.ecommerce.customer.domain.model.CustomerId;

public class CartProviderAdapter implements CartProvider {

    private final CartRepository cartRepository;

    public CartProviderAdapter(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart findByCustomerId(CustomerId customerId) {
        return cartRepository.findByCustomerId(customerId)
            .orElseThrow(CartNotFoundException::new);
    }
}
