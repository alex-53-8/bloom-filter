package com.alex538.bloomfilter;

import java.io.Serializable;

import static com.alex538.utils.ObjectSerializer.serialize;

final class BasicHashingStrategy implements HashingStrategy {

    final BasicHashFunction hashFn;

    BasicHashingStrategy(BasicHashFunction hashFn) {
        this.hashFn = hashFn;
    }

    @Override
    public <Item extends Serializable> int[] apply(Item item, int hashFunctionsNumber, int maxValue) {
        long hash = hashFn.hash(serialize(item));
        int h1 = (int) hash;
        int hash2 = (int) (hash >>> 32);

        int[] indexes = new int[hashFunctionsNumber];

        for (int i = 1; i <= hashFunctionsNumber; i++) {
            int h = h1 + (i * hash2);
            if (h < 0) {
                h = ~h;
            }
            indexes[i - 1] = h % maxValue;
        }

        return indexes;
    }

}
