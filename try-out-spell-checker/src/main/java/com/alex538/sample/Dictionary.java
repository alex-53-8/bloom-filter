package com.alex538.sample;

import com.alex538.spellchecker.SpellChecker;
import com.alex538.spellchecker.SpellCheckerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static com.alex538.sample.Utils.getRandomWords;
import static java.util.stream.Collectors.toSet;

public class Dictionary {

    private static final Logger LOG = LoggerFactory.getLogger(Dictionary.class);

    public static void test(int randomWordsFromDictionaryNumber, File dictionaryFile) throws IOException {
        LOG.info("Number of random words: {}", randomWordsFromDictionaryNumber);
        LOG.info("Dictionary file: {}", dictionaryFile.toString());

        SpellChecker spellChecker = SpellCheckerFactory.create(dictionaryFile);
        Set<String> randomWords = getRandomWords(dictionaryFile, randomWordsFromDictionaryNumber);

        if (randomWords.isEmpty()){
            LOG.info("Random words are not specified. Check dictionary location");
            return;
        } else {
            LOG.info("Collected {} random words from the dictionary", randomWords.size());
        }

        if( spellChecker.size() <= 0) {
            LOG.info("Spell checker has words");
            return;
        } else {
            LOG.info("Spell checker contains {} words", spellChecker.size());
        }

        Set<String> wordsNotInSet = randomWords.stream().filter(word -> !spellChecker.contains(word)).collect(toSet());

        if(!wordsNotInSet.isEmpty()) {
            LOG.info("Following words were not found in the spell checker: " + String.join(", ", wordsNotInSet));
        } else {
            LOG.info("All randomly picked words from the dictionary were found in the spell checker");
        }
    }

}
