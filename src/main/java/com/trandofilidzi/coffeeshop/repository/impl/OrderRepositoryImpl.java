package com.trandofilidzi.coffeeshop.repository.impl;

import com.trandofilidzi.coffeeshop.model.Order;
import com.trandofilidzi.coffeeshop.repository.OrderRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRepositoryImpl.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void createOrder(Order order) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.save(order);
        LOGGER.info("Order has created. Order details: = {}", order);
    }

    @Override
    public void updateOrder(Order order) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.update(order);
        LOGGER.info("Order has updated. Order details: = {}", order);
    }

    @Override
    public void deleteOrder(long orderId) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Order order = session.load(Order.class, new Long(orderId));
        if (order != null) {
            session.delete(order);
        }
        LOGGER.info("Order has deleted. Order details: = {}", order);
    }

    @Override
    public Order getOrderById(long orderId) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Order order = session.load(Order.class, new Long(orderId));
        LOGGER.info("Order has loaded. Order details: = {}", order);
        return order;
    }

    @Override
    public List<Order> listOrders() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<Order> orderList = session.createQuery("from Order").list();
        LOGGER.info("All orders has loaded from db. Total list size = {}", orderList.size());
        return orderList;
    }
}
