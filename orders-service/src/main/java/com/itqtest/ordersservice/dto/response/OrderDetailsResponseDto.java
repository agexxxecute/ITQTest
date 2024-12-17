package com.itqtest.ordersservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;

/**
 * Dto с информацией об объекте OrderDetails
 *
 * @author Egor Nazarev
 */
@Builder
public record OrderDetailsResponseDto (
    @Schema(description = "Идентификатор", example = "1")
    @NotNull(message = "orderDetailsId не может быть null")
    Long orderDetailsId,

    @Schema(description = "Артикул", example = "11111")
    @NotNull(message = "article не может быть null")
    Long article,

    @Schema(description = "Название товара", example = "Ноутбук")
    @NotNull(message = "productName не может быть null")
    String productName,

    @Schema(description = "Количество товара", example = "1")
    @NotNull(message = "quantity не может быть null")
    Integer quantity,

    @Schema(description = "Общая сумма заказа", example = "100.00")
    @NotNull(message = "price не может быть null")
    BigDecimal price
){ }
