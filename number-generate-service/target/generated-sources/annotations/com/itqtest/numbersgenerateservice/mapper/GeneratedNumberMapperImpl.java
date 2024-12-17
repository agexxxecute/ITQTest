package com.itqtest.numbersgenerateservice.mapper;

import com.itqtest.numbersgenerateservice.dto.GeneratedNumberDto;
import com.itqtest.numbersgenerateservice.entity.GeneratedNumber;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T17:04:22+0400",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class GeneratedNumberMapperImpl implements GeneratedNumberMapper {

    @Override
    public GeneratedNumber toEntity(GeneratedNumberDto generatedNumberDto) {
        if ( generatedNumberDto == null ) {
            return null;
        }

        GeneratedNumber generatedNumber = new GeneratedNumber();

        generatedNumber.setOrderNumber( generatedNumberDto.generatedNumber() );

        return generatedNumber;
    }
}
