package com.alex538.bloomfilter;

import com.alex538.utils.NumberUtils;

final class BloomFilterUtils {

    private BloomFilterUtils() {

    }

    private final static double LN2 = Math.log(2);
    private final static double LN2_SQUARE = Math.pow(LN2, 2);

    /**
     * Calculates minimal size of bits array by a formula
     * m = -n * ln(Perr) / (ln2)^2
     * where
     * Perr - false positive rate
     * n - number of elements to store
     * m - bits array size
     *
     * @param itemsNumber
     * @param falsePositiveRate
     * @return
     */
    static int suggestBitsNumber(int itemsNumber, double falsePositiveRate) {
        return NumberUtils.castToInt(
                -itemsNumber * Math.log(falsePositiveRate) / LN2_SQUARE
        );
    }

    /**
     * Calculates number of hash functions by a formula
     * k = ln2 * m / n
     * where
     * n - number of elements to store
     * m - bits array size
     * k - number of hash functions
     *
     * @param itemsNumber
     * @param bitsNumber
     * @return
     */
    static int suggestHashFunctionsNumber(int itemsNumber, int bitsNumber) {
        return Math.max(1, (int) Math.ceil(LN2 * bitsNumber / itemsNumber));
    }

    /**
     * The method calculates false positive rate for input parameters by a formula
     *
     * Perr = (1 - e^(-kn / m))^k
     * where
     * k - hashFunctionsNumber
     * n - number of elements to store
     * m - bits array size
     *
     * @param itemsNumber
     * @param bitsNumber
     * @param hashFunctionsNumber
     * @return calculated false positive rate for input parameters
     */
    static double falsePositiveRate(int itemsNumber, int bitsNumber, int hashFunctionsNumber) {
        return Math.pow(1 - Math.exp((-1d * itemsNumber * hashFunctionsNumber) / bitsNumber), hashFunctionsNumber);
    }

}
