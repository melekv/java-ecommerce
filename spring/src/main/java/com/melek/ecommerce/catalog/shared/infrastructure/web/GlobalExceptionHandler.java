package com.melek.ecommerce.catalog.shared.infrastructure.web;

import com.melek.ecommerce.catalog.category.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.product.application.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCategoryNotFoundException(CategoryNotFoundException exception) {
        return new ErrorResponse(
            "CATEGORY_NOT_FOUND",
            exception.getMessage()
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException exception) {
        return new ErrorResponse(
            "PRODUCT_NOT_FOUND",
            exception.getMessage()
        );
    }
}
