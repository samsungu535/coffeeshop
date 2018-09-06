package com.trandofilidzi.coffeeshop.repository;

import com.trandofilidzi.coffeeshop.model.SubOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubOrderRepository extends JpaRepository<SubOrder, Long> {
}
