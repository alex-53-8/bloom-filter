package com.alex538.spellchecker;

import com.alex538.bloomfilter.BloomFilterOriginal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("ALL")
public class SpellCheckerTest {

    @Test
    public void testAdd() {
        BloomFilterOriginal filter = mock(BloomFilterOriginal.class);
        SpellChecker subject = new SpellChecker(filter);

        when(filter.add("test")).thenReturn(true);

        boolean result = subject.add("test");

        assertTrue(result);
    }

    @Test
    public void testContains() {
        BloomFilterOriginal filter = mock(BloomFilterOriginal.class);
        SpellChecker subject = new SpellChecker(filter);

        when(filter.contains("test")).thenReturn(true);

        boolean result = subject.contains("test");

        assertTrue(result);
    }

    @Test
    public void testSize() {
        BloomFilterOriginal filter = mock(BloomFilterOriginal.class);
        SpellChecker subject = new SpellChecker(filter);

        when(filter.size()).thenReturn(123);

        int result = subject.size();

        assertEquals(123, result);
    }

}
