package com.trandofilidzi.coffeeshop.beans.convertors;

import com.trandofilidzi.coffeeshop.model.Coffee;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
public class CoffeeConverter implements Converter, Serializable {

    private List<Coffee> coffeeList = new ArrayList<>();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return coffeeList.get(Integer.parseInt(value) - 1);
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


}
