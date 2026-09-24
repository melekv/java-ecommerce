package com.melek.ecommerce.cart.application.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException() {
        super("Cart item not found");
    }
}
