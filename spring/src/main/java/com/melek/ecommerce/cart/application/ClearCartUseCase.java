package com.melek.ecommerce.cart.application;

import com.melek.ecommerce.cart.application.exception.CartNotFoundException;
import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.cart.domain.repository.CartRepository;
import com.melek.ecommerce.customer.domain.model.CustomerId;

public class ClearCartUseCase {

    private final CartRepository cartRepository;

    public ClearCartUseCase(
        CartRepository cartRepository
    ) {
        this.cartRepository = cartRepository;
    }

    public Cart execute(CustomerId customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId)
            .orElseThrow(CartNotFoundException::new);

        cart.clear();

        return cartRepository.save(cart);
    }
}
