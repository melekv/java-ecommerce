package com.melek.ecommerce.catalog.infrastructure;

import com.melek.ecommerce.catalog.category.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.catalog.product.domain.model.Money;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.catalog.product.infrastructure.web.ProductController;
import com.melek.ecommerce.catalog.product.infrastructure.web.ProductResponseMapper;
import com.melek.ecommerce.catalog.product.application.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import(ProductResponseMapper.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateProductUseCase createProductUseCase;

    @MockitoBean
    private GetProductUseCase getProductUseCase;

    @MockitoBean
    private GetProductsUseCase getProductsUseCase;

    @MockitoBean
    private UpdateProductUseCase updateProductUseCase;

    @MockitoBean
    private DeleteProductUseCase deleteProductUseCase;

    @Test
    public void Should_Create_Product() throws Exception {

        CategoryId categoryId = CategoryId.generate();

        Product product = new Product(
            ProductId.generate(),
            "MacBook Pro",
            "Apple laptop",
            Money.of(
                BigDecimal.valueOf(9999),
                Currency.getInstance("PLN")
            ),
            categoryId
        );

        when(createProductUseCase.execute(
            eq("MacBook Pro"),
            eq("Apple laptop"),
            eq(BigDecimal.valueOf(9999)),
            eq(Currency.getInstance("PLN")),
            eq(categoryId)
        )).thenReturn(product);

        var request = """
            {
                "name": "MacBook Pro",
                "description": "Apple laptop",
                "price": 9999,
                "currency": "PLN",
                "categoryId": "%s"
            }
            """.formatted(categoryId.value());

        mockMvc.perform(
            post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(product.getId().value().toString()));
    }

    @Test
    public void shouldRejectProductWithEmptyName() throws Exception {

        var request = """
            {
                "name": "",
                "description": "Apple laptop",
                "price": 9999,
                "currency": "PLN",
                "categoryId": "550e8400-e29b-41d4-a716-446655440000"
            }
            """;

        mockMvc.perform(
                post("/api/v1/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request)
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldRejectProductWithNegativePrice() throws Exception {

        var request = """
            {
                "name": "MacBook Pro",
                "description": "Apple laptop",
                "price": -100,
                "currency": "PLN",
                "categoryId": "550e8400-e29b-41d4-a716-446655440000"
            }
            """;

        mockMvc.perform(
            post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void Should_Reject_Product_When_Category_Not_Found() throws Exception {

        CategoryId categoryId = CategoryId.generate();

        when(createProductUseCase.execute(
            eq("MacBook Pro"),
            eq("Apple laptop"),
            eq(BigDecimal.valueOf(9999)),
            eq(Currency.getInstance("PLN")),
            eq(categoryId)
        )).thenThrow(new CategoryNotFoundException(categoryId));

        var request = """
            {
                "name": "MacBook Pro",
                "description": "Apple laptop",
                "price": 9999,
                "currency": "PLN",
                "categoryId": "%s"
            }
            """.formatted(categoryId.value());

        mockMvc.perform(
                post("/api/v1/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request)
            )
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.code").value("CATEGORY_NOT_FOUND"));
    }

    @Test
    public void Should_Return_A_Product() throws Exception {
        ProductId productId = ProductId.generate();
        CategoryId categoryId = CategoryId.generate();

        Product product = new Product(
            productId,
            "MacBook Pro",
            "Apple laptop",
            Money.of(
                BigDecimal.valueOf(9999),
                Currency.getInstance("PLN")
            ),
            categoryId
        );

        when(getProductUseCase.execute(productId))
            .thenReturn(product);

        mockMvc.perform(
            get("/api/v1/products/" + productId.value())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(productId.value().toString()))
            .andExpect(jsonPath("$.name").value("MacBook Pro"))
            .andExpect(jsonPath("$.description").value("Apple laptop"))
            .andExpect(jsonPath("$.price").value(9999))
            .andExpect(jsonPath("$.currency").value("PLN"))
            .andExpect(jsonPath("$.categoryId").value(categoryId.value().toString()));
    }

    @Test
    public void Should_Return_A_List_Of_Products() throws Exception {
        CategoryId categoryId = CategoryId.generate();

        Product product1 = new Product(
            ProductId.generate(),
            "MacBook Pro",
            "Apple laptop",
            Money.of(
                BigDecimal.valueOf(9999),
                Currency.getInstance("PLN")
            ),
            categoryId
        );

        Product product2 = new Product(
            ProductId.generate(),
            "iPhone",
            "Apple smartphone",
            Money.of(
                BigDecimal.valueOf(4999),
                Currency.getInstance("PLN")
            ),
            categoryId
        );

        when(getProductsUseCase.execute())
            .thenReturn(List.of(product1, product2));

        mockMvc.perform(
            get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())
            .andExpect((jsonPath("$").isArray()))
            .andExpect((jsonPath("$.length()").value(2)))
            .andExpect(jsonPath("$[0].id").value(product1.getId().value().toString()))
            .andExpect(jsonPath("$[0].name").value("MacBook Pro"))
            .andExpect(jsonPath("$[1].id").value(product2.getId().value().toString()))
            .andExpect(jsonPath("$[1].name").value("iPhone"));
    }
}
