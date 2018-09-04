package com.trandofilidzi.coffeeshop.beans.convertors;

import com.trandofilidzi.coffeeshop.model.Coffee;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Named
public class OrderDto {

    @Value("${minimalQuantity}")
    private static final long MIN = 100;
    private List<SubOrder> subOrderList = new ArrayList<>();

    private String quantity;
    private String coffeeId;
    private String delivery;
    private String subOrderListSize;
    private Date dateFrom;
    private Date dateTo;
    private Date maxDateTo;
    private BigDecimal totalPrice;
    private Coffee coffee;


    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public Date getMaxDateTo() {
        return maxDateTo;
    }



    public void setMaxDateTo(Date maxDateTo) {
        this.maxDateTo = maxDateTo;
    }

    public String getSubOrderListSize() {
        return String.valueOf(subOrderList.size());
    }

    public void setSubOrderListSize(String subOrderListSize) {
        this.subOrderListSize = subOrderListSize;
    }

    public List<SubOrder> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<SubOrder> subOrderList) {
        this.subOrderList = subOrderList;
    }

    public void addToBucket(){
        System.out.println("OrderDto.addToBucket " + coffee.toString());
        subOrderList.add(new SubOrder(coffee.getCoffeeName(), quantity));
        System.out.println("OrderDto.addToBucket " +  subOrderList.size());
    }

    public String getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(String coffeeId) {
        this.coffeeId = coffeeId;
    }


    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public BigDecimal getTotalPrice() {
        if (totalPrice.compareTo(BigDecimal.valueOf(MIN)) == 1){
            return totalPrice;
        }else {
            return BigDecimal.valueOf(MIN);
        }
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void onDateSelect(SelectEvent event) {
        maxDateTo = new Date(dateFrom.getTime() + 3600*1000*2);
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
            this.quantity = quantity;
    }
}
