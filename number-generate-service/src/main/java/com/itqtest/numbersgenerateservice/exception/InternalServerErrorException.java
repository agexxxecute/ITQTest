package com.itqtest.numbersgenerateservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое в случае внутренней ошибки сервера.
 *
 * @author Egor Nazarev
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

    /**
     * Метод возвращает сообщение, отображаемое в случае внутренней ошибки сервера.
     * @param message текст сообщения
     */
    public InternalServerErrorException(String message) {
        super(message);
    }
}
