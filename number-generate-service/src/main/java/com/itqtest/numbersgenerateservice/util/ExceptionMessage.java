package com.itqtest.numbersgenerateservice.util;

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
    INTERNAL_SERVER_ERROR("Внутренняя ошибка сервера"),
    INSUFFICIENT_STORAGE_ERROR("Достигнуто ограничение количества номеров за день");

    private final String description;
}
