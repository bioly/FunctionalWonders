package com.lukasz.functional.pg1;

public class FixedCosts extends PolynomialQuantity {

    public FixedCosts(double constant) {
        super(new double[] {constant});
    }

    @Override
    public String getName() {
        return "FIXED COSTS";
    }
}
