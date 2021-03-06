package com.trandofilidzi.coffeeshop.beans;

import com.trandofilidzi.coffeeshop.CoffeeShopApplicationTests;
import org.junit.Before;
import org.junit.Test;

public class OrderBeanTest extends CoffeeShopApplicationTests {

    private OrderBean orderBean;

    @Before
    public void setUp() throws Exception {
        orderBean = new OrderBean();
    }

    @Test(expected = NullPointerException.class)
    public void test_deleteOrderWhenOrderIdInNull() {
        orderBean.deleteOrder(null);
    }
}