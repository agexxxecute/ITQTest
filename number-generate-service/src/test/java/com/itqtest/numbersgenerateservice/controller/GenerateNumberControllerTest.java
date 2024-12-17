package com.itqtest.numbersgenerateservice.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.itqtest.numbersgenerateservice.dto.GeneratedNumberDto;
import com.itqtest.numbersgenerateservice.exception.InsufficientStorageException;
import com.itqtest.numbersgenerateservice.exception.InternalServerErrorException;
import com.itqtest.numbersgenerateservice.service.NumberGenerateService;
import com.itqtest.numbersgenerateservice.util.ConstantUtil;
import com.itqtest.numbersgenerateservice.util.ExceptionMessage;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = GenerateNumberController.class)
class GenerateNumberControllerTest {
    @MockitoBean
    private NumberGenerateService numberGenerateService;

    @Autowired
    private MockMvc mockMvc;

    private GeneratedNumberDto generatedNumberDto;

    private final String GENERATE_NUMBER_URL = "/numbers";

    @BeforeEach
    void setUpVariables() {
        generatedNumberDto = new GeneratedNumberDto(ConstantUtil.GENERATED_NUMBER);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should generate number successfully")
    void generateNumberSuccessfully() {
        when(numberGenerateService.generateNumber()).thenReturn(generatedNumberDto);

        mockMvc.perform(get(GENERATE_NUMBER_URL)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        verify(numberGenerateService).generateNumber();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Internal Server Error and status 500")
    void generateNumberInternalServerError(){
        when(numberGenerateService.generateNumber()).thenThrow(new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()));

        mockMvc.perform(get(GENERATE_NUMBER_URL)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()))
            .andReturn();

        verify(numberGenerateService).generateNumber();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Insufficient Storage and status 507")
    void generateNumberInsufficientStorage(){
        when(numberGenerateService.generateNumber()).thenThrow(new InsufficientStorageException(ExceptionMessage.INSUFFICIENT_STORAGE_ERROR.getDescription()));

        mockMvc.perform(get(GENERATE_NUMBER_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInsufficientStorage())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INSUFFICIENT_STORAGE_ERROR.getDescription()))
            .andReturn();

        verify(numberGenerateService).generateNumber();
    }
}