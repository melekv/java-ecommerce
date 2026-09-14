package com.melek.ecommerce.order.domain.model;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.shared.domain.model.Money;

public class OrderItem {

    private final ProductId productId;
    private final String productName;
    private final Money unitPrice;
    private final int quantity;

    public OrderItem(
        ProductId productId,
        String productName,
        Money unitPrice,
        int quantity
    ) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public ProductId getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money total() {
        return unitPrice.multiply(quantity);
    }
}
