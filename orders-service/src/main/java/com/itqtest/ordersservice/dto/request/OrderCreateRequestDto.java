package com.itqtest.ordersservice.dto.request;

import com.itqtest.ordersservice.entity.enums.DeliveryType;
import com.itqtest.ordersservice.entity.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.Builder;

/**
 * Dto для создания заказа.
 *
 * @author Egor Nazarev
 */
@Builder
public record OrderCreateRequestDto(
    @Schema(description = "Общая сумма заказа", example = "100.00")
    @NotNull(message = "price не может быть null")
    BigDecimal price,

    @Schema(description = "Дата заказа", example = "2024-12-12")
    @NotNull(message = "date не может быть null")
    LocalDate date,

    @Schema(description = "Получатель", example = "Иванов И.И.")
    @NotNull(message = "recipient не может быть null")
    String recipient,

    @Schema(description = "Адрес доставки", example = "Москва, Мансуровский переулок, 9")
    @NotNull(message = "deliveryAddress не может быть null")
    String deliveryAddress,

    @Schema(description = "Тип оплаты", example = "Наличные")
    @NotNull(message = "paymentType не может быть null")
    PaymentType paymentType,

    @Schema(description = "Тип доставки", example = "Самовывоз")
    @NotNull(message = "deliveryType не может быть null")
    DeliveryType deliveryType,

    @Schema(description = "Детали заказа")
    Set<OrderDetailsCreateRequestDto> orderDetails
)
{ }
