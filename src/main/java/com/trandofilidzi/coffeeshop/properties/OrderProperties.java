package com.trandofilidzi.coffeeshop.properties;

import java.math.BigDecimal;

public interface OrderProperties {

    BigDecimal getMinCoffeeQuantity();

    long getOneHour();

    long getTimeToDelivery();

    String getByCourierDelivery();

    String getPickupDelivery();

    BigDecimal getDeliveryPrice();

    long getTimeToOrderProcessing();
}
