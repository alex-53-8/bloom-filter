package com.alex538.sample;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import static com.alex538.utils.FileUtils.countLines;
import static com.alex538.utils.FileUtils.forEachLine;

final class Utils {
    static Set<String> getRandomWords(File file, int count) throws IOException {
        int linesCount = countLines(file);

        if (linesCount < count) {
            throw new RuntimeException(String.format("A dictionary file %s has no %s words, only %s are available", file.toString(), count, linesCount));
        }

        Set<Integer> randomLines = generateRandomNumberSet(count, countLines(file));
        Set<String> randomWords = new HashSet<>();
        int[] index = {0};
        forEachLine(file, word -> {
            if (randomLines.contains(index[0])) {
                randomWords.add(word);
            }
            index[0]++;
        });

        return randomWords;
    }

    static Set<Integer> generateRandomNumberSet(int number, int range) {
        Set<Integer> randomLines = new HashSet<>();
        Function<Integer, Integer> randomNumberFn = (Integer max) -> (int) (Math.random() * max);

        do {
            randomLines.add(randomNumberFn.apply(range));
        } while(randomLines.size() < number);

        return randomLines;
    }

    static String generateRandomWord(int length) {
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
