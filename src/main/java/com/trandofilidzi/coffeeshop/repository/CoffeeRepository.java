package com.trandofilidzi.coffeeshop.repository;

import com.trandofilidzi.coffeeshop.model.Coffee;

import java.util.List;

public interface CoffeeRepository {

  Coffee getCoffeeById(Long id);

  List<Coffee> listCoffee();
}
