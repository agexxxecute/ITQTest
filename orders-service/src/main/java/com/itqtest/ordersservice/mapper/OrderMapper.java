package com.itqtest.ordersservice.mapper;

import com.itqtest.ordersservice.dto.request.OrderCreateRequestDto;
import com.itqtest.ordersservice.dto.response.OrderResponseDto;
import com.itqtest.ordersservice.entity.Order;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * Интерфейс для преобразования объектов типа Order в Dto и обратно.
 *
 * @author Egor Nazarev
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

    /**
     * Метод для преобразования OrderCreateRequestDto в Order
     * @param orderCreateRequestDto входная информация о заказе.
     * @return объект заказа.
     */
    Order toEntity(OrderCreateRequestDto orderCreateRequestDto);

    /**
     * Метод для преобразования Order в OrderResponseDto
     * @param order объект заказа.
     * @return Dto с информацией о заказе.
     */
    OrderResponseDto toDto(Order order);

    /**
     * Метод для преобразования списка Order в список OrderResponseDto
     * @param orders список объектов заказа.
     * @return список Dto с информацией о заказе.
     */
    List<OrderResponseDto> toDto(List<Order> orders);
}
