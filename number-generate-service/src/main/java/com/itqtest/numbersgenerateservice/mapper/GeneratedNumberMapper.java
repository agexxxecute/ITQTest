package com.itqtest.numbersgenerateservice.mapper;

import com.itqtest.numbersgenerateservice.dto.GeneratedNumberDto;
import com.itqtest.numbersgenerateservice.entity.GeneratedNumber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GeneratedNumberMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "generatedNumber", target = "orderNumber")
    GeneratedNumber toEntity(GeneratedNumberDto generatedNumberDto);
}
