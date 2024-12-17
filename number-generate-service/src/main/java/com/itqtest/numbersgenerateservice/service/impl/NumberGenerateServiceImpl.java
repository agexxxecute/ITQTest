package com.itqtest.numbersgenerateservice.service.impl;

import com.itqtest.numbersgenerateservice.dto.GeneratedNumberDto;
import com.itqtest.numbersgenerateservice.entity.GeneratedNumber;
import com.itqtest.numbersgenerateservice.exception.InsufficientStorageException;
import com.itqtest.numbersgenerateservice.mapper.GeneratedNumberMapper;
import com.itqtest.numbersgenerateservice.repository.GeneratedNumberRepository;
import com.itqtest.numbersgenerateservice.service.NumberGenerateService;
import com.itqtest.numbersgenerateservice.util.ExceptionMessage;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Реализация интерфейса генерации номера заказа.
 *
 * @author Egor Nazarev
 */
@Service
@RequiredArgsConstructor
public class NumberGenerateServiceImpl implements NumberGenerateService {

    private final GeneratedNumberRepository generatedNumberRepository;
    private final GeneratedNumberMapper generatedNumberMapper;

    private Set<Integer> todayNumbers = new HashSet<>();
    private Random random = new Random();

    private final Integer MAX_NUMBER = 100_100;

    /**
     * Реализация метода для генерации номера заказа.
     * @return Dto со сгенерированным номером заказа.
     */
    @Override
    public GeneratedNumberDto generateNumber() {
        GeneratedNumberDto generatedNumberDto = new GeneratedNumberDto(generateUniqueRandomNumber() + LocalDate.now().toString().replace("-", ""));
        GeneratedNumber generatedNumber = generatedNumberMapper.toEntity(generatedNumberDto);
        generatedNumberRepository.save(generatedNumber);
        return generatedNumberDto;
    }

    /**
     * Метод генерирует уникальное случайное число от 0 до 99 999.
     * @return сгенерированное число в формате строки длинной в 5 символов.
     */
    private String generateUniqueRandomNumber() {
        if (todayNumbers.size() >= MAX_NUMBER) {
            throw new InsufficientStorageException(ExceptionMessage.INSUFFICIENT_STORAGE_ERROR.getDescription());
        }
        int number;
        do {
            number = random.nextInt(MAX_NUMBER);
        } while (todayNumbers.contains(number));
        todayNumbers.add(number);
        return String.format("%05d", number);
    }

    /**
     * Метод, очищающий список сгенерированных чисел ежедневно.
     */
    @Scheduled(cron = "@daily")
    private void clearNumbersSetDaily(){
        todayNumbers.clear();
    }
}
