package com.itqtest.ordersservice.util.dto;

import com.itqtest.ordersservice.dto.request.OrderFindByDateAndPriceDto;
import com.itqtest.ordersservice.util.ConstantUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderFindByDateAndPriceDtoUtil {
    public static OrderFindByDateAndPriceDto getValidOrderFindByDateAndPriceDto() {
        return OrderFindByDateAndPriceDto.builder()
            .orderDate(ConstantUtil.ORDER_DATE)
            .minPrice(ConstantUtil.ORDER_PRICE)
            .build();
    }

    public static OrderFindByDateAndPriceDto getInvalidOrderFindByDateAndPriceDto() {
        return OrderFindByDateAndPriceDto.builder()
            .orderDate(ConstantUtil.ORDER_DATE)
            .minPrice(ConstantUtil.INVALID_ORDER_PRICE)
            .build();
    }
}
