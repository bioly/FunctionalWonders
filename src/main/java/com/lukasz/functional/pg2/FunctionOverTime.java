package com.lukasz.functional.pg2;

@FunctionalInterface
public interface FunctionOverTime {
    double valueAt(int time);

    static FunctionOverTime monthByMonth(final double[] values){
        return time -> values[time - 1];
    }

    static FunctionOverTime constant(final double value){
        return polynomial(new double[]{value});
    }
    static FunctionOverTime line(final double intercept, final double slope){
        return polynomial(new double[]{intercept, slope});
    }

    static FunctionOverTime polynomial(final double[] coefficents){
        return time -> {
            Double sum = 0.0;
            for (int i = 0; i < coefficents.length; i++){
                sum += Math.pow(time, i) * coefficents[i];
            }
            return sum;
        };
    }

    @FunctionalInterface
    interface FunctionOf3{
        double apply(double a, double b, double c);
    }

    static FunctionOverTime combinationOf3(final FunctionOverTime a,
                                           final FunctionOverTime b,
                                           final FunctionOverTime c,
                                           final FunctionOf3 f){
        return time -> f.apply( a.valueAt(time),
                                b.valueAt(time),
                                c.valueAt(time));
    }
}
