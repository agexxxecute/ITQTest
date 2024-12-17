package com.itqtest.ordersservice.util.dto;

import com.itqtest.ordersservice.dto.request.OrderFindByArticleAndDateBetweenDto;
import com.itqtest.ordersservice.util.ConstantUtil;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderFindByArticleAndDateBetweenUtil {
    public static OrderFindByArticleAndDateBetweenDto getValidOrderFindByArticleAndDateBetweenDto() {
        return OrderFindByArticleAndDateBetweenDto.builder()
            .article(ConstantUtil.VALID_ARTICLE)
            .beginDate(ConstantUtil.BEGIN_DATE)
            .endDate(ConstantUtil.ORDER_DATE)
            .build();
    }

    public static OrderFindByArticleAndDateBetweenDto getInvalidOrderFindByArticleAndDateBetweenDto() {
        return OrderFindByArticleAndDateBetweenDto.builder()
            .article(ConstantUtil.INVALID_ARTICLE)
            .beginDate(ConstantUtil.BEGIN_DATE)
            .endDate(ConstantUtil.ORDER_DATE)
            .build();
    }
}
