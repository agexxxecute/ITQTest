package com.itqtest.numbersgenerateservice.service;

import com.itqtest.numbersgenerateservice.dto.GeneratedNumberDto;

/**
 * Интерфейс, описывающий метод для генерации номера заказа.
 *
 * @author Egor Nazarev
 */
public interface NumberGenerateService {

    /**
     * Метод для генерации номер заказа.
     * @return Dto со сгенерированным номером заказа
     */
    GeneratedNumberDto generateNumber();
}
