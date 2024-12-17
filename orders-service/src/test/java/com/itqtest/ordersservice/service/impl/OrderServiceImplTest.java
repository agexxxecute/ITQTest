package com.itqtest.ordersservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
import com.itqtest.ordersservice.util.ConstantUtil;
import com.itqtest.ordersservice.util.dto.OrderCreateRequestDtoUtil;
import com.itqtest.ordersservice.util.dto.OrderFindByArticleAndDateBetweenUtil;
import com.itqtest.ordersservice.util.dto.OrderFindByDateAndPriceDtoUtil;
import com.itqtest.ordersservice.util.dto.OrderResponseDtoUtil;
import com.itqtest.ordersservice.util.model.OrderUtil;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Value("${order.number.generate.url}")
    private String ORDER_NUMBER_GENERATE_URL;

    private Order validOrder;
    private OrderResponseDto orderResponseDto;
    private OrderCreateRequestDto orderCreateRequestDto;
    private OrderFindByDateAndPriceDto orderFindByDateAndPriceDto;
    private OrderFindByArticleAndDateBetweenDto orderFindByArticleAndDateBetweenDto;
    private GeneratedNumberDto generatedNumberDto;

    private final Integer ONCE = 1;

    @BeforeEach
    void setUpVariables() {
        validOrder = OrderUtil.getValidOrder();
        orderResponseDto = OrderResponseDtoUtil.getValidOrderResponseDto();
        orderCreateRequestDto = OrderCreateRequestDtoUtil.getValidOrderCreateRequestDto();
        orderFindByDateAndPriceDto = OrderFindByDateAndPriceDtoUtil.getValidOrderFindByDateAndPriceDto();
        orderFindByArticleAndDateBetweenDto = OrderFindByArticleAndDateBetweenUtil.getValidOrderFindByArticleAndDateBetweenDto();
        generatedNumberDto = new GeneratedNumberDto(ConstantUtil.VALID_ORDER_NUMBER);
    }

    @Test
    @DisplayName("Should successfully find an order")
    void findOrderByIdSuccess() {
        Long orderId = ConstantUtil.VALID_ORDER_ID;
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(validOrder));
        when(orderMapper.toDto(validOrder)).thenReturn(orderResponseDto);

        orderService.findOrderById(orderId);

        Mockito.verify(orderRepository, Mockito.times(ONCE)).findById(orderId);
        Mockito.verify(orderMapper, Mockito.times(ONCE)).toDto(validOrder);
    }

    @Test
    @DisplayName("Should throw NotFoundException")
    void findOrderByIdNotFound() {
        Long orderId = ConstantUtil.INVALID_ORDER_ID;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderService.findOrderById(orderId));

        Mockito.verify(orderRepository, Mockito.times(ONCE)).findById(orderId);
    }

    @Test
    @DisplayName("Should create order successfully")
    void createOrderSuccess() {
        when(orderMapper.toEntity(orderCreateRequestDto)).thenReturn(validOrder);
        when(restTemplate.getForEntity(ORDER_NUMBER_GENERATE_URL, GeneratedNumberDto.class)).thenReturn(new ResponseEntity<>(generatedNumberDto, HttpStatus.OK));
        when(orderRepository.save(validOrder)).thenReturn(validOrder);

        orderService.createOrder(orderCreateRequestDto);

        Mockito.verify(orderMapper, Mockito.times(ONCE)).toEntity(orderCreateRequestDto);
        Mockito.verify(restTemplate, Mockito.times(ONCE)).getForEntity(ORDER_NUMBER_GENERATE_URL, GeneratedNumberDto.class);
        Mockito.verify(orderRepository, Mockito.times(ONCE)).save(validOrder);
    }

    @Test
    @DisplayName("Should return Internal Server Error")
    void createOrderInternalServerError() {
        when(orderMapper.toEntity(orderCreateRequestDto)).thenReturn(validOrder);
        when(restTemplate.getForEntity(ORDER_NUMBER_GENERATE_URL, GeneratedNumberDto.class)).thenThrow(InternalServerErrorException.class);

        assertThrows(InternalServerErrorException.class, () -> orderService.createOrder(orderCreateRequestDto));

        Mockito.verify(orderMapper, Mockito.times(ONCE)).toEntity(orderCreateRequestDto);
        Mockito.verify(restTemplate, Mockito.times(ONCE)).getForEntity(ORDER_NUMBER_GENERATE_URL, GeneratedNumberDto.class);
    }

    @Test
    @DisplayName("Should find orders by date and price successfully")
    void findOrdersByDateAndPriceSuccessfully() {
        when(orderRepository.findByDateAndPrice(orderFindByDateAndPriceDto.orderDate(), orderFindByDateAndPriceDto.minPrice())).thenReturn(List.of(validOrder));

        orderService.findByDateAndPrice(orderFindByDateAndPriceDto);

        Mockito.verify(orderRepository, Mockito.times(ONCE)).findByDateAndPrice(orderFindByDateAndPriceDto.orderDate(), orderFindByDateAndPriceDto.minPrice());
    }

    @Test
    @DisplayName("Should return Not Found exception")
    void findOrdersByDateAndPriceNotFound() {
        when(orderRepository.findByDateAndPrice(orderFindByDateAndPriceDto.orderDate(), orderFindByDateAndPriceDto.minPrice())).thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> orderService.findByDateAndPrice(orderFindByDateAndPriceDto));

        Mockito.verify(orderRepository, Mockito.times(ONCE)).findByDateAndPrice(orderFindByDateAndPriceDto.orderDate(), orderFindByDateAndPriceDto.minPrice());
    }

    @Test
    @DisplayName("Should find orders by article and date between successfully")
    void findOrdersByArticleAndDateBetweenSuccessfully() {
        when(orderRepository.findByArticleAndDateBetween(orderFindByArticleAndDateBetweenDto.article(),
                                                        orderFindByArticleAndDateBetweenDto.beginDate(),
                                                        orderFindByArticleAndDateBetweenDto.endDate()))
            .thenReturn(List.of(validOrder));

        orderService.findByArticleAndDateBetween(orderFindByArticleAndDateBetweenDto);

        Mockito.verify(orderRepository, Mockito.times(ONCE)).findByArticleAndDateBetween(orderFindByArticleAndDateBetweenDto.article(),
                                                                                        orderFindByArticleAndDateBetweenDto.beginDate(),
                                                                                        orderFindByArticleAndDateBetweenDto.endDate());
    }

    @Test
    @DisplayName("Should return Not Found exception")
    void findOrdersByArticleAndDateBetweenNotFound() {
        when(orderRepository.findByArticleAndDateBetween(orderFindByArticleAndDateBetweenDto.article(),
            orderFindByArticleAndDateBetweenDto.beginDate(),
            orderFindByArticleAndDateBetweenDto.endDate()))
            .thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> orderService.findByArticleAndDateBetween(orderFindByArticleAndDateBetweenDto));

        Mockito.verify(orderRepository, Mockito.times(ONCE)).findByArticleAndDateBetween(orderFindByArticleAndDateBetweenDto.article(),
            orderFindByArticleAndDateBetweenDto.beginDate(),
            orderFindByArticleAndDateBetweenDto.endDate());
    }


}