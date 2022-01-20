package com.alex538.sample;

import com.alex538.spellchecker.SpellChecker;
import com.alex538.spellchecker.SpellCheckerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.alex538.sample.Utils.generateRandomWord;
import static com.alex538.utils.FileUtils.forEachLine;

public class RandomWord {

    private static final Logger LOG = LoggerFactory.getLogger(RandomWord.class);

    public static void test(int rounds, File dictionaryFile) throws IOException {
        LOG.info("Number of random words: {}", rounds);
        LOG.info("Dictionary file: {}", dictionaryFile.toString());

        SpellChecker spellChecker = SpellCheckerFactory.create(dictionaryFile);

        int foundRandomWords = 0;
        Set<String> dictionary = new HashSet<>();
        forEachLine(dictionaryFile, dictionary::add);

        for(int i = 0; i < rounds; i++) {
            String word = generateRandomWord(5);
            if (spellChecker.contains(word) && !dictionary.contains(word)) {
                foundRandomWords++;
            }
        }

        LOG.info("Words were not found in dictionary, but were found in the spellchecker: {}", foundRandomWords);
        LOG.info("Default false positive error rate is 0.03, calculated after the test run is: {}", 1d*foundRandomWords / rounds);
    }

}
