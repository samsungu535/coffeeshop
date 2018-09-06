package com.trandofilidzi.coffeeshop.beans;

import com.trandofilidzi.coffeeshop.CoffeeShopApplicationTests;
import org.junit.Before;
import org.junit.Test;

public class SubOrderBeanTest extends CoffeeShopApplicationTests {

    private SubOrderBean subOrderBean;

    @Before
    public void setUp() throws Exception {
        subOrderBean = new SubOrderBean();
    }

    @Test(expected = NullPointerException.class)
    public void test_deleteFromBucketWhenSubOrderIdIsNull() {
        subOrderBean.deleteFromBucket(null);
    }

    @Test
    public void test_deleteFromBucketSuccess() {
        subOrderBean.deleteFromBucket("1");
    }
}