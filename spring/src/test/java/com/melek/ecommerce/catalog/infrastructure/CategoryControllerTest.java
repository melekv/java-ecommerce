package com.melek.ecommerce.catalog.infrastructure;

import com.melek.ecommerce.catalog.category.application.*;
import com.melek.ecommerce.catalog.category.domain.model.Category;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.catalog.category.infrastructure.web.CategoryController;
import com.melek.ecommerce.catalog.category.infrastructure.web.CategoryResponseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
@Import(CategoryResponseMapper.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateCategoryUseCase createCategoryUseCase;

    @MockitoBean
    private GetCategoryUseCase getCategoryUseCase;

    @MockitoBean
    private GetCategoriesUseCase getCategoriesUseCase;

    @MockitoBean
    private UpdateCategoryUseCase updateCategoryUseCase;

    @MockitoBean
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @Test
    public void Should_Create_Category() throws Exception {

        Category category = new Category(
            CategoryId.generate(),
            "Food"
        );

        when(createCategoryUseCase.execute(
            eq("Food")
        )).thenReturn(category);

        var request = """
            {
                "name": "Food"
            }
            """;

        mockMvc.perform(
            post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(category.getId().value().toString()));
    }

    @Test
    void shouldRejectCategoryWithEmptyName() throws Exception {

        var request = """
            {
                "name": "",
            }
            """;

        mockMvc.perform(
                post("/api/v1/categories")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request)
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    public void Should_Return_A_Category() throws Exception {
        CategoryId categoryId = CategoryId.generate();

        Category product = new Category(
            categoryId,
            "Food"
        );

        when(getCategoryUseCase.execute(categoryId))
            .thenReturn(product);

        mockMvc.perform(
                get("/api/v1/categories/" + categoryId.value())
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(categoryId.value().toString()))
            .andExpect(jsonPath("$.name").value("Food"));
    }

    @Test
    public void Should_Return_A_List_Of_Categories() throws Exception {
        Category category1 = new Category(
            CategoryId.generate(),
            "Food"
        );

        Category category2 = new Category(
            CategoryId.generate(),
            "Education"
        );

        when(getCategoriesUseCase.execute())
            .thenReturn(List.of(category1, category2));

        mockMvc.perform(
                get("/api/v1/categories")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect((jsonPath("$").isArray()))
            .andExpect((jsonPath("$.length()").value(2)))
            .andExpect(jsonPath("$[0].id").value(category1.getId().value().toString()))
            .andExpect(jsonPath("$[0].name").value("Food"))
            .andExpect(jsonPath("$[1].id").value(category2.getId().value().toString()))
            .andExpect(jsonPath("$[1].name").value("Education"));
    }

    @Test
    public void Should_Update_A_Category() throws Exception {
        CategoryId categoryId = CategoryId.generate();

        Category category = new Category(
            categoryId,
            "Travel"
        );

        when(updateCategoryUseCase.execute(categoryId, "Travel"))
            .thenReturn(category);

        var request = """
            {
                "name": "Travel"
            }
            """;

        mockMvc.perform(
                put("/api/v1/categories/" + categoryId.value())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(request)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(categoryId.value().toString()))
            .andExpect(jsonPath("$.name").value("Travel"));

        verify(updateCategoryUseCase).execute(categoryId, "Travel");
    }
}
