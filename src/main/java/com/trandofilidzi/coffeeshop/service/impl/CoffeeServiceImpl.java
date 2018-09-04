package com.trandofilidzi.coffeeshop.service.impl;

import com.trandofilidzi.coffeeshop.model.Coffee;
import com.trandofilidzi.coffeeshop.repository.CoffeeRepository;
import com.trandofilidzi.coffeeshop.service.CoffeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoffeeServiceImpl implements CoffeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoffeeServiceImpl.class);

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Override
    @Transactional
    public Coffee getCoffeeById(Long id) {
        Coffee coffee = coffeeRepository.getCoffeeById(id);
        LOGGER.debug("Coffee with id = {} has found", id);
        return coffee;
    }

    @Override
    @Transactional
    public List<Coffee> listCoffee() {
        List<Coffee> coffeeList = coffeeRepository.listCoffee();
        LOGGER.debug("All coffee has found. Total list size = {}", coffeeList.size());
        return coffeeList;
    }
}
