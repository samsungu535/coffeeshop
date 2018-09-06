package com.trandofilidzi.coffeeshop.service;

import com.trandofilidzi.coffeeshop.model.SubOrder;

import java.util.List;

public interface SubOrderService {
    List<SubOrder> saveAllSubOrders(List<SubOrder> subOrderList);

    List<SubOrder> findAllByOrderOrderId(long orderId);
}
