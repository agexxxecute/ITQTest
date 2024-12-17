package com.itqtest.ordersservice.mapper;

import com.itqtest.ordersservice.dto.request.OrderDetailsCreateRequestDto;
import com.itqtest.ordersservice.dto.response.OrderDetailsResponseDto;
import com.itqtest.ordersservice.entity.OrderDetails;
import com.itqtest.ordersservice.util.dto.OrderDetailsCreateRequestDtoUtil;
import com.itqtest.ordersservice.util.model.OrderDetailsUtil;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class OrderDetailsMapperTest {
    private OrderDetailsMapper orderDetailsMapper;
    private OrderDetails orderDetails;
    private OrderDetailsCreateRequestDto orderDetailsCreateRequestDto;

    @BeforeEach
    void setUpVariables() {
        orderDetailsMapper = Mappers.getMapper(OrderDetailsMapper.class);

        orderDetails = OrderDetailsUtil.getValidOrderDetails();
        orderDetailsCreateRequestDto = OrderDetailsCreateRequestDtoUtil.getValidOrderDetailsCreateRequestDto();
    }

    @Test
    @DisplayName("OrderDetailsCreateRequestDto to OrderDetails")
    void toEntity() {
        OrderDetails orderDetailsResult = orderDetailsMapper.toEntity(orderDetailsCreateRequestDto);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(orderDetailsResult.getArticle()).isEqualTo(orderDetailsCreateRequestDto.article());
            softAssertions.assertThat(orderDetailsResult.getProductName()).isEqualTo(orderDetailsCreateRequestDto.productName());
            softAssertions.assertThat(orderDetailsResult.getQuantity()).isEqualTo(orderDetailsCreateRequestDto.quantity());
            softAssertions.assertThat(orderDetailsResult.getPrice()).isEqualTo(orderDetailsCreateRequestDto.price());
        });
    }

    @Test
    @DisplayName("OrderDetails to OrderDetailsResponseDto")
    void toDto() {
        OrderDetailsResponseDto orderDetailsResponseDtoResult = orderDetailsMapper.toDto(orderDetails);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(orderDetailsResponseDtoResult.orderDetailsId()).isEqualTo(orderDetails.getOrderDetailsId());
            softAssertions.assertThat(orderDetailsResponseDtoResult.article()).isEqualTo(orderDetails.getArticle());
            softAssertions.assertThat(orderDetailsResponseDtoResult.productName()).isEqualTo(orderDetails.getProductName());
            softAssertions.assertThat(orderDetailsResponseDtoResult.quantity()).isEqualTo(orderDetails.getQuantity());
            softAssertions.assertThat(orderDetailsResponseDtoResult.price()).isEqualTo(orderDetails.getPrice());
        });
    }
}
