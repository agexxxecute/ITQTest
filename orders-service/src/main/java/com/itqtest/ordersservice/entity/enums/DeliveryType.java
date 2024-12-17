package com.itqtest.ordersservice.entity.enums;

import lombok.Getter;

@Getter
public enum DeliveryType {
    SELF_DELIVERY("Самовывоз"),
    DOOR_DELIVERY("Доставка до двери");

    private final String description;

    DeliveryType(String description){
        this.description = description;
    }
}
