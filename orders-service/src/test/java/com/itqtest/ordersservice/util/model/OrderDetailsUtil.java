package com.itqtest.ordersservice.util.model;

import com.itqtest.ordersservice.entity.OrderDetails;
import com.itqtest.ordersservice.util.ConstantUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderDetailsUtil {
    public static OrderDetails getValidOrderDetails(){
        return OrderDetails.builder()
            .orderDetailsId(ConstantUtil.VALID_ORDER_DETAILS_ID)
            .article(ConstantUtil.VALID_ARTICLE)
            .productName(ConstantUtil.PRODUCT_NAME)
            .quantity(ConstantUtil.QUANTITY)
            .price(ConstantUtil.ORDER_DETAILS_PRICE)
            .build();
    }
}
