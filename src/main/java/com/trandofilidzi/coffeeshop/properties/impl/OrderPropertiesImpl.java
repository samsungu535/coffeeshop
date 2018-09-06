package com.trandofilidzi.coffeeshop.properties.impl;

import com.trandofilidzi.coffeeshop.properties.OrderProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderPropertiesImpl implements OrderProperties {

    @Value("${min.coffee.quantity}")
    private BigDecimal minCoffeeQuantity;

    @Value("${one.hour}")
    private long oneHour;

    @Value("${time.to.delivery}")
    private long timeToDelivery;

    @Value("${by.courier.delivery}")
    private String byCourierDelivery;

    @Value("${pickup.delivery}")
    private String pickupDelivery;

    @Value("${delivery.price}")
    private BigDecimal deliveryPrice;

    @Value("${time.order.processing}")
    private long timeToOrderProcessing;

    @Override
    public long getTimeToOrderProcessing() {
        return timeToOrderProcessing;
    }

    @Override
    public BigDecimal getMinCoffeeQuantity() {
        return minCoffeeQuantity;
    }

    @Override
    public long getOneHour() {
        return oneHour;
    }

    @Override
    public long getTimeToDelivery() {
        return timeToDelivery;
    }

    @Override
    public String getByCourierDelivery() {
        return byCourierDelivery;
    }

    @Override
    public String getPickupDelivery() {
        return pickupDelivery;
    }

    @Override
    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }
}
