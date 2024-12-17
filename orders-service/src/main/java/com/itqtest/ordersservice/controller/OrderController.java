package com.itqtest.ordersservice.controller;

import com.itqtest.ordersservice.dto.request.OrderCreateRequestDto;
import com.itqtest.ordersservice.dto.request.OrderFindByArticleAndDateBetweenDto;
import com.itqtest.ordersservice.dto.request.OrderFindByDateAndPriceDto;
import com.itqtest.ordersservice.dto.response.OrderResponseDto;
import com.itqtest.ordersservice.exception.handler.ErrorResponseDto;
import com.itqtest.ordersservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с заказами.
 * Предоставляет конечные точки для создания и получения заказов
 *
 * @author Egor Nazarev
 */
@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Tag(name = "Заказ", description = "Предоставляет конечные точки для работы с заказами")
public class OrderController {
    private OrderService orderService;

    @Operation(description = "Позволяет создать заказ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Создание заказа произошло успешно"),
        @ApiResponse(responseCode = "400", description = "Ошибка в данных запроса", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        })})
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto createOrder(@RequestBody @Valid OrderCreateRequestDto orderCreateRequestDto) {
        return orderService.createOrder(orderCreateRequestDto);
    }

    @Operation(description = "Позволяет найти заказ по id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Заказ найден успешно"),
        @ApiResponse(responseCode = "404", description = "Заказ с указанным id не найден", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto findOrderById(@RequestParam Long id) {
        return orderService.findOrderById(id);
    }

    @Operation(description = "Позволяет найти заказы за заданную дату и больше заданной общей суммы заказа.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Заказы найдены успешно"),
        @ApiResponse(responseCode = "400", description = "Ошибка в данных запроса", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "404", description = "Заказы с заданными условиями не найдены", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        })})
    @GetMapping("/date_price")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDto> findByDateAndPrice(@RequestBody @Valid OrderFindByDateAndPriceDto orderFindByDateAndPrice) {
        return orderService.findByDateAndPrice(orderFindByDateAndPrice);
    }

    @Operation(description = "Позволяет получить список заказов, не содержащих заданный товар и поступивших в заданный временной период")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Заказы найдены успешно"),
        @ApiResponse(responseCode = "400", description = "Ошибка в данных запроса", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "404", description = "Заказы с заданными условиями не найдены", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        })})
    @GetMapping("/article_date")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDto> findByArticleAndDateBetween(@RequestBody @Valid OrderFindByArticleAndDateBetweenDto orderFindByArticleAndDateBetweenDto) {
        return orderService.findByArticleAndDateBetween(orderFindByArticleAndDateBetweenDto);
    }
}
