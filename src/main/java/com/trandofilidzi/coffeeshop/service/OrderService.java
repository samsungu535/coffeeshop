package com.trandofilidzi.coffeeshop.service;

import com.trandofilidzi.coffeeshop.model.Order;

import java.util.List;

public interface OrderService {

    void createOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(long orderId);

    Order getOrderById(long orderId);

    List<Order> listOrders();
}
