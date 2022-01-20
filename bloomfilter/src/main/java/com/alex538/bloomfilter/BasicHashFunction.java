package com.alex538.bloomfilter;

/**
 * Hash function implemented here is based on the idea from
 * "Numerical recipes, the Art of Scientific Computing", chapter "7.6.1 Hash Function Object"
 * */
final class BasicHashFunction implements HashFunction {

    private static final long[] byteTable;
    private static final long HSTART = 0xBB40E64DA205B064L;
    private static final long HMULT =  0x544B2FBACAAF1684L;

    static {
        byteTable = new long[256];
        long h = 0x544B2FBACAAF1684L;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 31; j++) {
                h = (h >>> 7) ^ h;
                h = (h << 11) ^ h;
                h = (h >>> 10) ^ h;
            }
            byteTable[i] = h;
        }
    }

    @Override
    public long hash(byte[] bytes) {
        long hash = HSTART;
        for (byte _byte : bytes) {
            hash = (hash * HMULT) ^ byteTable[_byte & 0xff];
        }
        return hash;
    }

}
