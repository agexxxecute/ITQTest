package com.itqtest.ordersservice.mapper;

import com.itqtest.ordersservice.dto.request.OrderDetailsCreateRequestDto;
import com.itqtest.ordersservice.dto.response.OrderDetailsResponseDto;
import com.itqtest.ordersservice.entity.OrderDetails;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T16:33:50+0400",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class OrderDetailsMapperImpl implements OrderDetailsMapper {

    @Override
    public OrderDetails toEntity(OrderDetailsCreateRequestDto orderDetailsCreateRequestDto) {
        if ( orderDetailsCreateRequestDto == null ) {
            return null;
        }

        OrderDetails.OrderDetailsBuilder orderDetails = OrderDetails.builder();

        orderDetails.article( orderDetailsCreateRequestDto.article() );
        orderDetails.productName( orderDetailsCreateRequestDto.productName() );
        orderDetails.quantity( orderDetailsCreateRequestDto.quantity() );
        orderDetails.price( orderDetailsCreateRequestDto.price() );

        return orderDetails.build();
    }

    @Override
    public OrderDetailsResponseDto toDto(OrderDetails orderDetails) {
        if ( orderDetails == null ) {
            return null;
        }

        OrderDetailsResponseDto.OrderDetailsResponseDtoBuilder orderDetailsResponseDto = OrderDetailsResponseDto.builder();

        orderDetailsResponseDto.orderDetailsId( orderDetails.getOrderDetailsId() );
        orderDetailsResponseDto.article( orderDetails.getArticle() );
        orderDetailsResponseDto.productName( orderDetails.getProductName() );
        orderDetailsResponseDto.quantity( orderDetails.getQuantity() );
        orderDetailsResponseDto.price( orderDetails.getPrice() );

        return orderDetailsResponseDto.build();
    }
}
