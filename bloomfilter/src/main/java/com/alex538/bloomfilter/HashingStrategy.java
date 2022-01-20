package com.alex538.bloomfilter;

import java.io.Serializable;

public interface HashingStrategy {

    <Item extends Serializable> int[] apply(Item item, int hashFunctionsNumber, int maxValue);

}
