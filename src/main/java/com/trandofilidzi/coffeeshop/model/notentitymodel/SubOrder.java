package com.trandofilidzi.coffeeshop.model.notentitymodel;

import com.google.common.base.Objects;
import com.trandofilidzi.coffeeshop.model.Coffee;

import java.math.BigDecimal;

public class SubOrder {

    private int subOrderId;
    private Coffee coffee;
    private int quantity;
    private BigDecimal subOrderTotalPrice;

    public SubOrder(int subOrderId, Coffee coffee, int quantity, BigDecimal subOrderTotalPrice) {
        this.subOrderId = subOrderId;
        this.coffee = coffee;
        this.quantity = quantity;
        this.subOrderTotalPrice = subOrderTotalPrice;
    }

    public int getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(int subOrderId) {
        this.subOrderId = subOrderId;
    }

    public BigDecimal getSubOrderTotalPrice() {
        return subOrderTotalPrice;
    }

    public void setSubOrderTotalPrice(BigDecimal subOrderTotalPrice) {
        this.subOrderTotalPrice = subOrderTotalPrice;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubOrder subOrder = (SubOrder) o;
        return subOrderId == subOrder.subOrderId &&
                quantity == subOrder.quantity &&
                subOrderTotalPrice == subOrder.subOrderTotalPrice &&
                Objects.equal(coffee, subOrder.coffee);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(subOrderId, coffee, quantity, subOrderTotalPrice);
    }

    @Override
    public String toString() {
        return "SubOrder{" +
                "subOrderId=" + subOrderId +
                ", coffee=" + coffee +
                ", quantity=" + quantity +
                ", subOrderTotalPrice=" + subOrderTotalPrice +
                '}';
    }
}
