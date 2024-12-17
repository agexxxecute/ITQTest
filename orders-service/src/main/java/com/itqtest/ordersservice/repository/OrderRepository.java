package com.itqtest.ordersservice.repository;

import com.itqtest.ordersservice.entity.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с заказами в базе данных.
 *
 * @author Egor Nazarev
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    /**
     * Метод для поиска заказов за заданную дату и больше заданной общей суммы заказа.
     * @param date дата.
     * @param price минимальная стоимость.
     * @return список найденных заказов.
     */
    @Query("""
    SELECT * FROM orders
    WHERE date = :date
    AND price > :price
    """)
    List<Order> findByDateAndPrice(LocalDate date, BigDecimal price);

    /**
     * Метод для поиска заказов, не содержащих заданный товар и поступивших в заданный временной период.
     * @param article артикул товара.
     * @param beginDate дата начала периода.
     * @param endDate дата окончания периода.
     * @return список найденных заказов.
     */
    @Query("""
    SELECT * FROM orders o
    WHERE NOT EXISTS (
              SELECT *
              FROM order_details od
              WHERE od.orders_id = o.order_id AND od.article = :article
          )
    AND o.date BETWEEN :beginDate AND :endDate
    """)
    List<Order> findByArticleAndDateBetween(Long article, LocalDate beginDate, LocalDate endDate);
}
