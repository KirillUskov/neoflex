package ru.kduskov.vacationcalculator.util;

import java.text.DecimalFormat;

public final class NumberUtil {
    public static double roundDoubleTwoDigits(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        return  Double.parseDouble(df.format(d));
    }
}
