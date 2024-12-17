package com.itqtest.ordersservice.exception.handler;

/**
 * DTO для представления сообщения об ошибке.
 * Используется для передачи сообщений об ошибках от сервисов или контроллеров
 *
 * @author Egor Nazarev
 */
public record ErrorResponseDto(String errorMessage) {}
