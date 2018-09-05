package com.trandofilidzi.coffeeshop.beans;

import com.trandofilidzi.coffeeshop.model.Coffee;
import com.trandofilidzi.coffeeshop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.List;

@Named
public class CoffeeBean {

    @Autowired
    private CoffeeService coffeeService;

    private List<Coffee> coffeeList;

    @PostConstruct
    public void init() {
        coffeeList = coffeeService.listCoffee();
    }

    public List<Coffee> getCoffeeList() {
        return coffeeList;
    }

    public void setCoffeeList(List<Coffee> coffeeList) {
        this.coffeeList = coffeeList;
    }
}
