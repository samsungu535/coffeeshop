package com.trandofilidzi.coffeeshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_order")
public class Order {

    private long order_id;
    private boolean isDeliver;
    private Date deliverDateFrom;
    private Date deliverDateTo;
    private BigDecimal orderTotalPrice;
    private List<Coffee> coffeeList = new ArrayList<>();

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    public List<Coffee> getCoffeeList() {
        return coffeeList;
    }

    public void setCoffeeList(List<Coffee> coffeeList) {
        this.coffeeList = coffeeList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    public long getOrderId() {
        return order_id;
    }

    public void setOrderId(long order_id) {
        this.order_id = order_id;
    }

    @Column(name = "order_is_deliver", nullable = false)
    public boolean isDeliver() {
        return isDeliver;
    }

    public void setDeliver(boolean deliver) {
        isDeliver = deliver;
    }

    @Column(name = "order_deliver_date_from")
    public Date getDeliverDateFrom() {
        return deliverDateFrom;
    }

    public void setDeliverDateFrom(Date deliverDateFrom) {
        this.deliverDateFrom = deliverDateFrom;
    }

    @Column(name = "order_deliver_date_to")
    public Date getDeliverDateTo() {
        return deliverDateTo;
    }

    public void setDeliverDateTo(Date deliverDateTo) {
        this.deliverDateTo = deliverDateTo;
    }

    @Column(name = "order_total_price", nullable = false)
    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", isDeliver=" + isDeliver +
                ", deliverDateFrom=" + deliverDateFrom +
                ", deliverDateTo=" + deliverDateTo +
                ", orderTotalPrice=" + orderTotalPrice +
                ", coffeeList=" + coffeeList +
                '}';
    }
}
