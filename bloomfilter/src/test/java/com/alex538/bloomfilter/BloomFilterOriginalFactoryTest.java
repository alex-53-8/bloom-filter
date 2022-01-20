package com.alex538.bloomfilter;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static com.alex538.bloomfilter.BloomFilterOriginalFactory.DEFAULT_ERROR_RATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class BloomFilterOriginalFactoryTest {

    @Test
    public void testCreate_whenOnlyMaxItemsAreSpecified() {
        int maxItems = 10;
        int bitsNumber = 80;
        int hashFunctionsNumber = 5;
        try (MockedStatic<BloomFilterUtils> staticMock = mockStatic(BloomFilterUtils.class)) {
            staticMock.when(() -> BloomFilterUtils.suggestBitsNumber(maxItems, DEFAULT_ERROR_RATE))
                    .thenReturn(bitsNumber);
            staticMock.when(() -> BloomFilterUtils.suggestHashFunctionsNumber(maxItems, bitsNumber))
                    .thenReturn(hashFunctionsNumber);

            BloomFilterOriginal<String> filter = BloomFilterOriginalFactory.create(10);

            assertEquals(bitsNumber, filter.bitsNumber());
        }
    }

}
