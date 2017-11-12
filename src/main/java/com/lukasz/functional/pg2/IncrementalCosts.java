package com.lukasz.functional.pg2;

public class IncrementalCosts implements QuantityOfInterest {

    private final FunctionOverTime valueFunction;

    public IncrementalCosts(FunctionOverTime f) {
        valueFunction = f;
    }

    @Override
    public String getName() {
        return "INCREMENTAL COSTS";
    }

    @Override
    public double valueAt(int time) {
        return valueFunction.valueAt(time);
    }
}
