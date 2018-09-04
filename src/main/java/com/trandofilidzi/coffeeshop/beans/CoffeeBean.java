package com.trandofilidzi.coffeeshop.beans;

import com.trandofilidzi.coffeeshop.beans.convertors.SubOrder;
import com.trandofilidzi.coffeeshop.model.Coffee;
import com.trandofilidzi.coffeeshop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
public class CoffeeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CoffeeService coffeeService;

    private List<SubOrder> subOrderList = new ArrayList<>();


    private List<Coffee> coffeeList;
    private Coffee coffee;

    @PostConstruct
    public void init() {
        coffeeList = coffeeService.listCoffee();
    }

    public List<SubOrder> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<SubOrder> subOrderList) {
        this.subOrderList = subOrderList;
    }

    public List<String> completeText(String q) {
        List<String> results = new ArrayList<String>();
        for (Coffee coffee : coffeeList) {
            results.add(coffee.toString());
        }
        return results;
    }


    public List<Coffee> getCoffeeList() {
        return coffeeList;
    }

    public void setCoffeeList(List<Coffee> coffeeList) {
        this.coffeeList = coffeeList;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }


    public void addToBucket(Coffee coffee, String quantity){
        System.out.println("CoffeeBean.addToBucket " + coffee.toString());
        subOrderList.add(new SubOrder(coffee.getCoffeeName(), quantity));
        System.out.println("OrderDto.addToBucket " +  subOrderList.size());
    }
}
