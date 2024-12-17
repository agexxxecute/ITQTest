package com.itqtest.ordersservice.exception.handler;

import com.itqtest.ordersservice.exception.BadRequestException;
import com.itqtest.ordersservice.exception.InternalServerErrorException;
import com.itqtest.ordersservice.exception.NotFoundException;
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
     * Обрабатывает исключение NotFoundException и возвращает HTTP-ответ с кодом 404 Not Found
     * @param runtimeException исключение NotFoundException
     * @return ответ с сообщением об ошибке и кодом состояния 404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponseDto handleNotFoundException(RuntimeException runtimeException) {
        return new ErrorResponseDto(runtimeException.getMessage());
    }

    /**
     * Обрабатывает исключение BadRequestException и возвращает HTTP-ответ с кодом 400 Bad Request
     * @param runtimeException исключение BadRequestException
     * @return ответ с сообщением об ошибке и кодом состояния 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponseDto handleBadRequestException(RuntimeException runtimeException) {
        return new ErrorResponseDto(runtimeException.getMessage());
    }

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
}
