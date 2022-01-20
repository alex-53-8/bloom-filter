package com.alex538.bloomfilter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ByteArrayTest {

    @Test
    public void testSetAndGet_whenArrayIsPristine() {
        ByteArray subject = new ByteArray(2);

        assertTrue(subject.set(0, (byte)1));
        assertEquals(1, subject.get(0));
        assertEquals(0, subject.get(1));
    }

    @Test
    public void testSet_whenCellIsNotPristine() {
        ByteArray subject = new ByteArray(2);

        assertTrue(subject.set(0, (byte)1));
        assertFalse(subject.set(0, (byte)1));
    }

}
