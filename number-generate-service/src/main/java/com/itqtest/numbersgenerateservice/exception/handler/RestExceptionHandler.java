package com.itqtest.numbersgenerateservice.exception.handler;

import com.itqtest.numbersgenerateservice.exception.InsufficientStorageException;
import com.itqtest.numbersgenerateservice.exception.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Глобальный обработчик исключений для REST-контроллеров.
 * Обрабатывает исключения, возникающие в контроллерах, и возвращает соответствующие ответы клиенту.
 *
 * @author Egor Nazarev
 */
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * Обрабатывает исключение InternalServerError и возвращает HTTP-ответ с кодом 500 Internal Server Error
     * @param runtimeException исключение InternalServerError
     * @return ответ с сообщением об ошибке и кодом состояния 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    public ErrorResponseDto handleInternalServerErrorException(RuntimeException runtimeException) {
        return new ErrorResponseDto(runtimeException.getMessage());
    }

    /**
     * Обрабатывает исключение InsufficientStorage и возвращает HTTP-ответ с кодом 507 Insufficient Storage
     * @param runtimeException исключение InsufficientStorage
     * @return ответ с сообщением об ошибке и кодом состояния 507
     */
    @ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
    @ExceptionHandler(InsufficientStorageException.class)
    public ErrorResponseDto handleInsufficientStorageException(RuntimeException runtimeException) {
        return new ErrorResponseDto(runtimeException.getMessage());
    }
}
