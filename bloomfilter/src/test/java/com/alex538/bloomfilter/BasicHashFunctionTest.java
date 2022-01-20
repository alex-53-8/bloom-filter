package com.alex538.bloomfilter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicHashFunctionTest {

    @Test()
    @DisplayName("Hash function must generate the same hash on the same data")
    public void testHash() {
        byte[] data = "toBeHashed".getBytes();
        BasicHashFunction hashFn = new BasicHashFunction();

        long resultFirst = hashFn.hash(data);
        long resultSecond = hashFn.hash(data);

        assertEquals(resultFirst, resultSecond);
    }

}