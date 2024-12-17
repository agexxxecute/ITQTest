package com.itqtest.ordersservice.util.dto;

import com.itqtest.ordersservice.dto.response.OrderResponseDto;
import com.itqtest.ordersservice.util.ConstantUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderResponseDtoUtil {
    public static OrderResponseDto getValidOrderResponseDto(){
        return OrderResponseDto.builder()
            .orderId(ConstantUtil.VALID_ORDER_ID)
            .number(ConstantUtil.VALID_ORDER_NUMBER)
            .price(ConstantUtil.ORDER_PRICE)
            .date(ConstantUtil.BEGIN_DATE)
            .recipient(ConstantUtil.ORDER_RECIPIENT)
            .deliveryAddress(ConstantUtil.DELIVERY_ADDRESS)
            .paymentType(ConstantUtil.ORDER_PAYMENT_TYPE)
            .deliveryType(ConstantUtil.ORDER_DELIVERY_TYPE)
            .build();
    }
}
