package com.alex538.bloomfilter;

public class ByteArray {

    private final byte[] array;

    public ByteArray(int size) {
        array = new byte[size];
    }

    public boolean set(int index, byte value) {
        if (array[index] == value) {
            return false;
        } else {
            array[index] = value;
            return true;
        }
    }

    public byte get(int index) {
        return array[index];
    }

    public int length() {
        return array.length;
    }

}
