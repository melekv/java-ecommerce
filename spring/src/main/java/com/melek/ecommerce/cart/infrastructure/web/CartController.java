package com.melek.ecommerce.cart.infrastructure.web;

import com.melek.ecommerce.cart.application.*;
import com.melek.ecommerce.cart.application.dto.*;
import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final AddItemToCartUseCase addItemToCartUseCase;
    private final GetCartUseCase getCartUseCase;
    private final RemoveItemFromCartUseCase removeItemFromCartUseCase;
    private final DecreaseCartItemQuantityUseCase decreaseItemFromCartUseCase;
    private final ClearCartUseCase clearCartUseCase;
    private final CartResponseMapper mapper;

    public CartController(
        AddItemToCartUseCase addItemToCartUseCase,
        GetCartUseCase getCartUseCase,
        RemoveItemFromCartUseCase removeItemFromCartUseCase,
        DecreaseCartItemQuantityUseCase decreaseItemFromCartUseCase,
        ClearCartUseCase clearCartUseCase,
        CartResponseMapper mapper
    ) {
        this.addItemToCartUseCase = addItemToCartUseCase;
        this.getCartUseCase = getCartUseCase;
        this.removeItemFromCartUseCase = removeItemFromCartUseCase;
        this.decreaseItemFromCartUseCase = decreaseItemFromCartUseCase;
        this.clearCartUseCase = clearCartUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/{customerId}/items")
    public CartResponse addItem(
        @PathVariable UUID customerId,
        @Valid @RequestBody AddItemToCartRequest request
    ) {
        AddItemToCartCommand command = new AddItemToCartCommand(
            new CustomerId(customerId),
            new ProductId(request.productId()),
            request.quantity()
        );

        Cart cart = addItemToCartUseCase.execute(command);

        return mapper.map(cart);
    }

    @GetMapping("/{customerId}")
    public CartResponse getCart(@PathVariable UUID customerId) {
        Cart cart = getCartUseCase.execute(
            new CustomerId(customerId)
        );

        return mapper.map(cart);
    }

    @DeleteMapping("/{customerId}/items/{productId}")
    public CartResponse removeItem(
        @PathVariable UUID customerId,
        @PathVariable UUID productId
    ) {
        RemoveItemFromCartCommand command = new RemoveItemFromCartCommand(
            new CustomerId(customerId),
            new ProductId(productId)
        );

        Cart cart = removeItemFromCartUseCase.execute(command);

        return mapper.map(cart);
    }

    @DeleteMapping("/{customerId}/items/")
    public CartResponse clearCart(@PathVariable UUID customerId) {
        Cart cart = clearCartUseCase.execute(
            new CustomerId(customerId)
        );

        return mapper.map(cart);
    }

    @PatchMapping("/{customerId}/items/{productId}")
    public CartResponse decreaseItem(
        @PathVariable UUID customerId,
        @PathVariable UUID productId
    ) {
        DecreaseCartItemQuantityCommand command = new DecreaseCartItemQuantityCommand(
            new CustomerId(customerId),
            new ProductId(productId),
            1
        );

        Cart cart = decreaseItemFromCartUseCase.execute(command);

        return mapper.map(cart);
    }
}
