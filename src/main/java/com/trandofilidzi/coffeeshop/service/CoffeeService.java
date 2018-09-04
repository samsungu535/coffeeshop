package com.trandofilidzi.coffeeshop.service;

import com.trandofilidzi.coffeeshop.model.Coffee;

import java.util.List;

public interface CoffeeService {

  Coffee getCoffeeById(Long id);

  List<Coffee> listCoffee();
}
