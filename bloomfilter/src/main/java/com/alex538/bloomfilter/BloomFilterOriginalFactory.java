package com.alex538.bloomfilter;

import java.io.Serializable;

import static com.alex538.bloomfilter.BloomFilterUtils.suggestBitsNumber;
import static com.alex538.bloomfilter.BloomFilterUtils.suggestHashFunctionsNumber;

public class BloomFilterOriginalFactory {
    public static final double DEFAULT_ERROR_RATE = 0.03d;

    public static <Item extends Serializable> BloomFilterOriginal<Item> create(int expectedItemsNumber) {
        return create(expectedItemsNumber, DEFAULT_ERROR_RATE);
    }

    public static <Item extends Serializable> BloomFilterOriginal<Item> create(int expectedItemsNumber, double errorRate) {
        return create(expectedItemsNumber, errorRate, Strategy.BASIC);
    }

    public static <Item extends Serializable> BloomFilterOriginal<Item> create(int expectedItemsNumber, double errorRate, Strategy strategy) {
        return create(expectedItemsNumber, errorRate, strategy.strategy());
    }

    public static <Item extends Serializable> BloomFilterOriginal<Item> create(int expectedItemsNumber, double errorRate, HashingStrategy strategy) {
        int bitsNumber = suggestBitsNumber(expectedItemsNumber, errorRate);
        int hashFunctionsNumber = suggestHashFunctionsNumber(expectedItemsNumber, bitsNumber);

        return new BloomFilterOriginal<>(new ByteArray(bitsNumber), hashFunctionsNumber, strategy);
    }
}
