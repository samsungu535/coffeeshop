package com.trandofilidzi.coffeeshop.properties.impl;

import com.trandofilidzi.coffeeshop.properties.OrderProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderPropertiesImpl implements OrderProperties {

    @Value("${min.coffee.quantity}")
    private int minCoffeeQuantity;

    @Value("${one.hour}")
    private long oneHour;

    @Value("${time.to.delivery}")
    private long timeToDelivery;

    @Value("${by.courier.delivery}")
    private String byCourierDelivery;

    @Value("${pickup.delivery}")
    private String pickupDelivery;

    @Value("${delivery.price}")
    private int deliveryPrice;

    @Override
    public int getMinCoffeeQuantity() {
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
    public int getDeliveryPrice() {
        return deliveryPrice;
    }
}
