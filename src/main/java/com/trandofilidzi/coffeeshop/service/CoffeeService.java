package com.trandofilidzi.coffeeshop.service;

import com.trandofilidzi.coffeeshop.model.Coffee;

import java.util.List;

public interface CoffeeService {

    Coffee getCoffeeById(long id);

    List<Coffee> listCoffee();
}
