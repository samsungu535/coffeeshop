package com.trandofilidzi.coffeeshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_coffee")
public class Coffee {

    private long coffeeId;
    private String coffeeName;
    private int coffeeArabica;
    private BigDecimal coffeePricePerGram;
    private SubOrder subOrder;

    @OneToOne
    @JoinColumn(name = "sub_order_coffee_id")
    public SubOrder getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(SubOrder subOrder) {
        this.subOrder = subOrder;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coffee_id", nullable = false)
    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    @Column(name = "coffee_name", nullable = false)
    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    @Column(name = "coffee_arabica", nullable = false)
    public int getCoffeeArabica() {
        return coffeeArabica;
    }

    public void setCoffeeArabica(int coffeeArabica) {
        this.coffeeArabica = coffeeArabica;
    }

    public BigDecimal getCoffeePricePerGram() {
        return coffeePricePerGram;
    }

    @Column(name = "coffee_price_per_gram", nullable = false)
    public void setCoffeePricePerGram(BigDecimal coffeePricePerGram) {
        this.coffeePricePerGram = coffeePricePerGram;
    }

    @Override
    public String toString() {
        return coffeeName + ", arabica:" + coffeeArabica + "%";
    }
}
