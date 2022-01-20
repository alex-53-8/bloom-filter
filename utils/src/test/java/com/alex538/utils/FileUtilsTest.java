package com.alex538.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUtilsTest {

    @Test
    public void testCountLines() throws URISyntaxException, IOException {
        File file = getTestFile();

        int linesCount = FileUtils.countLines(file);

        assertEquals(2, linesCount);
    }

    @Test
    public void testForEachLine() throws URISyntaxException, IOException {
        File file = getTestFile();
        Set<String> words = new HashSet<>();

        FileUtils.forEachLine(file, words::add);

        Set<String> expectedSet = new HashSet<String>() {{
            add("Hello");
            add("world");
        }};
        assertEquals(expectedSet, words);
    }

    private File getTestFile() throws URISyntaxException {
        return new File(this.getClass().getClassLoader().getResource("words.txt").toURI());
    }

}
