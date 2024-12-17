package com.itqtest.ordersservice.service.impl;

import com.itqtest.ordersservice.dto.GeneratedNumberDto;
import com.itqtest.ordersservice.dto.request.OrderCreateRequestDto;
import com.itqtest.ordersservice.dto.request.OrderFindByArticleAndDateBetweenDto;
import com.itqtest.ordersservice.dto.request.OrderFindByDateAndPriceDto;
import com.itqtest.ordersservice.dto.response.OrderResponseDto;
import com.itqtest.ordersservice.entity.Order;
import com.itqtest.ordersservice.exception.InternalServerErrorException;
import com.itqtest.ordersservice.exception.NotFoundException;
import com.itqtest.ordersservice.mapper.OrderMapper;
import com.itqtest.ordersservice.repository.OrderRepository;
import com.itqtest.ordersservice.service.OrderService;
import com.itqtest.ordersservice.util.ExceptionMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Реализация интерфейса OrderService. Содержит бизнес-логику для работы с заказами.
 *
 * @author Egor Nazarev
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RestTemplate restTemplate;

    @Value("${order.number.generate.url}")
    private String ORDER_NUMBER_GENERATE_URL;

    /**
     * Реализация метода поиска заказа по идентификатору
     * @param orderId идентификатор заказа.
     * @return информация о найденном заказе.
     */
    @Override
    public OrderResponseDto findOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
            () -> new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND.getDescription()
                .formatted(orderId)));
        return orderMapper.toDto(order);
    }

    /**
     * Реализация метода создания заказа.
     * @param orderCreateRequestDto информация для создания заказа.
     * @return информация о созданном заказе.
     */
    @Override
    public OrderResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        Order incomingOrder = orderMapper.toEntity(orderCreateRequestDto);
        try {
            incomingOrder.setNumber(getOrderNumberRest());
        } catch (Exception e) {
            throw new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription());
        }
        return orderMapper.toDto(orderRepository.save(incomingOrder));
    }

    /**
     * Реализация метода для поиска заказов за заданную дату и больше заданной общей суммы заказа.
     * @param orderFindByDateAndPriceDto информация о дате и сумме.
     * @return список найденных заказов.
     */
    @Override
    public List<OrderResponseDto> findByDateAndPrice(
        OrderFindByDateAndPriceDto orderFindByDateAndPriceDto) {
        List<Order> foundedOrders = orderRepository.findByDateAndPrice(orderFindByDateAndPriceDto.orderDate(), orderFindByDateAndPriceDto.minPrice());
        if(foundedOrders.isEmpty()){
            throw new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND.getDescription());
        }
        return orderMapper.toDto(foundedOrders);
    }

    /**
     * Реализация метода для поиска заказов, не содержащих заданный товар и поступивших в заданный временной период.
     * @param orderFindByArticleAndDateBetweenDto информация об артикле товара и временном периоде.
     * @return список найденных заказов.
     */
    @Override
    public List<OrderResponseDto> findByArticleAndDateBetween(
        OrderFindByArticleAndDateBetweenDto orderFindByArticleAndDateBetweenDto) {
        List<Order> foundedOrders = orderRepository.findByArticleAndDateBetween(orderFindByArticleAndDateBetweenDto.article(),
                                                                                orderFindByArticleAndDateBetweenDto.beginDate(),
                                                                                orderFindByArticleAndDateBetweenDto.endDate());
        if(foundedOrders.isEmpty()){
            throw new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND.getDescription());
        }
        return orderMapper.toDto(foundedOrders);
    }

    private String getOrderNumberRest(){
        log.info(ORDER_NUMBER_GENERATE_URL);
        System.out.println(ORDER_NUMBER_GENERATE_URL);
        ResponseEntity<GeneratedNumberDto> orderNumberEntity = restTemplate.getForEntity(ORDER_NUMBER_GENERATE_URL, GeneratedNumberDto.class);
        return orderNumberEntity.getBody().generatedNumber();
    }
}
