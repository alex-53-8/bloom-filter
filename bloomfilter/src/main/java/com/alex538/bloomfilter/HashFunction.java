package com.alex538.bloomfilter;

public interface HashFunction {

    long hash(byte[] bytes);

}
