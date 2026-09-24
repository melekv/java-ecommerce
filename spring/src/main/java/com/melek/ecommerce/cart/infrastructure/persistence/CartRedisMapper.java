package com.melek.ecommerce.cart.infrastructure.persistence;

import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.cart.domain.model.CartId;
import com.melek.ecommerce.cart.domain.model.CartItem;
import com.melek.ecommerce.cart.domain.model.CartItemId;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import com.melek.ecommerce.shared.domain.model.Money;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class CartRedisMapper {

    public CartRedisData toData(Cart cart) {
        return new CartRedisData(
            cart.getId().value(),
            cart.getCustomerId().value(),
            cart.getItems()
                .stream()
                .map(item -> new CartItemRedisData(
                    item.getId().value(),
                    item.getProductId().value(),
                    item.getPrice().amount(),
                    item.getPrice().currency().getCurrencyCode(),
                    item.getQuantity()
                )).toList()
        );
    }

    public Cart toDomain(CartRedisData data) {
        return new Cart(
            new CartId(data.id()),
            new CustomerId(data.customerId()),
            data.items()
                .stream()
                .map(item -> new CartItem(
                    new CartItemId(item.id()),
                    new ProductId(item.productId()),
                    Money.of(
                        item.price(),
                        Currency.getInstance(item.currency())
                    ),
                    item.quantity()
                ))
                .toList()
        );
    }
}
