package com.itqtest.ordersservice.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Сущность, описывающая детали заказа.
 *
 * @author Egor Nazarev
 */
@Table("order_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    @Id
    @Column("order_details_id")
    private Long orderDetailsId;

    @Column("article")
    private Long article;

    @Column("product_name")
    private String productName;

    @Column("quantity")
    private Integer quantity;

    @Column("price")
    private BigDecimal price;
}
