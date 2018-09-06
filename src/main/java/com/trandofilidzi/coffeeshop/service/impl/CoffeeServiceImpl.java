package com.trandofilidzi.coffeeshop.service.impl;

import com.trandofilidzi.coffeeshop.model.Coffee;
import com.trandofilidzi.coffeeshop.repository.CoffeeRepository;
import com.trandofilidzi.coffeeshop.service.CoffeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeServiceImpl implements CoffeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoffeeServiceImpl.class);

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Override
    public Coffee getCoffeeById(long id) {
        Coffee coffee = coffeeRepository.getOne(id);
        LOGGER.debug("Coffee with id = {} has found", id);
        return coffee;
    }

    @Override
    public List<Coffee> listCoffee() {
        List<Coffee> coffeeList = coffeeRepository.findAll();
        LOGGER.debug("All coffee has found. Total list size = {}", coffeeList.size());
        return coffeeList;
    }
}
