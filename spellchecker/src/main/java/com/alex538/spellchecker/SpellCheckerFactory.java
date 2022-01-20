package com.alex538.spellchecker;

import com.alex538.bloomfilter.BloomFilterOriginal;
import com.alex538.bloomfilter.BloomFilterOriginalFactory;

import java.io.File;
import java.io.IOException;

import static com.alex538.utils.FileUtils.countLines;
import static com.alex538.utils.FileUtils.forEachLine;

public class SpellCheckerFactory {

    public static SpellChecker create(int wordsCount) {
        return new SpellChecker(BloomFilterOriginalFactory.create(wordsCount));
    }

    public static SpellChecker create(File dictionary) throws IOException {
        BloomFilterOriginal<String> filter = BloomFilterOriginalFactory.create(countLines(dictionary));
        forEachLine(dictionary, filter::add);
        return new SpellChecker(filter);
    }

}
