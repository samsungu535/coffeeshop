package com.trandofilidzi.coffeeshop.service.impl;

import com.trandofilidzi.coffeeshop.beans.convertors.SubOrder;
import com.trandofilidzi.coffeeshop.model.Coffee;
import com.trandofilidzi.coffeeshop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Named
public class CoffeeConverter implements Converter, Serializable {

    private String quantity;
    private List<SubOrder> subOrderList = new ArrayList<>();
    private List<Coffee> coffeeList = new ArrayList<>();
    private Coffee coffee;


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

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                coffee = coffeeList.get(Integer.parseInt(value) - 1);
                return coffee;
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if (object != null) {
            coffeeList.add((Coffee) object);
            return String.valueOf(((Coffee) object).getCoffeeId());
        } else {
            return null;
        }
    }

    public void addToBucket() {
        subOrderList.add(new SubOrder(coffee.getCoffeeName(), quantity));
        System.out.println(coffee.toString());
        System.out.println(quantity);
    }

    public void deleteFromBucket() {
        if (subOrderList.size()==1){
            subOrderList.clear();
            return;
        }
        SubOrder currentSuborder = new SubOrder(coffee.getCoffeeName(), quantity);
        Iterator<SubOrder> iterator = subOrderList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().hashCode() == currentSuborder.hashCode() ) {
                System.out.println("Param iterator.next().getCoffeeId()" + iterator.next().getCoffee() );
                iterator.remove();
                return;
            }
        }
    }

}
