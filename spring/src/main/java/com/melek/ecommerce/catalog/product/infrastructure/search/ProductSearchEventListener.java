package com.melek.ecommerce.catalog.product.infrastructure.search;

import com.melek.ecommerce.catalog.product.application.exception.ProductNotFoundException;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.shared.messaging.event.ProductCreatedEvent;
import com.melek.ecommerce.shared.messaging.event.ProductDeletedEvent;
import com.melek.ecommerce.shared.messaging.event.ProductUpdatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductSearchEventListener {

    private final ProductRepository productRepository;
    private final ProductSearchRepository productSearchRepository;

    public ProductSearchEventListener(
        ProductRepository productRepository,
        ProductSearchRepository productSearchRepository
    ) {
        this.productRepository = productRepository;
        this.productSearchRepository = productSearchRepository;
    }

    @RabbitListener(queues = "products.elasticsearch.created")
    public void handle(ProductCreatedEvent event) {
        Product product = productRepository.findById(
            ProductId.from(
                event.productId()
            )
        )
        .orElseThrow();

        ProductDocument document = new ProductDocument(
            product.getId().value(),
            product.getName(),
            product.getDescription(),
            product.getPrice().amount(),
            product.getPrice().currency().getCurrencyCode(),
            product.getCategoryId().value()
        );

        productSearchRepository.save(document);
    }

    @RabbitListener(queues = "products.elasticsearch.updated")
    public void handle(ProductUpdatedEvent event) {
        Product product = productRepository.findById(
                ProductId.from(
                    event.productId()
                )
            )
            .orElseThrow();

        ProductDocument document = new ProductDocument(
            product.getId().value(),
            product.getName(),
            product.getDescription(),
            product.getPrice().amount(),
            product.getPrice().currency().getCurrencyCode(),
            product.getCategoryId().value()
        );

        productSearchRepository.save(document);
    }

    @RabbitListener(queues = "products.elasticsearch.deleted")
    public void handle(ProductDeletedEvent event) {
        productSearchRepository.deleteById(event.productId());
    }
}
