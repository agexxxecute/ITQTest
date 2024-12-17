package com.itqtest.numbersgenerateservice.service.impl;

import com.itqtest.numbersgenerateservice.dto.GeneratedNumberDto;
import com.itqtest.numbersgenerateservice.exception.InsufficientStorageException;
import com.itqtest.numbersgenerateservice.mapper.GeneratedNumberMapper;
import com.itqtest.numbersgenerateservice.repository.GeneratedNumberRepository;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NumberGenerateServiceImplTest {
    @Mock
    private GeneratedNumberMapper generatedNumberMapper;

    @Mock
    private GeneratedNumberRepository generatedNumberRepository;

    @InjectMocks
    private NumberGenerateServiceImpl numberGenerateService;

    private Integer VALID_GENERATED_NUMBER_LENGTH = 13;
    private Integer MAX_NUMBER = 100_000;

    @Test
    @DisplayName("Should generate order number successfully")
    void generateNumberSuccessfully(){
        GeneratedNumberDto generatedNumberDtoResult = numberGenerateService.generateNumber();
        String result = generatedNumberDtoResult.generatedNumber();

        Assertions.assertEquals(result.length(), VALID_GENERATED_NUMBER_LENGTH);
        Assertions.assertEquals(result.substring(5), LocalDate.now().toString().replace("-", ""));
    }

    @Test
    @SneakyThrows
    @DisplayName("Should throw Insufficient Storage Exception")
    void generateNumberInsufficientStorageException(){
        Field todayNumbersField = NumberGenerateServiceImpl.class.getDeclaredField("todayNumbers");
        todayNumbersField.setAccessible(true);
        Set<Integer> todayNumbers = (Set<Integer>) todayNumbersField.get(numberGenerateService);

        int maxNumber = 100_100;
        for (int i = 0; i < maxNumber; i++) {
            todayNumbers.add(i);
        }

        Assertions.assertThrows(InsufficientStorageException.class, () -> numberGenerateService.generateNumber());
    }
}