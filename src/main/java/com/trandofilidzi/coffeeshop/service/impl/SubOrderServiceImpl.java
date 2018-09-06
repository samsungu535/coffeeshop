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
    public void saveAllSubOrders(List<SubOrder> subOrderList) {
        subOrderRepository.saveAll(subOrderList);
        LOGGER.info("Sub order saved");
    }
}
