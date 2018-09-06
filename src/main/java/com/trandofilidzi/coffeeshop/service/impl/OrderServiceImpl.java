package com.trandofilidzi.coffeeshop.service.impl;

import com.trandofilidzi.coffeeshop.model.Order;
import com.trandofilidzi.coffeeshop.repository.OrderRepository;
import com.trandofilidzi.coffeeshop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        LOGGER.info("Order created");
        return savedOrder;
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
        LOGGER.info("Order updated");
    }

    @Override
    public void deleteOrder(long orderId) {
        orderRepository.deleteById(orderId);
        LOGGER.info("Order deleted");
    }

    @Override
    public Order getOrderById(long orderId) {
        Order order = orderRepository.getOne(orderId);
        LOGGER.info("Order received");
        return order;
    }

    @Override
    public List<Order> listOrders() {
        List<Order> orderList = orderRepository.findAll();
        LOGGER.info("Orders received");
        return orderList;
    }
}
