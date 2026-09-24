package com.melek.ecommerce.cart.application;

import com.melek.ecommerce.cart.application.dto.DecreaseCartItemQuantityCommand;
import com.melek.ecommerce.cart.application.exception.CartItemNotFoundException;
import com.melek.ecommerce.cart.application.exception.CartNotFoundException;
import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.cart.domain.model.CartItem;
import com.melek.ecommerce.cart.domain.repository.CartRepository;

public class DecreaseCartItemQuantityUseCase {

    private final CartRepository cartRepository;

    public DecreaseCartItemQuantityUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart execute(DecreaseCartItemQuantityCommand command) {
        Cart cart = cartRepository.findByCustomerId(command.customerId())
            .orElseThrow(CartNotFoundException::new);

        CartItem cartItem = cart.getItems()
            .stream()
            .filter(
                item -> item.getProductId()
                    .equals(
                        command.productId()
                    )
            )
            .findFirst()
            .orElseThrow(CartItemNotFoundException::new);

        cartItem.decreaseQuantity(command.quantity());

        return cartRepository.save(cart);
    }
}
