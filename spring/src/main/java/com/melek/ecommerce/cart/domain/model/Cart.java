package com.melek.ecommerce.cart.domain.model;

import com.melek.ecommerce.cart.application.exception.CartItemNotFoundException;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.customer.domain.model.CustomerId;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final CartId id;

    private final CustomerId customerId;

    private List<CartItem> items;

    public Cart(CustomerId customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer id cannot be null");
        }

        this.id = CartId.generate();
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public Cart(
        CartId id,
        CustomerId customerId,
        List<CartItem> items
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Cart id cannot be null");
        }

        if (customerId == null) {
            throw new IllegalArgumentException("Customer id cannot be null");
        }

        if (items == null) {
            throw new IllegalArgumentException("Items cannot be null");
        }

        this.id = id;
        this.customerId = customerId;
        this.items = new ArrayList<>(items);
    }

    public void addItem(CartItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        this.items.stream()
            .filter(existing -> existing.getProductId().equals(item.getProductId()))
            .findFirst()
            .ifPresentOrElse(
                existing -> existing.increaseQuantity(item.getQuantity()),
                () -> this.items.add(item)
            );
    }

    public void removeItem(ProductId productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        boolean removed = this.items.removeIf(item -> item.getProductId().equals(productId));

        if (!removed) {
            throw new IllegalArgumentException("Product does not exist in cart");
        }
    }

    public void clear() {
        this.items.clear();
    }

    public void decreaseItemQuantity(ProductId productId, int quantity) {
        CartItem cartItem = this.getItems()
            .stream()
            .filter(
                item -> item.getProductId()
                    .equals(productId)
            )
            .findFirst()
            .orElseThrow(CartItemNotFoundException::new);

        cartItem.decreaseQuantity(quantity);

        if (cartItem.getQuantity() ==0) {
            this.items.remove(cartItem);
        }
    }

    public CartId getId() {
        return id;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }
}
