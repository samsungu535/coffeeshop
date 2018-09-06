package com.trandofilidzi.coffeeshop.service.impl;

import com.trandofilidzi.coffeeshop.model.SubOrder;
import com.trandofilidzi.coffeeshop.repository.SubOrderRepository;
import com.trandofilidzi.coffeeshop.service.SubOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubOrderServiceImpl implements SubOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubOrderServiceImpl.class);

    @Autowired
    private SubOrderRepository subOrderRepository;

    @Override
    public List<SubOrder> saveAllSubOrders(List<SubOrder> subOrderList) {
        List<SubOrder> savedSubOrderList = subOrderRepository.saveAll(subOrderList);
        LOGGER.info("Sub order saved {}", savedSubOrderList);
        return savedSubOrderList;
    }

    @Override
    public List<SubOrder> findAllByOrderOrderId(long orderId) {
        List<SubOrder> subOrderList = subOrderRepository.findAllByOrderOrderId(orderId);
        LOGGER.info("Sub order loaded {}", subOrderList);
        return subOrderList;
    }
}
