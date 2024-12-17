package com.itqtest.ordersservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое в случае ошибки в запросе.
 *
 * @author Egor Nazarev
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
     * Метод возвращает сообщение, отображаемое в случае ошибки в запросе.
     * @param message текст сообщения
     */
    public BadRequestException(String message) {
        super(message);
    }
}
