package com.lukasz.functional.pg2;

public class Sales implements QuantityOfInterest {

    private final FunctionOverTime valueFunction;

    public Sales(FunctionOverTime valueFunction) {
        this.valueFunction = valueFunction;
    }

    @Override
    public String getName() {
        return "SALES";
    }

    @Override
    public double valueAt(int time) {
        return valueFunction.valueAt(time);
    }
}
