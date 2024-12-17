package com.itqtest.numbersgenerateservice.controller;

import com.itqtest.numbersgenerateservice.dto.GeneratedNumberDto;
import com.itqtest.numbersgenerateservice.exception.handler.ErrorResponseDto;
import com.itqtest.numbersgenerateservice.service.NumberGenerateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/numbers")
@AllArgsConstructor
@Tag(name = "Генерация номера заказа", description = "Предоставляет конечную точку для генерации номера заказа")
public class GenerateNumberController {
    private NumberGenerateService numberGenerateService;

    @Operation(description = "Позволяет сгенерировать номер заказа")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Создание заказа произошло успешно"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "507", description = "Достигнуто ограничение количества номеров за день", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GeneratedNumberDto generateNumber() {
       return numberGenerateService.generateNumber();
    }
}
