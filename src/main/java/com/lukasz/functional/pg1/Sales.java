package com.lukasz.functional.pg1;

public class Sales extends MonthByMonthInterest {

    public Sales(final double[] values) {
        super(values);
    }

    @Override
    public String getName() {
        return "Sales !!!";
    }
}
