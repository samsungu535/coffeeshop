package com.trandofilidzi.coffeeshop.beans.convertors;

import java.util.Objects;

public class SubOrder {

    private String coffee;
    private String quantity;

    public SubOrder(String coffee, String quantity) {
        this.coffee = coffee;
        this.quantity = quantity;
    }

    public String getCoffee() {
        return coffee;
    }

    public void setCoffee(String coffee) {
        this.coffee = coffee;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubOrder subOrder = (SubOrder) o;
        return Objects.equals(coffee, subOrder.coffee) &&
                Objects.equals(quantity, subOrder.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffee, quantity);
    }

    @Override
    public String toString() {
        return "SubOrder{" +
                "coffeeId='" + coffee + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
