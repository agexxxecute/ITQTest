package com.itqtest.ordersservice.repository;

import com.itqtest.ordersservice.entity.Order;
import com.itqtest.ordersservice.util.ConstantUtil;
import com.itqtest.ordersservice.util.TestContainersUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class OrderRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17.2");

    @Autowired
    OrderRepository orderRepository;

    private static LocalDate beginDate;
    private static LocalDate endDate;
    private static LocalDate unexpectedDate;
    private static LocalDate unexpectedEndDate;
    private static Long article;
    private static BigDecimal price;
    private static BigDecimal unexpectedPrice;

    @BeforeAll
    static void setUp(){
        article = 87894L;
        price = ConstantUtil.MIN_PRICE;
        beginDate = ConstantUtil.BEGIN_DATE;
        endDate = ConstantUtil.ORDER_DATE;
        unexpectedDate = ConstantUtil.UNEXPECTED_DATE;
        unexpectedPrice = ConstantUtil.ORDER_PRICE;
        unexpectedEndDate = ConstantUtil.UNEXPECTED_END_DATE;
    }

    @Test
    void findByDateAndPriceSuccessfully(){
        List<Order> foundedOrders = orderRepository.findByDateAndPrice(beginDate, price);
        Assertions.assertEquals(1, foundedOrders.size());
        Assertions.assertEquals(TestContainersUtil.orders.get(0), foundedOrders.get(0));
    }

    @Test
    void findByUnexpectedDateAndPrice(){
        List<Order> foundedOrders = orderRepository.findByDateAndPrice(unexpectedDate, price);
        Assertions.assertTrue(foundedOrders.isEmpty());
    }

    @Test
    void findByDateDateAndUnexpectedPrice(){
        List<Order> foundedOrders = orderRepository.findByDateAndPrice(beginDate, unexpectedPrice);
        Assertions.assertTrue(foundedOrders.isEmpty());
    }

    @Test
    void findByArticleAndDateBetweenSuccessfully(){
        List<Order> foundedOrders = orderRepository.findByArticleAndDateBetween(article, beginDate, endDate);
        Assertions.assertEquals(2, foundedOrders.size());
        Assertions.assertEquals(foundedOrders.get(0), TestContainersUtil.orders.get(0));
        Assertions.assertEquals(foundedOrders.get(1), TestContainersUtil.orders.get(1));
    }

    @Test
    void findByArticleAndUnexpectedDateBetween(){
        List<Order> foundedOrders = orderRepository.findByArticleAndDateBetween(article, unexpectedDate, unexpectedEndDate);
        Assertions.assertTrue(foundedOrders.isEmpty());
    }

}
