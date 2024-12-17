package com.itqtest.ordersservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Описание сообщений об ошибках в исключениях.
 * Определяет текстовые сообщения для различных исключительных ситуаций.
 *
 * @author Egor Nazarev
 */
@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    BAD_REQUEST("Ошибка в данных запроса"),
    INTERNAL_SERVER_ERROR("Внутренняя ошибка сервера"),
    ORDER_NOT_FOUND("Заказ с заданными параметрами не найден");

    private final String description;
}
