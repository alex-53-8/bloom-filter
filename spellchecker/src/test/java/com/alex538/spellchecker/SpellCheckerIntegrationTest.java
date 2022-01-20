package com.alex538.spellchecker;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import static com.alex538.utils.FileUtils.forEachLine;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpellCheckerIntegrationTest {

    @Test
    public void testOnDictionary() throws IOException, URISyntaxException {
        File file = new File(this.getClass().getClassLoader().getResource("dictionary.txt").toURI());
        SpellChecker spellChecker = SpellCheckerFactory.create(file);

        Set<String> dictionary = new HashSet<>();
        forEachLine(file, dictionary::add);

        dictionary.forEach(word -> assertTrue(spellChecker.contains(word)));

        assertFalse(spellChecker.contains("not in the dictionary"));
        assertFalse(spellChecker.contains("drink"));
    }

}
