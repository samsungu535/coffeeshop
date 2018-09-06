package com.trandofilidzi.coffeeshop.beans;

import com.google.common.base.Preconditions;
import com.trandofilidzi.coffeeshop.model.Coffee;
import com.trandofilidzi.coffeeshop.model.SubOrder;
import com.trandofilidzi.coffeeshop.properties.OrderProperties;
import com.trandofilidzi.coffeeshop.utils.MatcherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Named
public class SubOrderBean implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubOrderBean.class);
    @Autowired
    private OrderProperties orderProperties;

    @Inject
    private OrderBean orderBean;
    private String quantity;
    private Coffee coffee;
    private List<SubOrder> subOrderList = new ArrayList<>();

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public List<SubOrder> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<SubOrder> subOrderList) {
        this.subOrderList = subOrderList;
    }

    public void addToBucket() {
        if (MatcherUtil.quantityValidate(quantity)) {
            BigDecimal coffeeQuantity = BigDecimal.valueOf(Long.parseLong(quantity));
            if (coffeeQuantity.compareTo(orderProperties.getMinCoffeeQuantity()) == 1 ||
                    coffeeQuantity.compareTo(orderProperties.getMinCoffeeQuantity()) == 0) {
                BigDecimal subOrderTotalPrice = coffee.getCoffeePricePerGram().multiply(coffeeQuantity);
                SubOrder subOrder = new SubOrder(coffee.toString(), coffee, Integer.parseInt(quantity), subOrderTotalPrice);
                subOrder.setInternalSubOrderId(subOrder.hashCode());
                orderBean.setOrderTotalPrice(orderBean.getOrderTotalPrice().add(subOrderTotalPrice));
                subOrderList.add(subOrder);
                clearFormAfterSuborderCreated();
                LOGGER.info("SubOrder {} added to bucket", subOrder);
            } else {
                LOGGER.info("The quantity is very small, quantity = {}", quantity);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "The quantity is very small", "Minimal quantity is" + orderProperties.getMinCoffeeQuantity()));
            }
        } else {
            LOGGER.info("Invalid format of quantity");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid format of quantity", "Enter the number"));
        }
    }

    public void deleteFromBucket(String internalSubOrderId) {
        Preconditions.checkNotNull(internalSubOrderId, "OrderBean.deleteFromBucket. subOrderId is null");

        if (subOrderList.size() == 1) {
            subOrderList.clear();
            orderBean.setOrderTotalPrice(BigDecimal.ZERO);
            LOGGER.info("The bucket is empty");
            return;
        }
        Iterator<SubOrder> iterator = subOrderList.iterator();
        while (iterator.hasNext()) {
            SubOrder subOrder = iterator.next();
            long id = subOrder.getInternalSubOrderId();
            long incomeId = Integer.parseInt(internalSubOrderId);
            if (id == incomeId) {
                orderBean.setOrderTotalPrice(orderBean.getOrderTotalPrice().subtract(subOrder.getSubOrderTotalPrice()));
                iterator.remove();
                LOGGER.info("SubOrder {} deleted from bucket", subOrder);
                return;
            }
        }
    }

    public void clearFormAfterSuborderCreated() {
        quantity = null;
        LOGGER.info("Quantity field refreshed");
    }
}
