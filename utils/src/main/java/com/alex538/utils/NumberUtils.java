package com.alex538.utils;

public final class NumberUtils {

    private NumberUtils() {

    }

    public static int castToInt(double value) {
        int integerValue = (int) value;

        if (integerValue == (long) value) {
            return integerValue;
        } else {
            throw new IllegalArgumentException(String.format("%s exceed Integer values' range", value));
        }
    }

}
