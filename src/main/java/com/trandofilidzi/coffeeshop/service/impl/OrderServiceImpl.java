package com.trandofilidzi.coffeeshop.service.impl;

import com.trandofilidzi.coffeeshop.model.Order;
import com.trandofilidzi.coffeeshop.repository.OrderRepository;
import com.trandofilidzi.coffeeshop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public void createOrder(Order order) {
        orderRepository.createOrder(order);
        LOGGER.info("Order created");
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    @Override
    @Transactional
    public void deleteOrder(long orderId) {
        orderRepository.deleteOrder(orderId);
    }

    @Override
    @Transactional
    public Order getOrderById(long orderId) {
        Order order = orderRepository.getOrderById(orderId);
        return order;
    }

    @Override
    @Transactional
    public List<Order> listOrders() {
        List<Order> orderList = orderRepository.listOrders();
        return orderList;
    }
}
