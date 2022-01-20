package com.alex538.spellchecker;

import com.alex538.bloomfilter.BloomFilterOriginal;
import com.alex538.bloomfilter.BloomFilterOriginalFactory;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SpellCheckerFactoryTest {

    @Test
    public void testCreate_whenWordsAreReadFromFile() throws URISyntaxException, IOException {
        try (MockedStatic<BloomFilterOriginalFactory> staticMock = mockStatic(BloomFilterOriginalFactory.class)) {
            BloomFilterOriginal filter = mock(BloomFilterOriginal.class);
            staticMock.when(() -> BloomFilterOriginalFactory.create(2)).thenReturn(filter);

            SpellChecker subject = SpellCheckerFactory.create(getTestFile());

            assertNotNull(subject);
            assertEquals(filter, subject.filter);
            verify(filter).add("Hello");
            verify(filter).add("world");
        }
    }

    @Test
    public void testCreate_whenJustSizeIsSpecified_thenEmptyBloomFilterIsCreated() throws URISyntaxException, IOException {
        try (MockedStatic<BloomFilterOriginalFactory> staticMock = mockStatic(BloomFilterOriginalFactory.class)) {
            BloomFilterOriginal filter = mock(BloomFilterOriginal.class);
            staticMock.when(() -> BloomFilterOriginalFactory.create(3)).thenReturn(filter);

            SpellChecker subject = SpellCheckerFactory.create(3);

            assertNotNull(subject);
            assertEquals(filter, subject.filter);
            verifyNoInteractions(filter);
        }
    }

    File getTestFile() throws URISyntaxException {
        return new File(this.getClass().getClassLoader().getResource("words.txt").toURI());
    }

}
