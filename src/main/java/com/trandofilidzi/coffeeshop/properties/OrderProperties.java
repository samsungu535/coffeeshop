package com.trandofilidzi.coffeeshop.properties;

public interface OrderProperties {

    int getMinCoffeeQuantity();
    long getOneHour();
    long getTimeToDelivery();
    String getByCourierDelivery();
    String getPickupDelivery();
    int getDeliveryPrice();
    long getTimeToOrderProcessing();
}
