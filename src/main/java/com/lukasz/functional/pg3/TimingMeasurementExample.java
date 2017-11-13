package com.lukasz.functional.pg3;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static com.lukasz.functional.pg3.Timing.timed;

public class TimingMeasurementExample {

    private static Logger log = LoggerFactory.getLogger(TimingMeasurementExample.class);

    public static void main(String[] args) {
        log.debug("Hello from TimingMeasurementExample where we are going to measure a time of execution...");

        final Double costs =
                timed("Costs", TimingMeasurementExample::calculateCosts);

        final Double revenue =
                timed("Revenue", TimingMeasurementExample::calculateRevenue);

//        final Double profit =
//                timed("Profit", ()->calculateProfit(costs, revenue),
//                        System.out::println);
//
//        final Double profit =
//                timed("Profit", ()->calculateProfit(costs, revenue));
//
        final Double profit =
                timed("Profit", ()->calculateProfit(costs, revenue), log::debug);

        log.debug("Calculated profit is: {} ", profit +" $USD");
    }

    private static Double calculateProfit(Double costs, Double revenue) {
        pretendToWorkHard();
        return revenue - costs;
    }

    private static Double calculateRevenue() {
        pretendToWorkHard();
        return 9999.9;
    }

    private static Double calculateCosts() {
        pretendToWorkHard();
        return 4555.5;
    }

    private static void pretendToWorkHard() {
        Random random = new Random();
        int sleepTime = random.nextInt(2000);
        try{
            Thread.sleep(sleepTime);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
