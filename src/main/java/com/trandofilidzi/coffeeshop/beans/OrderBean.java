package com.trandofilidzi.coffeeshop.beans;

import com.google.common.base.Preconditions;
import com.trandofilidzi.coffeeshop.model.Order;
import com.trandofilidzi.coffeeshop.model.SubOrder;
import com.trandofilidzi.coffeeshop.properties.OrderProperties;
import com.trandofilidzi.coffeeshop.service.OrderService;
import com.trandofilidzi.coffeeshop.service.SubOrderService;
import com.trandofilidzi.coffeeshop.utils.RedirectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named
public class OrderBean implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderBean.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProperties orderProperties;
    @Autowired
    private SubOrderService subOrderService;
    private Order order;
    private List<Order> orderList = new ArrayList<>();
    @Inject
    private SubOrderBean subOrderBean;
    private BigDecimal orderTotalPrice = BigDecimal.ZERO;
    private String delivery;
    private Date dateFrom;
    private Date minDateFrom;
    private Date dateTo;
    private Date maxDateTo;
    private Date minDateTo;
    private boolean isOrderIdIsNull;

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

    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
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

    public boolean isOrderIdIsNull() {
        if (order != null) {
            if (order.getOrderId() > 0) {
                return isOrderIdIsNull = false;
            } else {
                return isOrderIdIsNull = true;
            }
        }
        return isOrderIdIsNull = true;
    }

    public void setOrderIdIsNull(boolean orderIdIsNull) {
        isOrderIdIsNull = orderIdIsNull;
    }

    public void onDateSelect() {
        minDateTo = new Date(dateFrom.getTime() + orderProperties.getOneHour() * orderProperties.getTimeToOrderProcessing());
        maxDateTo = new Date(minDateTo.getTime() + orderProperties.getOneHour() * orderProperties.getTimeToDelivery());
    }

    public void onDeliveryChange() {
        if (delivery.equals(orderProperties.getByCourierDelivery())) {
            orderTotalPrice = orderTotalPrice.add(orderProperties.getDeliveryPrice());
            LOGGER.info("The total price is increased by the price of delivery: {}", orderProperties.getDeliveryPrice());
        } else if (delivery.equals(orderProperties.getPickupDelivery())) {
            orderTotalPrice = orderTotalPrice.subtract(orderProperties.getDeliveryPrice());
            LOGGER.info("The total price is reduced by the price of delivery: {}", orderProperties.getDeliveryPrice());
        }
    }

    public void createOrder() {
        List<SubOrder> subOrderList = subOrderBean.getSubOrderList();
        if (!subOrderList.isEmpty()) {
            Order order = new Order();
            checkDelivery(order);
            order.setSubOrderList(subOrderBean.getSubOrderList());
            order.setOrderTotalPrice(orderTotalPrice);
            Order savedOrder = orderService.createOrder(order);
            for (SubOrder subOrder : subOrderList) {
                subOrder.setOrder(savedOrder);
            }
            subOrderService.saveAllSubOrders(subOrderList);
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

    public void updateOrder() {
        List<SubOrder> subOrderList = subOrderBean.getSubOrderList();
        if (!subOrderList.isEmpty()) {
            checkDelivery(order);
            order.setSubOrderList(subOrderBean.getSubOrderList());
            order.setOrderTotalPrice(orderTotalPrice);
            Order savedOrder = orderService.createOrder(order);
            for (SubOrder subOrder : subOrderList) {
                subOrder.setOrder(savedOrder);
            }
            orderService.createOrder(order);
            subOrderService.saveAllSubOrders(subOrderList);
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

    public void checkDelivery(Order order) {
        if (delivery.equals(orderProperties.getByCourierDelivery())) {
            order.setDeliver(true);
            order.setDeliverDateFrom(dateFrom);
            order.setDeliverDateTo(dateTo);
        } else if (delivery.equals(orderProperties.getPickupDelivery())) {
            order.setDeliver(false);
        }
    }

    public String editOrder() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        order = orderService.getOrderById(Long.parseLong(params.get("orderId")));
        List<SubOrder> subOrderList = subOrderService.findAllByOrderOrderId(order.getOrderId());
        subOrderBean.setSubOrderList(subOrderList);
        for (SubOrder subOrder : subOrderList) {
            subOrder.setCoffeeToString(subOrder.getCoffee().toString());
            subOrder.setInternalSubOrderId(subOrder.hashCode());
        }
        if (order.isDeliver()) {
            setDelivery(orderProperties.getByCourierDelivery());
        } else {
            setDelivery(orderProperties.getPickupDelivery());
        }
        setDateFrom(order.getDeliverDateFrom());
        setDateTo(order.getDeliverDateTo());
        setOrderTotalPrice(order.getOrderTotalPrice());
        return "editCreateOrder";
    }

    public void deleteOrder(String orderId) {
        Preconditions.checkNotNull(orderId, "OrderBean.deleteOrder. orderId is null");

        orderService.deleteOrder(Long.parseLong(orderId));
        LOGGER.info("SubOrder with id: {} deleted" + orderId);
    }

    public void createNewOrderRedirect() {
        RedirectUtil.redirectEditCreateOrderPage();
    }

    public void clearFormAfterOrderCreated() {
        order = null;
        delivery = orderProperties.getPickupDelivery();
        dateFrom = null;
        dateTo = null;
        orderTotalPrice = BigDecimal.ZERO;
        subOrderBean.getSubOrderList().clear();
        LOGGER.info("Creating order form refreshed");
    }
}
