package com.alex538.bloomfilter;

import java.io.Serializable;

public interface BloomFilter<Item extends Serializable> {

    /**
     * Adds new element to the filter
     */
    boolean add(Item item);

    /**
     * Tests if an item is a part of the set.
     * @return true - if an item is in a part of the set, false - if not
     */
    boolean contains(Item item);

    /**
     * Returns size of an array used storing information about items of the set
     */
    int bitsNumber();

    /**
     * Returns number of items stored in the filter
     */
    int size();

    /**
     * Returns false positive rate calculated value for a filter, based on number of hash functions,
     * number of stored items, and size of array for storing items
     */
    double rate();

}
