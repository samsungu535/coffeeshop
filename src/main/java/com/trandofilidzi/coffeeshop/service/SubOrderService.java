package com.trandofilidzi.coffeeshop.service;

import com.trandofilidzi.coffeeshop.model.SubOrder;

import java.util.List;

public interface SubOrderService {
    void saveAllSubOrders(List<SubOrder> subOrderList);
}
