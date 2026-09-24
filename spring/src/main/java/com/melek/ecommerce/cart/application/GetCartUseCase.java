package com.melek.ecommerce.cart.application;

import com.melek.ecommerce.cart.application.exception.CartNotFoundException;
import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.cart.domain.repository.CartRepository;
import com.melek.ecommerce.customer.domain.model.CustomerId;

public class GetCartUseCase {

    private final CartRepository cartRepository;

    public GetCartUseCase(
        CartRepository cartRepository
    ) {
        this.cartRepository = cartRepository;
    }

    public Cart execute(CustomerId customerId) {
        return cartRepository.findByCustomerId(customerId)
            .orElseThrow(CartNotFoundException::new);
    }
}
