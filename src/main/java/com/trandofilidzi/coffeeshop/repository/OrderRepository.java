package com.trandofilidzi.coffeeshop.repository;

import com.trandofilidzi.coffeeshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
