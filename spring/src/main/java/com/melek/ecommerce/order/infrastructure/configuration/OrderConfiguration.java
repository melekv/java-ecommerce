package com.melek.ecommerce.order.infrastructure.configuration;

import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.order.application.ConfirmOrderUseCase;
import com.melek.ecommerce.order.application.CreateOrderUseCase;
import com.melek.ecommerce.order.application.port.ProductCatalog;
import com.melek.ecommerce.order.domain.repository.OrderRepository;
import com.melek.ecommerce.order.infrastructure.catalog.ProductCatalogAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfiguration {

    @Bean
    public ProductCatalog productCatalog(
        ProductRepository productRepository
    ) {
        return new ProductCatalogAdapter(productRepository);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase(
        OrderRepository orderRepository,
        ProductCatalog productCatalog
    ) {
        return new CreateOrderUseCase(orderRepository, productCatalog);
    }

    @Bean
    public ConfirmOrderUseCase confirmOrderUseCase(
        OrderRepository orderRepository
    ) {
        return new ConfirmOrderUseCase(orderRepository);
    }
}
