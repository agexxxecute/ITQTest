package com.itqtest.ordersservice.mapper;

import com.itqtest.ordersservice.dto.request.OrderDetailsCreateRequestDto;
import com.itqtest.ordersservice.dto.response.OrderDetailsResponseDto;
import com.itqtest.ordersservice.entity.OrderDetails;
import org.mapstruct.Mapper;

/**
 * Интерфейс для преобразования объектов типа OrderDetails в Dto и обратно.
 *
 * @author Egor Nazarev
 */
@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {
    /**
     * Метод для преобразования OrderDetailsCreateRequestDto в OrderDetails
     * @param orderDetailsCreateRequestDto входная информация о деталях заказа.
     * @return объект деталей заказа.
     */
    OrderDetails toEntity(OrderDetailsCreateRequestDto orderDetailsCreateRequestDto);

    /**
     * Метод для преобразования OrderDetails в OrderDetailsResponseDto
     * @param orderDetails объект деталей заказа.
     * @return Dto с информацией о деталях заказа.
     */
    OrderDetailsResponseDto toDto(OrderDetails orderDetails);
}
