package com.itqtest.ordersservice.util.dto;

import com.itqtest.ordersservice.dto.request.OrderCreateRequestDto;
import com.itqtest.ordersservice.util.ConstantUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderCreateRequestDtoUtil {
    public static OrderCreateRequestDto getValidOrderCreateRequestDto(){
        return OrderCreateRequestDto.builder()
            .price(ConstantUtil.ORDER_PRICE)
            .date(ConstantUtil.ORDER_DATE)
            .recipient(ConstantUtil.ORDER_RECIPIENT)
            .deliveryAddress(ConstantUtil.DELIVERY_ADDRESS)
            .paymentType(ConstantUtil.ORDER_PAYMENT_TYPE)
            .deliveryType(ConstantUtil.ORDER_DELIVERY_TYPE)
            .build();
    }

    public static OrderCreateRequestDto getInvalidOrderCreateRequestDto(){
        return OrderCreateRequestDto.builder()
            .price(ConstantUtil.ORDER_PRICE)
            .date(ConstantUtil.ORDER_DATE)
            .recipient(ConstantUtil.INVALID_ORDER_RECIPIENT)
            .deliveryAddress(ConstantUtil.DELIVERY_ADDRESS)
            .paymentType(ConstantUtil.ORDER_PAYMENT_TYPE)
            .deliveryType(ConstantUtil.ORDER_DELIVERY_TYPE)
            .build();
    }
}
