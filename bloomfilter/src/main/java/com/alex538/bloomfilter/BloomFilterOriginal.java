package com.alex538.bloomfilter;

import java.io.Serializable;

import static com.alex538.bloomfilter.BloomFilterUtils.falsePositiveRate;

public class BloomFilterOriginal<Item extends Serializable> implements BloomFilter<Item> {

    private int size;
    private final int hashFunctionsNumber;
    private final ByteArray array;
    private final HashingStrategy hashingStrategy;

    BloomFilterOriginal(ByteArray array, int hashFunctionsNumber, HashingStrategy hashingStrategy) {
        this.array = array;
        this.hashFunctionsNumber = hashFunctionsNumber;
        this.hashingStrategy = hashingStrategy;
    }

    public boolean add(Item item) {
        int[] indexes = hashingStrategy.apply(item, hashFunctionsNumber, array.length());
        boolean bitsModified = false;

        for (int index : indexes) {
            bitsModified = array.set(index, (byte) 1) || bitsModified;
        }

        if (bitsModified) {
            size++;
        }

        return bitsModified;
    }

    public boolean contains(Item item) {
        int[] indexes = hashingStrategy.apply(item, hashFunctionsNumber, array.length());

        for (int index : indexes) {
            if (array.get(index) == 0) {
                return false;
            }
        }

        return true;
    }

    public int bitsNumber() {
        return array.length();
    }

    public int size() {
        return size;
    }

    public double rate() {
        return falsePositiveRate(size, array.length(), hashFunctionsNumber);
    }

}
