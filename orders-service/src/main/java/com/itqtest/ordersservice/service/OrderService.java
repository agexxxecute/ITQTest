package com.itqtest.ordersservice.service;

import com.itqtest.ordersservice.dto.request.OrderCreateRequestDto;
import com.itqtest.ordersservice.dto.request.OrderFindByArticleAndDateBetweenDto;
import com.itqtest.ordersservice.dto.request.OrderFindByDateAndPriceDto;
import com.itqtest.ordersservice.dto.response.OrderResponseDto;
import java.util.List;

/**
 * Интерфейс, содержащий бизнес-логику для работы с заказами.
 *
 * @author Egor Nazarev
 */
public interface OrderService {

    /**
     * Метод для поиска заказа по идентификатору.
     * @param orderId идентификатор заказа.
     * @return информация о найденном заказе.
     */
    OrderResponseDto findOrderById(Long orderId);

    /**
     * Метод для создания заказа.
     * @param orderCreateRequestDto информация для создания заказа.
     * @return информация о созданном заказе.
     */
    OrderResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);

    /**
     * Метод для поиска заказов за заданную дату и больше заданной общей суммы заказа.
     * @param orderFindByDateAndPriceDto информация о дате и сумме.
     * @return список найденных заказов.
     */
    List<OrderResponseDto> findByDateAndPrice(OrderFindByDateAndPriceDto orderFindByDateAndPriceDto);

    /**
     * Метод для поиска заказов, не содержащих заданный товар и поступивших в заданный временной период.
     * @param orderFindByArticleAndDateBetween информация об артикле товара и временном периоде.
     * @return список найденных заказов.
     */
    List<OrderResponseDto> findByArticleAndDateBetween(
        OrderFindByArticleAndDateBetweenDto orderFindByArticleAndDateBetween);
}
