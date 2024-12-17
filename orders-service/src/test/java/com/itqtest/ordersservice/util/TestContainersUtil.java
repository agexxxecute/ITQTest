package com.itqtest.ordersservice.util;

import com.itqtest.ordersservice.entity.Order;
import com.itqtest.ordersservice.entity.OrderDetails;
import com.itqtest.ordersservice.entity.enums.DeliveryType;
import com.itqtest.ordersservice.entity.enums.PaymentType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestContainersUtil {
    public static List<OrderDetails> orderDetails = List.of(
        new OrderDetails(1L, 87894L, "Ноутбук", 1, BigDecimal.valueOf(200)),
        new OrderDetails(2L, 8884L, "Клавиатура", 2, BigDecimal.valueOf(50)),
        new OrderDetails(3L, 64541L, "Стереосистема", 1, BigDecimal.valueOf(300)));

    public static List<Order> orders = List.of(
        new Order(1L, "1111120240101", BigDecimal.valueOf(100), LocalDate.of(2024, 1, 1), "Иванов И.И.", "Москва, Мансуровский переулок, 9",
            PaymentType.CASH.toString(), DeliveryType.SELF_DELIVERY.toString(), Set.of(orderDetails.get(1))),
        new Order(2L, "9876520240603", BigDecimal.valueOf(50), LocalDate.of(2024,6,3), "Петров А.А.", "Санкт-Петербург, Думская, 3",
            PaymentType.CARD.toString(), DeliveryType.DOOR_DELIVERY.toString(), Set.of()),
        new Order(3L, "5465220241212", BigDecimal.valueOf(500), LocalDate.of(2024,12,12), "Сидоров Р.Д.", "Самара, Ленина, 52",
            PaymentType.CARD.toString(), DeliveryType.SELF_DELIVERY.toString(), Set.of(orderDetails.get(0), orderDetails.get(2))));
}
