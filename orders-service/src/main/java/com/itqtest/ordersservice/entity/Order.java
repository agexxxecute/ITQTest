package com.itqtest.ordersservice.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Сущность, описывающая заказ.
 *
 * @author Egor Nazarev
 */
@Table("orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column("order_id")
    private Long orderId;

    @Column("number")
    private String number;

    @Column("price")
    private BigDecimal price;

    @Column("date")
    private LocalDate date;

    @Column("recipient")
    private String recipient;

    @Column("delivery_address")
    private String deliveryAddress;

    @Column("payment_type")
    private String paymentType;

    @Column("delivery_type")
    private String deliveryType;

    @MappedCollection(idColumn = "orders_id")
    private Set<OrderDetails> orderDetails;
}
