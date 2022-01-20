package com.alex538.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberUtilsTest {

    @Test
    public void testCastToInt_whenValue_LowerIntegerMinValue() {
        double value = Integer.MIN_VALUE - 100D;
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.castToInt(value));
    }

    @Test
    public void testCastToInt_whenValue_HigherIntegerMaxValue() {
        double value = Integer.MAX_VALUE + 100D;
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.castToInt(value));
    }

    @Test
    public void testCastToInt_whenValueInIntegerRange() {
        double value = Integer.MAX_VALUE - 100;
        assertEquals(value, NumberUtils.castToInt(value));
    }

    @Test
    public void testCastToInt_whenValueInIntegerRangeAndDouble() {
        double value = 100.123456;
        assertEquals(100, NumberUtils.castToInt(value));
    }

}
