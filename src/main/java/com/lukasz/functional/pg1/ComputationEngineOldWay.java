package com.lukasz.functional.pg1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputationEngineOldWay {

    private static Logger log = LoggerFactory.getLogger(ComputationEngineOldWay.class);

    final static double[] EXPECTED_SALES_JAN_TO_DEC =
            new double[]{
                42.0, 45.5, 55.5, 55.5, 44.4, 43.0,
                42.0, 45.5, 55.5, 55.5, 66.4, 55.0
            };

    public static void main(String[] args) {

        log.debug("ComputationEngineOldWay let's begin...");

        final Sales sales =
                new Sales(EXPECTED_SALES_JAN_TO_DEC);

        final FixedCosts fixedCosts =
                new FixedCosts(15.0);
        final IncrementalCosts incrementalCosts =
                new IncrementalCosts(5.1, 0.15);


        final Profit profit = new Profit(
                sales,
                incrementalCosts,
                fixedCosts
        );

        double total = 0.0;
        for(int i = 1; i <= 12; i++){
            total += profit.valueAt(i);
        }

        log.debug("Total profit for the year: {} USD" ,total);
    }
}
