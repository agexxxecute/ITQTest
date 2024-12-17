package com.itqtest.ordersservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Dto со сгенерированным номером заказа, получаемый от микросервиса генерации номеров.
 *
 * @author Egor Nazarev
 */
public record GeneratedNumberDto(
    @Schema(description = "Сгенерированный номер заказа", example = "1111120241212")
    @NotNull(message = "generatedNumber не может быть null")
    String generatedNumber
) { }
