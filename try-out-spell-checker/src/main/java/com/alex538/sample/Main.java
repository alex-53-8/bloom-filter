package com.alex538.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            LOG.info("Specify program arguments: 1 - sample name (dictionary or random), 2- number of words to check, 3 - path to a dictionary");
            return;
        }

        if ("dictionary".equals(args[0])) {
            Dictionary.test(Integer.parseInt(args[1]), new File(args[2]));
        } else if ("random".equals(args[0])) {
            RandomWord.test(Integer.parseInt(args[1]), new File(args[2]));
        } else {
            LOG.info("Sample name provided incorrectly.");
        }
    }

}
