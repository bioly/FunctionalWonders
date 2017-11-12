package com.lukasz.functional.pg1;

abstract class  MonthByMonthInterest implements QuantityOfInterest {

    private final double[] values;

    public MonthByMonthInterest(final double[] values) {
        this.values = values;
    }

    @Override
    public double valueAt(int time) {
        return values[time - 1];
    }
}
