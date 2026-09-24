package com.melek.ecommerce.cart.infrastructure.configuration;

import com.melek.ecommerce.cart.application.*;
import com.melek.ecommerce.cart.application.port.ProductCatalog;
import com.melek.ecommerce.cart.domain.repository.CartRepository;
import com.melek.ecommerce.cart.infrastructure.persistence.CartRedisMapper;
import com.melek.ecommerce.cart.infrastructure.persistence.RedisCartRepository;
import com.melek.ecommerce.cart.infrastructure.web.CartResponseMapper;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.cart.infrastructure.catalog.ProductCatalogAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class CartConfiguration {

    @Bean
    public ProductCatalog cartProductCatalog(
        ProductRepository productRepository
    ) {
        return new ProductCatalogAdapter(productRepository);
    }

    @Bean
    public CartRedisMapper cartRedisMapper() {
        return new CartRedisMapper();
    }

    @Bean
    public CartResponseMapper cartResponseMapper() {
        return new CartResponseMapper();
    }

    @Bean
    public CartRepository cartRepository(
        StringRedisTemplate stringRedisTemplate,
        CartRedisMapper cartRedisMapper,
        ObjectMapper objectMapper
    ) {
        return new RedisCartRepository(
            stringRedisTemplate,
            cartRedisMapper,
            objectMapper
        );
    }

    @Bean
    public AddItemToCartUseCase addItemToCartUseCase(
        CartRepository cartRepository,
        ProductCatalog productCatalog
    ) {
        return new AddItemToCartUseCase(
            cartRepository,
            productCatalog
        );
    }

    @Bean
    public GetCartUseCase getCartUseCase(CartRepository cartRepository) {
        return new GetCartUseCase(cartRepository);
    }

    @Bean
    public RemoveItemFromCartUseCase removeItemFromCartUseCase(CartRepository cartRepository) {
        return new RemoveItemFromCartUseCase(cartRepository);
    }

    @Bean
    public DecreaseCartItemQuantityUseCase decreaseCartItemQuantityUseCase(
        CartRepository cartRepository
    ) {
        return new DecreaseCartItemQuantityUseCase(cartRepository);
    }

    @Bean
    public ClearCartUseCase clearCartUseCase(CartRepository cartRepository) {
        return new ClearCartUseCase(cartRepository);
    }
}
