package com.alex538.bloomfilter;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BloomFilterUtilsTest {

    @Test
    public void falsePositiveRate() {
        double expectedFalsePositiveRate = 0.04893d; //  calculated manually with usage of a formula and a calculator

        int elementsNumber = 8;
        int bitsNumber = 64;
        int hashFunctionsNumber = 2;

        double falsePositiveRate = BloomFilterUtils.falsePositiveRate(elementsNumber, bitsNumber, hashFunctionsNumber);

        double result = BigDecimal.valueOf(falsePositiveRate).setScale(5, RoundingMode.HALF_UP)
                .doubleValue();
        assertEquals(expectedFalsePositiveRate, result);
    }

    @Test
    public void testSuggestHashFunctionsNumber() {
        int expectedHashFunctionsNumber = 6; //  calculated manually with usage of a formula and a calculator

        int elementsNumber = 8;
        int bitsNumber = 64;

        int result = BloomFilterUtils.suggestHashFunctionsNumber(elementsNumber, bitsNumber);
        assertEquals(expectedHashFunctionsNumber, result);
    }

    @Test
    public void testSuggestBitsNumber() {
        int expectedBitsArrayLength = 50; //  calculated manually with usage of a formula and a calculator

        int elementsNumber = 8;
        double falsePositiveRate = 0.04893d;

        assertEquals(expectedBitsArrayLength, BloomFilterUtils.suggestBitsNumber(elementsNumber, falsePositiveRate));
    }

}
