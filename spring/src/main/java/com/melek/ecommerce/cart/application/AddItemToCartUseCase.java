package com.melek.ecommerce.cart.application;

import com.melek.ecommerce.cart.application.dto.AddItemToCartCommand;
import com.melek.ecommerce.cart.application.port.ProductCatalog;
import com.melek.ecommerce.cart.application.port.ProductData;
import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.cart.domain.model.CartItem;
import com.melek.ecommerce.cart.domain.repository.CartRepository;

public class AddItemToCartUseCase {

    private final CartRepository cartRepository;

    private final ProductCatalog productCatalog;

    public AddItemToCartUseCase(
        CartRepository cartRepository,
        ProductCatalog productCatalog
    ) {
        this.cartRepository = cartRepository;
        this.productCatalog = productCatalog;
    }

    public Cart execute(AddItemToCartCommand command) {
        ProductData productData = productCatalog.findById(command.productId());

        Cart cart = cartRepository.findByCustomerId(command.customerId())
            .orElse(
                new Cart(command.customerId())
            );

        CartItem item = new CartItem(
            productData.id(),
            productData.price(),
            command.quantity()
        );

        cart.addItem(item);

        return cartRepository.save(cart);
    }
}
