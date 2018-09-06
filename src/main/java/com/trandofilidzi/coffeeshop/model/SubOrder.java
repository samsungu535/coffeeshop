package com.trandofilidzi.coffeeshop.model;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity(name = "t_sub_order")
public class SubOrder {

    private long subOrderId;
    volatile private long internalSubOrderId;
    volatile private String coffeeToString;
    private Coffee coffee;
    private int subOrderCoffeeQuantity;
    private BigDecimal subOrderTotalPrice;
    private Order order;

    public SubOrder() {
    }

    public SubOrder(long internalSubOrderId, Coffee coffee, int subOrderCoffeeQuantity, BigDecimal subOrderTotalPrice) {
        this.internalSubOrderId = internalSubOrderId;
        this.coffee = coffee;
        this.subOrderCoffeeQuantity = subOrderCoffeeQuantity;
        this.subOrderTotalPrice = subOrderTotalPrice;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @OneToOne(mappedBy = "subOrder")
    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_order_id", nullable = false)
    public long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(long subOrderId) {
        this.subOrderId = subOrderId;
    }

    @Column(name = "sub_order_total_price", nullable = false)
    public BigDecimal getSubOrderTotalPrice() {
        return subOrderTotalPrice;
    }

    public void setSubOrderTotalPrice(BigDecimal subOrderTotalPrice) {
        this.subOrderTotalPrice = subOrderTotalPrice;
    }

    @Column(name = "sub_order_coffee_quantity", nullable = false)
    public int getSubOrderCoffeeQuantity() {
        return subOrderCoffeeQuantity;
    }

    public void setSubOrderCoffeeQuantity(int subOrderCoffeeQuantity) {
        this.subOrderCoffeeQuantity = subOrderCoffeeQuantity;
    }

    public long getInternalSubOrderId() {
        return internalSubOrderId;
    }

    public void setInternalSubOrderId(long internalSubOrderId) {
        this.internalSubOrderId = internalSubOrderId;
    }

    public String getCoffeeToString() {
        return coffee.toString();
    }

    public void setCoffeeToString(String coffeeToString) {
        this.coffeeToString = coffeeToString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubOrder subOrder = (SubOrder) o;
        return subOrderId == subOrder.subOrderId &&
                subOrderCoffeeQuantity == subOrder.subOrderCoffeeQuantity &&
                Objects.equal(coffee, subOrder.coffee) &&
                Objects.equal(subOrderTotalPrice, subOrder.subOrderTotalPrice) &&
                Objects.equal(order, subOrder.order);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(subOrderId, coffee, subOrderCoffeeQuantity, subOrderTotalPrice, order);
    }

    @Override
    public String toString() {
        return coffeeToString + ", quantity=" + subOrderCoffeeQuantity + ", price=" + subOrderTotalPrice;
    }
}
