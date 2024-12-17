package com.itqtest.ordersservice.mapper;

import com.itqtest.ordersservice.dto.request.OrderCreateRequestDto;
import com.itqtest.ordersservice.dto.response.OrderResponseDto;
import com.itqtest.ordersservice.entity.Order;
import com.itqtest.ordersservice.util.dto.OrderCreateRequestDtoUtil;
import com.itqtest.ordersservice.util.dto.OrderResponseDtoUtil;
import com.itqtest.ordersservice.util.model.OrderUtil;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class OrderMapperTest {
    private OrderMapper orderMapper;
    private Order order;
    private OrderCreateRequestDto orderCreateRequestDto;
    private OrderResponseDto orderResponseDto;

    private final int FIRST_ELEMENT = 0;

    @BeforeEach
    void setUpVariables() {
        orderMapper = Mappers.getMapper(OrderMapper.class);

        order = OrderUtil.getValidOrder();
        orderCreateRequestDto = OrderCreateRequestDtoUtil.getValidOrderCreateRequestDto();
        orderResponseDto = OrderResponseDtoUtil.getValidOrderResponseDto();
    }

    @Test
    @DisplayName("OrderCreateRequestDto to Order")
    void toEntity() {
        Order orderResult = orderMapper.toEntity(orderCreateRequestDto);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(orderResult.getPrice()).isEqualTo(orderCreateRequestDto.price());
            softAssertions.assertThat(orderResult.getDate()).isEqualTo(orderCreateRequestDto.date());
            softAssertions.assertThat(orderResult.getRecipient()).isEqualTo(orderCreateRequestDto.recipient());
            softAssertions.assertThat(orderResult.getDeliveryAddress()).isEqualTo(orderCreateRequestDto.deliveryAddress());
            softAssertions.assertThat(orderResult.getPaymentType()).isEqualTo(orderCreateRequestDto.paymentType().toString());
            softAssertions.assertThat(orderResult.getDeliveryType()).isEqualTo(orderCreateRequestDto.deliveryType().toString());
        });
    }

    @Test
    @DisplayName("Order to OrderResponseDto")
    void toDto() {
        OrderResponseDto orderResponseDtoResult = orderMapper.toDto(order);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(orderResponseDtoResult.orderId()).isEqualTo(order.getOrderId());
            softAssertions.assertThat(orderResponseDtoResult.number()).isEqualTo(order.getNumber());
            softAssertions.assertThat(orderResponseDtoResult.date()).isEqualTo(order.getDate());
            softAssertions.assertThat(orderResponseDtoResult.recipient()).isEqualTo(order.getRecipient());
            softAssertions.assertThat(orderResponseDtoResult.deliveryAddress()).isEqualTo(order.getDeliveryAddress());
            softAssertions.assertThat(orderResponseDtoResult.paymentType().toString()).isEqualTo(order.getPaymentType());
            softAssertions.assertThat(orderResponseDtoResult.deliveryType().toString()).isEqualTo(order.getDeliveryType());
        });
    }

    @Test
    @DisplayName("Order List to OrderResponseDto List")
    void toDtoList() {
        List<OrderResponseDto> orderResponseDtoList = orderMapper.toDto(List.of(order));

        Assertions.assertEquals(orderResponseDtoList.get(FIRST_ELEMENT), orderResponseDto);
    }
}