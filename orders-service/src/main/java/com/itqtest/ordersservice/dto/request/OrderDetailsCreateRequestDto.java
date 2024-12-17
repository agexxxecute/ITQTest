package com.itqtest.ordersservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;

/**
 * Dto для создания объекта OrderDetails
 *
 * @author Egor Nazarev
 */
@Builder
public record OrderDetailsCreateRequestDto (
    @Schema(description = "Артикул товара", example = "11111")
    @NotNull(message = "article не может быть null")
    Long article,

    @Schema(description = "Название товара", example = "Ноутбук")
    @NotNull(message = "productName не может быть null")
    String productName,

    @Schema(description = "Количество товара", example = "1")
    @NotNull(message = "quantity не может быть null")
    Integer quantity,

    @Schema(description = "Стоимость товара", example = "50.0")
    @NotNull(message = "price не может быть null")
    BigDecimal price
){ }
