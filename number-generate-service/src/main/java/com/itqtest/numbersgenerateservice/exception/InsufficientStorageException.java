package com.itqtest.numbersgenerateservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение, выбрасываемое в случае переполнения хранилища.
 *
 * @author Egor Nazarev
 */
@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class InsufficientStorageException extends RuntimeException {

    /**
     * Метод возвращает сообщение, отображаемое в случае переполнения хранилища.
     * @param message текст сообщения
     */
    public InsufficientStorageException(String message) {
        super(message);
    }
}
