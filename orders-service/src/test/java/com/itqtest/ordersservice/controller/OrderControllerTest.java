package com.itqtest.ordersservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itqtest.ordersservice.dto.request.OrderCreateRequestDto;
import com.itqtest.ordersservice.dto.request.OrderFindByArticleAndDateBetweenDto;
import com.itqtest.ordersservice.dto.request.OrderFindByDateAndPriceDto;
import com.itqtest.ordersservice.dto.response.OrderResponseDto;
import com.itqtest.ordersservice.exception.BadRequestException;
import com.itqtest.ordersservice.exception.InternalServerErrorException;
import com.itqtest.ordersservice.exception.NotFoundException;
import com.itqtest.ordersservice.service.OrderService;
import com.itqtest.ordersservice.util.ConstantUtil;
import com.itqtest.ordersservice.util.ExceptionMessage;
import com.itqtest.ordersservice.util.dto.OrderCreateRequestDtoUtil;
import com.itqtest.ordersservice.util.dto.OrderFindByArticleAndDateBetweenUtil;
import com.itqtest.ordersservice.util.dto.OrderFindByDateAndPriceDtoUtil;
import com.itqtest.ordersservice.util.dto.OrderResponseDtoUtil;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @MockitoBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderCreateRequestDto validOrderCreateRequestDto;
    private OrderCreateRequestDto invalidOrderCreateRequestDto;
    private OrderResponseDto orderResponseDto;
    private OrderFindByDateAndPriceDto validOrderFindByDateAndPriceDto;
    private OrderFindByDateAndPriceDto invalidOrderFindByDateAndPriceDto;
    private OrderFindByArticleAndDateBetweenDto validOrderFindByArticleAndDateBetweenDto;
    private OrderFindByArticleAndDateBetweenDto invalidOrderFindByArticleAndDateBetweenDto;

    private final String ORDER_CREATE_URL = "/orders";
    private final String VALID_ORDER_FIND_BY_ID_URL = "/orders?id=" + ConstantUtil.VALID_ORDER_ID;
    private final String INVALID_ORDER_FIND_BY_ID_URL = "/orders?id=" + ConstantUtil.INVALID_ORDER_ID;
    private final String ORDER_FIND_BY_DATE_AND_PRICE_URL = "/orders/date_price";
    private final String ORDER_FIND_BY_ARTICLE_AND_DATE_BETWEEN_URL = "/orders/article_date";

    @BeforeEach
    void setUpVariables() {
        validOrderCreateRequestDto = OrderCreateRequestDtoUtil.getValidOrderCreateRequestDto();
        invalidOrderCreateRequestDto = OrderCreateRequestDtoUtil.getInvalidOrderCreateRequestDto();
        orderResponseDto = OrderResponseDtoUtil.getValidOrderResponseDto();
        validOrderFindByDateAndPriceDto = OrderFindByDateAndPriceDtoUtil.getValidOrderFindByDateAndPriceDto();
        invalidOrderFindByDateAndPriceDto = OrderFindByDateAndPriceDtoUtil.getInvalidOrderFindByDateAndPriceDto();
        validOrderFindByArticleAndDateBetweenDto = OrderFindByArticleAndDateBetweenUtil.getValidOrderFindByArticleAndDateBetweenDto();
        invalidOrderFindByArticleAndDateBetweenDto = OrderFindByArticleAndDateBetweenUtil.getInvalidOrderFindByArticleAndDateBetweenDto();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should create order successfully")
    void createOrderSuccessfully(){
        when(orderService.createOrder(validOrderCreateRequestDto)).thenReturn(orderResponseDto);

        mockMvc.perform(post(ORDER_CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validOrderCreateRequestDto)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        Mockito.verify(orderService).createOrder(validOrderCreateRequestDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should throw Bad Request and status 400")
    void createOrderBadRequest(){
        when(orderService.createOrder(invalidOrderCreateRequestDto)).thenThrow(new BadRequestException(ExceptionMessage.BAD_REQUEST.getDescription()));

        mockMvc.perform(post(ORDER_CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidOrderCreateRequestDto)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.BAD_REQUEST.getDescription()))
            .andReturn();

        Mockito.verify(orderService).createOrder(invalidOrderCreateRequestDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should throw Internal Server Error and status 500")
    void createOrderInternalServerError(){
        when(orderService.createOrder(validOrderCreateRequestDto)).thenThrow(new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()));

        mockMvc.perform(post(ORDER_CREATE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validOrderCreateRequestDto)))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()))
            .andReturn();

        Mockito.verify(orderService).createOrder(validOrderCreateRequestDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return founded order and status 200")
    void findOrderSuccessfully(){
        when(orderService.findOrderById(ConstantUtil.VALID_ORDER_ID)).thenReturn(orderResponseDto);

        mockMvc.perform(get(VALID_ORDER_FIND_BY_ID_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        Mockito.verify(orderService).findOrderById(ConstantUtil.VALID_ORDER_ID);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Not Found and status 404")
    void findOrderNotFound(){
        when(orderService.findOrderById(ConstantUtil.INVALID_ORDER_ID)).thenThrow(new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND.getDescription()));

        mockMvc.perform(get(INVALID_ORDER_FIND_BY_ID_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.ORDER_NOT_FOUND.getDescription()))
            .andReturn();

        Mockito.verify(orderService).findOrderById(ConstantUtil.INVALID_ORDER_ID);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Internal Server Error and status 500")
    void findOrderInternalServerError(){
        when(orderService.findOrderById(ConstantUtil.VALID_ORDER_ID)).thenThrow(new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()));

        mockMvc.perform(get(VALID_ORDER_FIND_BY_ID_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()))
            .andReturn();

        Mockito.verify(orderService).findOrderById(ConstantUtil.VALID_ORDER_ID);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return founded orders list and status 200")
    void findOrdersByDateAndPriceSuccessfully(){
        when(orderService.findByDateAndPrice(validOrderFindByDateAndPriceDto)).thenReturn(List.of(orderResponseDto));

        mockMvc.perform(get(ORDER_FIND_BY_DATE_AND_PRICE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validOrderFindByDateAndPriceDto)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        Mockito.verify(orderService).findByDateAndPrice(validOrderFindByDateAndPriceDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Bad Request and status 400")
    void findOrdersByDateAndPriceBadRequest(){
        when(orderService.findByDateAndPrice(invalidOrderFindByDateAndPriceDto)).thenThrow(new BadRequestException(ExceptionMessage.BAD_REQUEST.getDescription()));

        mockMvc.perform(get(ORDER_FIND_BY_DATE_AND_PRICE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidOrderFindByDateAndPriceDto)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.BAD_REQUEST.getDescription()))
            .andReturn();

        Mockito.verify(orderService).findByDateAndPrice(invalidOrderFindByDateAndPriceDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Not Found and status 404")
    void findOrdersByDateAndPriceNotFound(){
        when(orderService.findByDateAndPrice(validOrderFindByDateAndPriceDto)).thenThrow(new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND.getDescription()));

        mockMvc.perform(get(ORDER_FIND_BY_DATE_AND_PRICE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validOrderFindByDateAndPriceDto)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.ORDER_NOT_FOUND.getDescription()))
            .andReturn();

        Mockito.verify(orderService).findByDateAndPrice(validOrderFindByDateAndPriceDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Internal Server Error and status 500")
    void findOrdersByDateAndPriceInternalServerError(){
        when(orderService.findByDateAndPrice(validOrderFindByDateAndPriceDto)).thenThrow(new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()));

        mockMvc.perform(get(ORDER_FIND_BY_DATE_AND_PRICE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validOrderFindByDateAndPriceDto)))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()))
            .andReturn();

        Mockito.verify(orderService).findByDateAndPrice(validOrderFindByDateAndPriceDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return founded orders list and status 200")
    void findOrdersByArticleAndDateBetweenSuccessfully(){
        when(orderService.findByArticleAndDateBetween(validOrderFindByArticleAndDateBetweenDto)).thenReturn(List.of(orderResponseDto));

        mockMvc.perform(get(ORDER_FIND_BY_ARTICLE_AND_DATE_BETWEEN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validOrderFindByArticleAndDateBetweenDto)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        Mockito.verify(orderService).findByArticleAndDateBetween(validOrderFindByArticleAndDateBetweenDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Bad Request and status 400")
    void findOrdersByArticleAndDateBetweenBadRequest(){
        when(orderService.findByArticleAndDateBetween(invalidOrderFindByArticleAndDateBetweenDto)).thenThrow(new BadRequestException(ExceptionMessage.BAD_REQUEST.getDescription()));

        mockMvc.perform(get(ORDER_FIND_BY_ARTICLE_AND_DATE_BETWEEN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidOrderFindByArticleAndDateBetweenDto)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.BAD_REQUEST.getDescription()))
            .andReturn();

        Mockito.verify(orderService).findByArticleAndDateBetween(invalidOrderFindByArticleAndDateBetweenDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Not Found and status 404")
    void findOrdersByArticleAndDateBetweenNotFound(){
        when(orderService.findByArticleAndDateBetween(validOrderFindByArticleAndDateBetweenDto)).thenThrow(new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND.getDescription()));

        mockMvc.perform(get(ORDER_FIND_BY_ARTICLE_AND_DATE_BETWEEN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validOrderFindByArticleAndDateBetweenDto)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.ORDER_NOT_FOUND.getDescription()))
            .andReturn();

        Mockito.verify(orderService).findByArticleAndDateBetween(validOrderFindByArticleAndDateBetweenDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Internal Server Error and status 500")
    void findOrdersByArticleAndDateBetweenInternalServerError(){
        when(orderService.findByArticleAndDateBetween(validOrderFindByArticleAndDateBetweenDto)).thenThrow(new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()));

        mockMvc.perform(get(ORDER_FIND_BY_ARTICLE_AND_DATE_BETWEEN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validOrderFindByArticleAndDateBetweenDto)))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()))
            .andReturn();

        Mockito.verify(orderService).findByArticleAndDateBetween(validOrderFindByArticleAndDateBetweenDto);
    }
}