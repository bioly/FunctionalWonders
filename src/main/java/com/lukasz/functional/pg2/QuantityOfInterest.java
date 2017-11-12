package com.lukasz.functional.pg2;

public interface QuantityOfInterest {

    String getName();

    /**
     * expected value for particular month
     * @param time month, 1-12
     * @return expected value
     */
    double valueAt(final int time);
}
