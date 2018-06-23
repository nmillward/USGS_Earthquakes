package com.nicholasmillward.usgsearthquakes.utils;

import java.text.DecimalFormat;

/**
 * Created by nmillward on 6/23/18.
 */

public class DecimalUtils {

    public static String doubleToString(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(number);
    }

}
