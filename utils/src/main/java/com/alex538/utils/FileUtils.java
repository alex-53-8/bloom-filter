package com.alex538.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class FileUtils {

    private FileUtils() {

    }

    public static int countLines(File file) throws IOException {
        try (Stream<String> stream = Files.lines(file.toPath())) {
            return NumberUtils.castToInt(stream.count());
        }
    }

    public static void forEachLine(File file, Consumer<String> consumer) throws IOException {
        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(consumer::accept);
        }
    }

}
