package com.itqtest.numbersgenerateservice.mapper;

import com.itqtest.numbersgenerateservice.dto.GeneratedNumberDto;
import com.itqtest.numbersgenerateservice.entity.GeneratedNumber;
import com.itqtest.numbersgenerateservice.util.ConstantUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class GeneratedNumberMapperTest {

    private GeneratedNumberMapper generatedNumberMapper;

    private GeneratedNumberDto generatedNumberDto;

    @BeforeEach
    void setUpVariables() {
        generatedNumberMapper = Mappers.getMapper(GeneratedNumberMapper.class);

        generatedNumberDto = new GeneratedNumberDto(ConstantUtil.GENERATED_NUMBER);
    }

    @Test
    @DisplayName("GeneratedNumberDto to GeneratedNumber")
    void generatedNumberDtoToGeneratedNumberSuccessfully() {
        GeneratedNumber generatedNumberResult = generatedNumberMapper.toEntity(generatedNumberDto);

        Assertions.assertNotNull(generatedNumberResult);
        Assertions.assertNull(generatedNumberResult.getId());
        Assertions.assertEquals(generatedNumberResult.getOrderNumber(), generatedNumberDto.generatedNumber());
    }

}