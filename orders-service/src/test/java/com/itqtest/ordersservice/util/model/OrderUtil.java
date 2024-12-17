package com.itqtest.ordersservice.util.model;

import com.itqtest.ordersservice.entity.Order;
import com.itqtest.ordersservice.util.ConstantUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderUtil {
    public static Order getValidOrder(){
        return Order.builder()
            .orderId(ConstantUtil.VALID_ORDER_ID)
            .number(ConstantUtil.VALID_ORDER_NUMBER)
            .price(ConstantUtil.ORDER_PRICE)
            .date(ConstantUtil.BEGIN_DATE)
            .recipient(ConstantUtil.ORDER_RECIPIENT)
            .deliveryAddress(ConstantUtil.DELIVERY_ADDRESS)
            .paymentType(ConstantUtil.ORDER_PAYMENT_TYPE.toString())
            .deliveryType(ConstantUtil.ORDER_DELIVERY_TYPE.toString())
            .build();
    }
}
