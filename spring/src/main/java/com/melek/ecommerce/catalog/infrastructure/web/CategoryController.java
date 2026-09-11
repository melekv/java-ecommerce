package com.melek.ecommerce.catalog.infrastructure.web;

import com.melek.ecommerce.catalog.application.*;
import com.melek.ecommerce.catalog.application.dto.CategoryResponse;
import com.melek.ecommerce.catalog.application.dto.CreateCategoryRequest;
import com.melek.ecommerce.catalog.application.dto.UpdateCategoryRequest;
import com.melek.ecommerce.catalog.domain.model.Category;
import com.melek.ecommerce.catalog.domain.model.CategoryId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoriesUseCase getCategoriesUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final CategoryResponseMapper mapper;

    public CategoryController(
        CreateCategoryUseCase createCategoryUseCase,
        GetCategoriesUseCase getCategoriesUseCase,
        GetCategoryUseCase getCategoryUseCase,
        UpdateCategoryUseCase updateCategoryUseCase,
        DeleteCategoryUseCase deleteCategoryUseCase,
        CategoryResponseMapper mapper
    ) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.getCategoriesUseCase = getCategoriesUseCase;
        this.getCategoryUseCase = getCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(
        @Valid @RequestBody CreateCategoryRequest request
    ) {
        Category category = createCategoryUseCase.execute(request.name());

        return mapper.map(category);
    }

    @GetMapping
    public List<CategoryResponse> getAll() {
        List<Category> categories = getCategoriesUseCase.execute();

        return categories.stream()
            .map(mapper::map)
            .toList();
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable UUID id) {
        Category category = getCategoryUseCase.execute(
            new CategoryId(id)
        );

        return mapper.map(category);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateCategoryRequest request
    ) {
        Category category = updateCategoryUseCase.execute(
            new CategoryId(id),
            request.name()
        );

        return mapper.map(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        deleteCategoryUseCase.execute(new CategoryId(id));
    }
}
