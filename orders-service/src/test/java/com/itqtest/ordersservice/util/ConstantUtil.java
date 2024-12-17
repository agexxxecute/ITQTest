package com.itqtest.ordersservice.util;

import com.itqtest.ordersservice.entity.enums.DeliveryType;
import com.itqtest.ordersservice.entity.enums.PaymentType;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantUtil {
    public static final Long VALID_ORDER_ID = 1L;
    public static final Long INVALID_ORDER_ID = 0L;
    public static final String VALID_ORDER_NUMBER = "1111120240101";
    public static final BigDecimal ORDER_PRICE = new BigDecimal("100");
    public static final BigDecimal INVALID_ORDER_PRICE = new BigDecimal("-1");
    public static final LocalDate ORDER_DATE = LocalDate.of(2024, 12, 12);
    public static final String ORDER_RECIPIENT = "Иванов И.И.";
    public static final String INVALID_ORDER_RECIPIENT = "-";
    public static final String DELIVERY_ADDRESS = "Москва, Мансуровский переулок, 9";
    public static final PaymentType ORDER_PAYMENT_TYPE = PaymentType.CASH;
    public static final DeliveryType ORDER_DELIVERY_TYPE = DeliveryType.SELF_DELIVERY;
    public static final LocalDate BEGIN_DATE = LocalDate.of(2024, 1, 1);
    public static final BigDecimal MIN_PRICE = new BigDecimal("50");
    public static final LocalDate UNEXPECTED_DATE = LocalDate.of(2025, 1, 1);
    public static final LocalDate UNEXPECTED_END_DATE = LocalDate.of(2026, 1, 1);

    public static final Long VALID_ORDER_DETAILS_ID = 1L;
    public static final Long VALID_ARTICLE = 87894L;
    public static final Long INVALID_ARTICLE = -1L;
    public static final String PRODUCT_NAME = "Ноутбук";
    public static final Integer QUANTITY = 1;
    public static final BigDecimal ORDER_DETAILS_PRICE = new BigDecimal("200");

}
