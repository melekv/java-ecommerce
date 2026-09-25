package com.melek.ecommerce.order.infrastructure.configuration;

import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.order.application.*;
import com.melek.ecommerce.order.application.port.PaymentGateway;
import com.melek.ecommerce.order.application.port.ProductCatalog;
import com.melek.ecommerce.order.domain.repository.OrderRepository;
import com.melek.ecommerce.order.infrastructure.catalog.ProductCatalogAdapter;
import com.melek.ecommerce.order.infrastructure.payment.FakePaymentGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfiguration {

    @Bean
    public ProductCatalog orderProductCatalog(
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

    @Bean
    public CancelOrderUseCase cancelOrderUseCase(
        OrderRepository orderRepository
    ) {
        return new CancelOrderUseCase(orderRepository);
    }

    @Bean
    public PayOrderUseCase payOrderUseCase(
        OrderRepository orderRepository,
        PaymentGateway paymentGateway
    ) {
        return new PayOrderUseCase(orderRepository, paymentGateway);
    }

    @Bean
    public ShipOrderUseCase shipOrderUseCase(
        OrderRepository orderRepository
    ) {
        return new ShipOrderUseCase(orderRepository);
    }

    @Bean
    public DeliverOrderUseCase deliverOrderUseCase(
        OrderRepository orderRepository
    ) {
        return new DeliverOrderUseCase(orderRepository);
    }

    @Bean
    public GetOrderUseCase getOrderUseCase(
        OrderRepository orderRepository
    ) {
        return new GetOrderUseCase(orderRepository);
    }

    @Bean
    public PaymentGateway paymentGateway() {
        return new FakePaymentGateway();
    }
}
