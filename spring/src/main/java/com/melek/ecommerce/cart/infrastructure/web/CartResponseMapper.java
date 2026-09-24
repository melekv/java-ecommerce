package com.melek.ecommerce.cart.infrastructure.web;

import com.melek.ecommerce.cart.application.dto.CartItemResponse;
import com.melek.ecommerce.cart.application.dto.CartResponse;
import com.melek.ecommerce.cart.domain.model.Cart;

public class CartResponseMapper {

    public CartResponse map(Cart cart) {
        return new CartResponse(
            cart.getId().value(),
            cart.getCustomerId().value(),
            cart.getItems()
                .stream()
                .map(item -> new CartItemResponse(
                    item.getId().value(),
                    item.getProductId().value(),
                    item.getPrice().amount(),
                    item.getPrice().currency().getCurrencyCode(),
                    item.getQuantity()
                ))
                .toList()
        );
    }
}
