package com.trandofilidzi.coffeeshop.repository;

import com.trandofilidzi.coffeeshop.model.SubOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubOrderRepository extends JpaRepository<SubOrder, Long> {
    List<SubOrder> findAllByOrderOrderId(long orderId);
}
