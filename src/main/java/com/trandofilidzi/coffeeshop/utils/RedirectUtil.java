package com.trandofilidzi.coffeeshop.utils;

import javax.faces.context.FacesContext;
import java.io.IOException;

public class RedirectUtil {

    public static void redirectToHomePage() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void redirectEditCreateOrderPage() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("editCreateOrder.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
