package com.itqtest.ordersservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое в случае отсутствия запрошенного ресурса
 *
 * @author Egor Nazarev
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Метод возвращает сообщение, отображаемое в случае отсутствия запрошенного ресурса
     * @param message текст сообщения
     */
    public NotFoundException(String message) {
        super(message);
    }
}
