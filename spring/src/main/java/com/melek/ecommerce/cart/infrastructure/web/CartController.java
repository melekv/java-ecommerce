package com.melek.ecommerce.cart.infrastructure.web;

import com.melek.ecommerce.cart.application.AddItemToCartUseCase;
import com.melek.ecommerce.cart.application.dto.AddItemToCartCommand;
import com.melek.ecommerce.cart.application.dto.AddItemToCartRequest;
import com.melek.ecommerce.cart.application.dto.CartResponse;
import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final AddItemToCartUseCase addItemToCartUseCase;

    private final CartResponseMapper mapper;

    public CartController(
        AddItemToCartUseCase addItemToCartUseCase,
        CartResponseMapper mapper
    ) {
        this.addItemToCartUseCase = addItemToCartUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/{customerId}/items")
    public CartResponse addItem(
        @PathVariable UUID customerId,
        @RequestBody AddItemToCartRequest request
    ) {
        AddItemToCartCommand command = new AddItemToCartCommand(
            new CustomerId(customerId),
            new ProductId(request.productId()),
            request.quantity()
        );

        Cart cart = addItemToCartUseCase.execute(command);

        return mapper.map(cart);
    }
}
