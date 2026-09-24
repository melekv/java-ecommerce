package com.melek.ecommerce.order.infrastructure.web;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.order.application.*;
import com.melek.ecommerce.order.application.dto.OrderItemResponse;
import com.melek.ecommerce.order.application.dto.OrderResponse;
import com.melek.ecommerce.order.domain.model.*;
import com.melek.ecommerce.shared.domain.model.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateOrderUseCase createOrderUseCase;

    @MockitoBean
    private ConfirmOrderUseCase confirmOrderUseCase;

    @MockitoBean
    private CancelOrderUseCase cancelOrderUseCase;

    @MockitoBean
    private PayOrderUseCase payOrderUseCase;

    @MockitoBean
    private ShipOrderUseCase shipOrderUseCase;

    @MockitoBean
    private DeliverOrderUseCase deliverOrderUseCase;

    @MockitoBean
    private GetOrderUseCase getOrderUseCase;

    @MockitoBean
    private OrderResponseMapper mapper;

    @Test
    public void Should_Confirm_Order() throws Exception {
        OrderItem orderItem = new OrderItem(
            OrderItemId.generate(),
            ProductId.generate(),
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(100),
                Currency.getInstance("PLN")
            ),
            2
        );

        Order order = new Order(
            OrderId.generate(),
            UUID.randomUUID(),
            List.of(orderItem)
        );

        OrderItemResponse orderItemResponse = new OrderItemResponse(
            orderItem.getId().value(),
            orderItem.getProductId().value(),
            orderItem.getProductName(),
            orderItem.getUnitPrice().amount(),
            orderItem.getUnitPrice().currency().getCurrencyCode(),
            orderItem.getQuantity()
        );

        OrderResponse response = new OrderResponse(
            order.getId().value(),
            order.getCustomerId(),
            order.getStatus(),
            List.of(orderItemResponse)
        );

        when(confirmOrderUseCase.execute(order.getId()))
            .thenReturn(order);

        when(mapper.map(order))
            .thenReturn(response);

        mockMvc.perform(
            post("/api/v1/orders/{id}/confirm", order.getId().value()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(order.getId().value().toString()))
            .andExpect(jsonPath("$.customerId").value(order.getCustomerId().toString()))
            .andExpect(jsonPath("$.status").value(order.getStatus().name()))
            .andExpect(jsonPath("$.items").isArray())
            .andExpect(jsonPath("$.items[0].id").value(orderItemResponse.id().toString()))
            .andExpect(jsonPath("$.items[0].productId").value(orderItemResponse.productId().toString()))
            .andExpect(jsonPath("$.items[0].productName").value(orderItemResponse.productName()))
            .andExpect(jsonPath("$.items[0].unitPrice").value(orderItemResponse.unitPrice()))
            .andExpect(jsonPath("$.items[0].currency").value(orderItemResponse.currency()))
            .andExpect(jsonPath("$.items[0].quantity").value(orderItemResponse.quantity()));

        verify(confirmOrderUseCase).execute(order.getId());
        verify(mapper).map(order);
    }

    @Test
    public void Should_Cancel_Order() throws Exception {
        OrderItem orderItem = new OrderItem(
            OrderItemId.generate(),
            ProductId.generate(),
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(100),
                Currency.getInstance("PLN")
            ),
            2
        );

        Order order = new Order(
            OrderId.generate(),
            UUID.randomUUID(),
            List.of(orderItem),
            OrderStatus.PAID
        );

        OrderItemResponse orderItemResponse = new OrderItemResponse(
            orderItem.getId().value(),
            orderItem.getProductId().value(),
            orderItem.getProductName(),
            orderItem.getUnitPrice().amount(),
            orderItem.getUnitPrice().currency().getCurrencyCode(),
            orderItem.getQuantity()
        );

        OrderResponse response = new OrderResponse(
            order.getId().value(),
            order.getCustomerId(),
            order.getStatus(),
            List.of(orderItemResponse)
        );

        when(cancelOrderUseCase.execute(order.getId()))
            .thenReturn(order);

        when(mapper.map(order))
            .thenReturn(response);

        mockMvc.perform(
                post("/api/v1/orders/{id}/cancel", order.getId().value()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value(order.getStatus().name()));

        verify(cancelOrderUseCase).execute(order.getId());
        verify(mapper).map(order);
    }
}
