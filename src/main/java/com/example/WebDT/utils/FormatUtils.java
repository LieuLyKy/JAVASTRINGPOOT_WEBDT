package com.example.WebDT.utils;

import java.text.DecimalFormat;

public class FormatUtils {

    public static String formatPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(price);
    }
}
