package com.lukasz.functional.pg2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputationEngineUsingLambdas {

    private static Logger log = LoggerFactory.getLogger(ComputationEngineUsingLambdas.class);

    final static double[] EXPECTED_SALES_JAN_TO_DEC =
            new double[]{
                    42.0, 45.5, 55.5, 55.5, 44.4, 43.0,
                    42.0, 45.5, 55.5, 55.5, 66.4, 55.0
            };

    public static void main(String[] args) {

        log.debug("ComputationEngineOldWay let's begin...");

        final Sales sales =
                new Sales(FunctionOverTime.monthByMonth(EXPECTED_SALES_JAN_TO_DEC));

        final FixedCosts fixedCosts =
                new FixedCosts(FunctionOverTime.constant(15.0));

        final IncrementalCosts incrementalCosts =
                new IncrementalCosts(FunctionOverTime.line(0.15, 3));


        final Profit profit =
                new Profit(sales, incrementalCosts, fixedCosts);

        double total = 0.0;
        for(int i = 1; i <= 12; i++){
            total += profit.valueAt(i);
        }

        log.debug("Total profit for the year: {} USD" , total);
    }
}