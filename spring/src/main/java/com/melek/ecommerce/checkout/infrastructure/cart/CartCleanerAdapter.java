package com.melek.ecommerce.checkout.infrastructure.cart;

import com.melek.ecommerce.cart.domain.repository.CartRepository;
import com.melek.ecommerce.checkout.application.port.CartCleaner;
import com.melek.ecommerce.customer.domain.model.CustomerId;

public class CartCleanerAdapter implements CartCleaner {

    private final CartRepository cartRepository;

    public CartCleanerAdapter(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void clear(CustomerId customerId) {
        cartRepository.findByCustomerId(customerId)
            .ifPresent(cart -> {
                cart.clear();
                cartRepository.save(cart);
            });
    }
}
