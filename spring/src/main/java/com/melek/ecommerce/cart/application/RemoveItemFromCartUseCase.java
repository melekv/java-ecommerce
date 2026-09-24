package com.melek.ecommerce.cart.application;

import com.melek.ecommerce.cart.application.dto.RemoveItemFromCartCommand;
import com.melek.ecommerce.cart.application.exception.CartNotFoundException;
import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.cart.domain.repository.CartRepository;

public class RemoveItemFromCartUseCase {

    private final CartRepository cartRepository;

    public RemoveItemFromCartUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart execute(RemoveItemFromCartCommand command) {
        Cart cart = cartRepository.findByCustomerId(command.customerId())
            .orElseThrow(CartNotFoundException::new);

        cart.removeItem(command.productId());

        return cartRepository.save(cart);
    }
}
