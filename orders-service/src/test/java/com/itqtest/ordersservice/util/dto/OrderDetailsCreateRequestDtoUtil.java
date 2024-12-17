package com.itqtest.ordersservice.util.dto;

import com.itqtest.ordersservice.dto.request.OrderDetailsCreateRequestDto;
import com.itqtest.ordersservice.util.ConstantUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderDetailsCreateRequestDtoUtil {
    public static OrderDetailsCreateRequestDto getValidOrderDetailsCreateRequestDto() {
        return OrderDetailsCreateRequestDto.builder()
            .article(ConstantUtil.VALID_ARTICLE)
            .productName(ConstantUtil.PRODUCT_NAME)
            .quantity(ConstantUtil.QUANTITY)
            .price(ConstantUtil.ORDER_DETAILS_PRICE)
            .build();
    }
}
