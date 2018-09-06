package com.trandofilidzi.coffeeshop.utils;

import java.util.regex.Pattern;

public class MatcherUtil {

    public static boolean quantityValidate(String quantity) {
        if (quantity != null && !quantity.equals("")) {
            return Pattern.compile("[0-9]*").matcher(quantity).matches();
        } else {
            return false;
        }
    }
}
