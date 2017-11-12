package com.lukasz.functional.pg2;

public class FixedCosts implements QuantityOfInterest {

    private final FunctionOverTime valueFunction;

    public FixedCosts(FunctionOverTime valueFunction) {
        this.valueFunction = valueFunction;
    }

    @Override
    public String getName() {
        return "FIXED COSTS";
    }

    @Override
    public double valueAt(int time) {
        return valueFunction.valueAt(time);
    }
}
