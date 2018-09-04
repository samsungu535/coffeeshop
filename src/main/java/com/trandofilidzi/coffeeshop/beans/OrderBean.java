package com.trandofilidzi.coffeeshop.beans;

import com.trandofilidzi.coffeeshop.model.Coffee;
import com.trandofilidzi.coffeeshop.model.Order;
import com.trandofilidzi.coffeeshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Named
public class OrderBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static long id = -1;
    @Autowired
    private OrderService orderService;

    private static final int MIN = 100;
    private String coffee;
    private List<Order> orderList;
    private Order order;
    private List<Coffee> coffeeList;
    private BigInteger totalPrice;


    public String getCoffee() {
        return coffee;
    }

    public void setCoffee(String coffee) {
        this.coffee = coffee;
    }

    public BigInteger getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigInteger totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        OrderBean.id = id;
    }

   /* @PostConstruct
    public void init() {
        totalPrice = new BigInteger(String.valueOf(MIN *100));
        orderList = orderService.listOrders();
    }*/

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Coffee> getCoffeeList() {
        return coffeeList;
    }

    public void setCoffeeList(List<Coffee> coffeeList) {
        this.coffeeList = coffeeList;
    }


    public void save() {
        if (this.id == 0) {
            this.orderService.createOrder(this.order);
        } else {
            this.orderService.updateOrder(this.order);
        }
    }

    public void deleteOrder(long orderId) {
        try {
            orderService.deleteOrder(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
