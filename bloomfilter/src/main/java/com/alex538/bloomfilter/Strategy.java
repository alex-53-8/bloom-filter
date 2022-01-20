package com.alex538.bloomfilter;

/**
 * Out of the box strategies for hashing objects and calculating indexes
 * */
public enum Strategy {

    BASIC(new BasicHashingStrategy(new BasicHashFunction()));

    private HashingStrategy strategy;

    Strategy(HashingStrategy strategy) {
        this.strategy = strategy;
    }

    HashingStrategy strategy() {
        return strategy;
    }

}
