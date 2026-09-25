package com.melek.ecommerce.checkout.infrastructure.configuration;

import com.melek.ecommerce.cart.domain.repository.CartRepository;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.checkout.application.CheckoutCartUseCase;
import com.melek.ecommerce.checkout.application.port.CartCleaner;
import com.melek.ecommerce.checkout.application.port.CartProvider;
import com.melek.ecommerce.checkout.application.port.OrderCreator;
import com.melek.ecommerce.checkout.infrastructure.cart.CartCleanerAdapter;
import com.melek.ecommerce.checkout.infrastructure.cart.CartProviderAdapter;
import com.melek.ecommerce.checkout.infrastructure.order.OrderCreatorAdapter;
import com.melek.ecommerce.order.domain.repository.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckoutConfiguration {

    @Bean
    public CartProvider cartProvider(
        CartRepository repository
    ) {
        return new CartProviderAdapter(repository);
    }

    @Bean
    public OrderCreator orderCreator(
        ProductRepository productRepository,
        OrderRepository orderRepository
    ) {
        return new OrderCreatorAdapter(productRepository, orderRepository);
    }

    @Bean
    public CartCleaner cartCleaner(
        CartRepository repository
    ) {
        return new CartCleanerAdapter(repository);
    }

    @Bean
    public CheckoutCartUseCase checkoutCartUseCase(
        CartProvider cartProvider,
        OrderCreator orderCreator,
        CartCleaner cartCleaner
    ) {
        return new CheckoutCartUseCase(cartProvider, orderCreator, cartCleaner);
    }
}
