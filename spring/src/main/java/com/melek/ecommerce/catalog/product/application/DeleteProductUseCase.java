package com.melek.ecommerce.catalog.product.application;

import com.melek.ecommerce.catalog.product.application.exception.ProductNotFoundException;
import com.melek.ecommerce.catalog.product.application.port.ProductEventPublisher;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.shared.messaging.event.ProductDeletedEvent;


public class DeleteProductUseCase {

    private final ProductRepository productRepository;
    private final ProductEventPublisher productEventPublisher;

    public DeleteProductUseCase(
        ProductRepository productRepository,
        ProductEventPublisher productEventPublisher
    ) {
        this.productRepository = productRepository;
        this.productEventPublisher = productEventPublisher;
    }

    public void execute(ProductId id) {
        productRepository.findById(id)
            .orElseThrow(
                () -> new ProductNotFoundException(id)
            );

        productRepository.delete(id);

        productEventPublisher.publish(
            new ProductDeletedEvent(
                id.value()
            )
        );
    }
}
