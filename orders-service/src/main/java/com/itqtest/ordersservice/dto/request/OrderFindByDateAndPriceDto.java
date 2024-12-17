package com.itqtest.ordersservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;

/**
 * Dto с информацией о дате и минимальной сумме заказа для поиска
 *
 * @author Egor Nazarev
 */
@Builder
public record OrderFindByDateAndPriceDto(
    @Schema(description = "Дата заказа", example = "2024-12-12")
    @NotNull(message = "orderDate не может быть null")
    LocalDate orderDate,

    @Schema(description = "Минимальная сумма заказа", example = "100.0")
    @NotNull(message = "minPrice не может быть null")
    BigDecimal minPrice
) {

}
