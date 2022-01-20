package com.alex538.bloomfilter;

import com.alex538.utils.ObjectSerializer;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BasicHashingStrategyTest {

    String item = "toBeHashed";
    int hashFunctionNumber = 1;
    byte[] serializedBytes = {1};
    int maxValue = 100000;

    @Test
    void apply() {
        /*
        * Calculations:
        * h1 = 1000
        * h2 = 1000 >>> 32 = 0
        * h = h1 + (i * hash2) = 1000
        * returns array with one index that is 1000
        * */
        testApplyMethodWithDifferentParameters(1000, 1000);

        /*
        * Calculations:
        * h1 = (int) 9223372036854774807 = -1001 (overflow happens when casting to integer from long)
        * h2 = 9223372036854774807 >>> 32 = 2147483647
        * h = h1 + (i * hash2) = -1001 + 2147483647 = 2147482646
        * index = h mod 100000 = 82646
        * */
        testApplyMethodWithDifferentParameters(Long.MAX_VALUE - 1000, 82646);
    }

    private void testApplyMethodWithDifferentParameters(long hash, int expectedIndex) {
        BasicHashFunction hashFn = mock(BasicHashFunction.class);
        BasicHashingStrategy subject = new BasicHashingStrategy(hashFn);

        try (MockedStatic<ObjectSerializer> staticMock = mockStatic(ObjectSerializer.class)) {
            staticMock.when(() -> ObjectSerializer.serialize(item)).thenReturn(serializedBytes);
            when(hashFn.hash(serializedBytes)).thenReturn(hash);

            int[] result = subject.apply(item, hashFunctionNumber, maxValue);

            assertEquals(hashFunctionNumber, result.length);
            assertEquals(expectedIndex, result[0]);
        }
    }

}