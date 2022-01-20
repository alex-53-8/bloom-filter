package com.alex538.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static com.alex538.bloomfilter.BloomFilterOriginalFactory.DEFAULT_ERROR_RATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BloomFilterOriginalTest {

    HashingStrategy strategy;
    ByteArray byteArray;
    final int hashFunctionsNumber = 5;
    final int byteArrayLength = 80;
    BloomFilterOriginal<String> subject;

    @BeforeEach
    public void setup() {
        strategy = mock(HashingStrategy.class);
        byteArray = mock(ByteArray.class);
        subject = new BloomFilterOriginal<>(byteArray, hashFunctionsNumber, strategy);

        when(byteArray.length()).thenReturn(byteArrayLength);
    }
    
    @Test
    public void testAdd_whenAddingToEmptyFilterNewItem_thenFilterSizeIsOne() {
        assertEquals(0, subject.size());

        when(strategy.apply("testItem", hashFunctionsNumber, byteArrayLength)).thenReturn(new int[]{1, 2});

        when(byteArray.set(1, (byte)1)).thenReturn(true);
        when(byteArray.set(2, (byte)1)).thenReturn(true);

        subject.add("testItem");

        assertEquals(1, subject.size());

        verify(byteArray).set(1, (byte)1);
        verify(byteArray).set(2, (byte)1);
    }

    @Test
    public void testAdd_whenAddingToEmptyFilterItemTwice_thenFilterSizeIsNotIncreasedSecondTime_AndFilterSizeIsOne() {
        assertEquals(0, subject.size());

        when(strategy.apply("testItem", hashFunctionsNumber, byteArrayLength)).thenReturn(new int[]{1, 2});

        when(byteArray.set(1, (byte)1)).thenReturn(true);
        when(byteArray.set(2, (byte)1)).thenReturn(true);

        subject.add("testItem");

        when(byteArray.set(1, (byte)1)).thenReturn(false);
        when(byteArray.set(2, (byte)1)).thenReturn(false);

        subject.add("testItem");

        assertEquals(1, subject.size());

        verify(byteArray, times(2)).set(1, (byte)1);
        verify(byteArray, times(2)).set(2, (byte)1);
    }

    @Test
    public void testContains_whenItemsIsInFilter_thenTrueIsReturned() {
        when(strategy.apply("testItem", hashFunctionsNumber, byteArrayLength)).thenReturn(new int[]{1, 2});
        when(byteArray.get(1)).thenReturn((byte)1);
        when(byteArray.get(2)).thenReturn((byte)1);

        assertTrue(subject.contains("testItem"));

        verify(byteArray).get(1);
        verify(byteArray).get(2);
    }

    @Test
    public void testContains_whenItemsIsNotInFilterAndHashIsStoredPartially_thenFalseIsReturned() {
        when(strategy.apply("testItem", hashFunctionsNumber, byteArrayLength)).thenReturn(new int[]{1, 2});
        when(byteArray.get(1)).thenReturn((byte)0);
        when(byteArray.get(2)).thenReturn((byte)0);

        assertFalse(subject.contains("testItem"));

        verify(byteArray).get(1);
        verify(byteArray, never()).get(2);
    }

    @Test
    public void testBitsNumber() {
        when(byteArray.length()).thenReturn(byteArrayLength);

        assertEquals(byteArrayLength, subject.bitsNumber());

        verify(byteArray).length();
    }

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
        }
    }

}
