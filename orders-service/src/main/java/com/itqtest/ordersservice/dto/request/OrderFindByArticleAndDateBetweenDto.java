package com.itqtest.ordersservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Builder;

/**
 * Dto для поиска заказов, не содержащих заданный товар и поступивших в заданный временной период.
 */
@Builder
public record OrderFindByArticleAndDateBetweenDto(
    @Schema(description = "Артикул товара", example = "12345")
    @NotNull(message = " не может быть null")
    Long article,

    @Schema(description = "Дата начала периода", example = "2024-01-01")
    @NotNull(message = " не может быть null")
    LocalDate beginDate,

    @Schema(description = "Дата окончания периода", example = "2024-12-12")
    @NotNull(message = " не может быть null")
    LocalDate endDate
) {

}
