package com.alex538.spellchecker;

import com.alex538.bloomfilter.BloomFilterOriginal;

public class SpellChecker {

    final BloomFilterOriginal<String> filter;

    SpellChecker(BloomFilterOriginal<String> filter) {
        this.filter = filter;
    }

    public boolean add(String word) {
        return filter.add(word);
    }

    public boolean contains(String word) {
        return filter.contains(word);
    }

    public int size() {
        return filter.size();
    }

}
