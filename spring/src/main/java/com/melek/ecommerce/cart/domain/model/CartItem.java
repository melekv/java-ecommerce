package com.melek.ecommerce.cart.domain.model;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.shared.domain.model.Money;

public class CartItem {

    private final CartItemId id;

    private final ProductId productId;

    private final Money price;

    private int quantity;

    public CartItem(
        ProductId productId,
        Money price,
        int quantity
    ) {
        if (productId == null) {
            throw new IllegalArgumentException("Product id cannot be null");
        }

        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be less than or equal to zero");
        }

        this.id = CartItemId.generate();
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public void increaseQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be less than or equal to zero");
        }

        this.quantity += quantity;
    }

    public void decreaseQuantity(int quantity) {
        if (quantity <= 0 || quantity > this.quantity) {
            throw new IllegalArgumentException("Quantity cannot be less than or equal to zero or greater than current quantity");
        }

        this.quantity -= quantity;
    }

    public CartItemId getId() {
        return id;
    }

    public ProductId getProductId() {
        return productId;
    }

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
