package com.melek.ecommerce.catalog.product.infrastructure.web;

import com.melek.ecommerce.catalog.product.application.*;
import com.melek.ecommerce.catalog.product.application.dto.CreateProductRequest;
import com.melek.ecommerce.catalog.product.application.dto.ProductResponse;
import com.melek.ecommerce.catalog.product.application.dto.UpdateProductRequest;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final GetProductsUseCase getProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final ProductResponseMapper mapper;

    public ProductController(
        CreateProductUseCase createProductUseCase,
        GetProductUseCase getProductUseCase,
        GetProductsUseCase getProductsUseCase,
        UpdateProductUseCase updateProductUseCase,
        DeleteProductUseCase deleteProductUseCase,
        ProductResponseMapper mapper
    ) {
        this.createProductUseCase = createProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.getProductsUseCase = getProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(
        @Valid @RequestBody CreateProductRequest request
    ) {
        Product product = createProductUseCase.execute(
            request.name(),
            request.description(),
            request.price(),
            request.currency(),
            new CategoryId(request.categoryId())
        );

        return mapper.map(product);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        List<Product> products = getProductsUseCase.execute();

        return products.stream()
            .map(mapper::map)
            .toList();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable UUID id) {
        Product product = getProductUseCase.execute(
            new ProductId(id)
        );

        return mapper.map(product);
    }

    @PutMapping("/{id}")
    public ProductResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateProductRequest request
    ) {
        Product product = updateProductUseCase.execute(
            new ProductId(id),
            request.name(),
            request.description(),
            request.price(),
            request.currency(),
            new CategoryId(request.categoryId())
        );

        return mapper.map(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        deleteProductUseCase.execute(new ProductId(id));
    }

}
