package com.trandofilidzi.coffeeshop.repository;

import com.trandofilidzi.coffeeshop.model.Order;

import java.util.List;

public interface OrderRepository {
  void createOrder(Order order);

  void updateOrder(Order order);

  void deleteOrder(long orderId);

  Order getOrderById(long orderId);

  List<Order> listOrders();
}
