package com.trandofilidzi.coffeeshop.beans;

import com.trandofilidzi.coffeeshop.model.Coffee;
import com.trandofilidzi.coffeeshop.model.Order;
import com.trandofilidzi.coffeeshop.model.notentitymodel.SubOrder;
import com.trandofilidzi.coffeeshop.properties.OrderProperties;
import com.trandofilidzi.coffeeshop.service.OrderService;
import com.trandofilidzi.coffeeshop.utils.MatcherUtil;
import com.trandofilidzi.coffeeshop.utils.RedirectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Named
public class OrderBean implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderBean.class);
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProperties orderProperties;
    private Order order;
    private List<Order> orderList = new ArrayList<>();
    private String quantity;
    private SubOrder subOrder;
    private List<SubOrder> subOrderList = new ArrayList<>();
    private Coffee coffee;
    private int orderTotalPrice;
    private String delivery;
    private Date dateFrom;
    private Date minDateFrom;
    private Date dateTo;
    private Date maxDateTo;
    private Date minDateTo;

    @PostConstruct
    public void init() {
        delivery = orderProperties.getPickupDelivery();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Order> getOrderList() {
        return orderService.listOrders();
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public Date getMinDateFrom() {
        return new Date(new Date().getTime() + orderProperties.getOneHour() * orderProperties.getTimeToDelivery());
    }

    public void setMinDateFrom(Date minDateFrom) {
        this.minDateFrom = minDateFrom;
    }

    public Date getMaxDateTo() {
        return maxDateTo;
    }

    public void setMaxDateTo(Date maxDateTo) {
        this.maxDateTo = maxDateTo;
    }

    public Date getMinDateTo() {
        return minDateTo;
    }

    public void setMinDateTo(Date minDateTo) {
        this.minDateTo = minDateTo;
    }

    public int getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(int orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
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

    public void onDateSelect() {
        minDateTo = new Date(dateFrom.getTime() + orderProperties.getOneHour() * orderProperties.getTimeToOrderProcessing());
        maxDateTo = new Date(minDateTo.getTime() + orderProperties.getOneHour() * orderProperties.getTimeToDelivery());
    }

    public SubOrder getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(SubOrder subOrder) {
        this.subOrder = subOrder;
    }

    public List<SubOrder> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<SubOrder> subOrderList) {
        this.subOrderList = subOrderList;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void addToBucket() {
        if (MatcherUtil.quantityValidate(quantity)) {
            int coffeeQuantity = Integer.parseInt(quantity);
            if (coffeeQuantity >= orderProperties.getMinCoffeeQuantity()) {
                int subOrderTotalPrice = new Double(coffeeQuantity * coffee.getCoffeePricePerGram()).intValue();
                SubOrder subOrder = new SubOrder(atomicInteger.incrementAndGet(), coffee, Integer.parseInt(quantity), subOrderTotalPrice);
                subOrderList.add(subOrder);
                orderTotalPrice = orderTotalPrice + subOrderTotalPrice;
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

    public void deleteFromBucket(String subOrderId) {
        if (subOrderList.size() == 1) {
            subOrderList.clear();
            orderTotalPrice = 0;
            LOGGER.info("The bucket is empty");
            return;
        }
        Iterator<SubOrder> iterator = subOrderList.iterator();
        while (iterator.hasNext()) {
            SubOrder subOrder = iterator.next();
            if (subOrder.getSubOrderId() == Integer.parseInt(subOrderId)) {
                orderTotalPrice = orderTotalPrice - subOrder.getSubOrderTotalPrice();
                iterator.remove();
                LOGGER.info("SubOrder {} deleted from bucket", subOrder);
                return;
            }
        }
    }

    public void onDeliveryChange() {
        if (delivery.equals(orderProperties.getByCourierDelivery())) {
            orderTotalPrice = orderTotalPrice + orderProperties.getDeliveryPrice();
            LOGGER.info("The total price is increased by the price of delivery: {}", orderProperties.getDeliveryPrice());
        } else if (delivery.equals(orderProperties.getPickupDelivery())) {
            orderTotalPrice = orderTotalPrice - orderProperties.getDeliveryPrice();
            LOGGER.info("The total price is reduced by the price of delivery: {}", orderProperties.getDeliveryPrice());
        }
    }

    public void createOrder() {
        if (!subOrderList.isEmpty()) {
            Order order = new Order();
            if (delivery.equals(orderProperties.getByCourierDelivery())) {
                order.setDeliver(true);
                order.setDeliverDateFrom(dateFrom);
                order.setDeliverDateTo(dateTo);
            } else if (delivery.equals(orderProperties.getPickupDelivery())) {
                order.setDeliver(false);
            }
            List<Coffee> coffeeList = new ArrayList<>();
            for (SubOrder subOrder : subOrderList) {
                coffeeList.add(subOrder.getCoffee());
            }
            order.setCoffeeList(coffeeList);
            order.setOrderTotalPrice(orderTotalPrice);
            orderService.createOrder(order);
            clearFormAfterOrderCreated();
            RedirectUtil.redirectToHomePage();
            LOGGER.info("Order created");
            return;
        } else {
            LOGGER.info("SubOrder list is empty");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Bucket is empty", "Please choose any more coffee"));
        }
    }

    public void deleteOrder(long orderId) {
        orderService.deleteOrder(orderId);
        LOGGER.info("SubOrder with id: {} deleted" + orderId);
    }

    public void createNewOrder() {
        RedirectUtil.redirectEditCreateOrderPage();
    }

    public void clearFormAfterOrderCreated() {
        delivery = orderProperties.getPickupDelivery();
        dateFrom = null;
        dateTo = null;
        orderTotalPrice = 0;
        quantity = null;
        subOrderList.clear();
        coffee = null;
        LOGGER.info("Creating order form refreshed");
    }

    public void clearFormAfterSuborderCreated() {
        quantity = null;
        LOGGER.info("Quantity field refreshed");
    }
}
