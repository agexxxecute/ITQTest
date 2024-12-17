package com.itqtest.ordersservice.mapper;

import com.itqtest.ordersservice.dto.request.OrderCreateRequestDto;
import com.itqtest.ordersservice.dto.request.OrderDetailsCreateRequestDto;
import com.itqtest.ordersservice.dto.response.OrderDetailsResponseDto;
import com.itqtest.ordersservice.dto.response.OrderResponseDto;
import com.itqtest.ordersservice.entity.Order;
import com.itqtest.ordersservice.entity.OrderDetails;
import com.itqtest.ordersservice.entity.enums.DeliveryType;
import com.itqtest.ordersservice.entity.enums.PaymentType;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T16:33:50+0400",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderCreateRequestDto orderCreateRequestDto) {
        if ( orderCreateRequestDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.price( orderCreateRequestDto.price() );
        order.date( orderCreateRequestDto.date() );
        order.recipient( orderCreateRequestDto.recipient() );
        order.deliveryAddress( orderCreateRequestDto.deliveryAddress() );
        if ( orderCreateRequestDto.paymentType() != null ) {
            order.paymentType( orderCreateRequestDto.paymentType().name() );
        }
        if ( orderCreateRequestDto.deliveryType() != null ) {
            order.deliveryType( orderCreateRequestDto.deliveryType().name() );
        }
        order.orderDetails( orderDetailsCreateRequestDtoSetToOrderDetailsSet( orderCreateRequestDto.orderDetails() ) );

        return order.build();
    }

    @Override
    public OrderResponseDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseDto.OrderResponseDtoBuilder orderResponseDto = OrderResponseDto.builder();

        orderResponseDto.orderId( order.getOrderId() );
        orderResponseDto.number( order.getNumber() );
        orderResponseDto.price( order.getPrice() );
        orderResponseDto.date( order.getDate() );
        orderResponseDto.recipient( order.getRecipient() );
        orderResponseDto.deliveryAddress( order.getDeliveryAddress() );
        if ( order.getPaymentType() != null ) {
            orderResponseDto.paymentType( Enum.valueOf( PaymentType.class, order.getPaymentType() ) );
        }
        if ( order.getDeliveryType() != null ) {
            orderResponseDto.deliveryType( Enum.valueOf( DeliveryType.class, order.getDeliveryType() ) );
        }
        orderResponseDto.orderDetails( orderDetailsSetToOrderDetailsResponseDtoSet( order.getOrderDetails() ) );

        return orderResponseDto.build();
    }

    @Override
    public List<OrderResponseDto> toDto(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderResponseDto> list = new ArrayList<OrderResponseDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( toDto( order ) );
        }

        return list;
    }

    protected OrderDetails orderDetailsCreateRequestDtoToOrderDetails(OrderDetailsCreateRequestDto orderDetailsCreateRequestDto) {
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

    protected Set<OrderDetails> orderDetailsCreateRequestDtoSetToOrderDetailsSet(Set<OrderDetailsCreateRequestDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<OrderDetails> set1 = new LinkedHashSet<OrderDetails>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OrderDetailsCreateRequestDto orderDetailsCreateRequestDto : set ) {
            set1.add( orderDetailsCreateRequestDtoToOrderDetails( orderDetailsCreateRequestDto ) );
        }

        return set1;
    }

    protected OrderDetailsResponseDto orderDetailsToOrderDetailsResponseDto(OrderDetails orderDetails) {
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

    protected Set<OrderDetailsResponseDto> orderDetailsSetToOrderDetailsResponseDtoSet(Set<OrderDetails> set) {
        if ( set == null ) {
            return null;
        }

        Set<OrderDetailsResponseDto> set1 = new LinkedHashSet<OrderDetailsResponseDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OrderDetails orderDetails : set ) {
            set1.add( orderDetailsToOrderDetailsResponseDto( orderDetails ) );
        }

        return set1;
    }
}
