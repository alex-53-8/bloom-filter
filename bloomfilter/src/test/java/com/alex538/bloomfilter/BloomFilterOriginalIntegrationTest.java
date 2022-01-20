package com.alex538.bloomfilter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BloomFilterOriginalIntegrationTest {

    @Test
    public void testBloomFilterWithBasicHashingStrategy() {
        BloomFilterOriginal<String> filter = BloomFilterOriginalFactory.create(10);

        assertTrue(filter.add("hello"));
        assertTrue(filter.contains("hello"));
        assertFalse(filter.contains("hellohello"));
        assertFalse(filter.contains("abracadabra"));
    }

}
