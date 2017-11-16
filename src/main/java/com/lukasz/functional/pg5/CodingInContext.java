package com.lukasz.functional.pg5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CodingInContext {

    private static final Predicate<String> NON_EMPTY = s -> !s.isEmpty();

    private static BinaryOperator<String> toLastOne =
            (allSoFar, nexElement) -> nexElement;

    private static final Function<String, String> LAST_WORD_TRANSFORMER =
            phrase -> Arrays.asList(phrase.split(" ")).stream()
                    .reduce(toLastOne).orElse("");


    private static Logger log = LoggerFactory.getLogger(CodingInContext.class);

    final static String[] food = {
            "Crunchy carrots",
            "Golden-hued bananas",
            "",
            "Bright orange pumpkins",
            "Little trees of broccoli",
            "meat"
    };


    private static String summary(String[] food) {

        System.out.println("--- Setting up the stream");
        Stream<String> lastWords =  Arrays.asList(food).stream()
                .peek(s -> System.out.println("About to filter: " + s))
                .filter(NON_EMPTY)
                .limit(3)
                .peek(s -> System.out.println("About to map: " + s))
                .map(LAST_WORD_TRANSFORMER)
                .peek(s -> System.out.println("About to reduce: " + s));

        System.out.println("--- Reducing");
                return lastWords.reduce(joinOn(" & "))
                .orElse("");
    }

    private static BinaryOperator<String> joinOn(String connector) {
        return (allSoFar, nextElement) ->
                allSoFar + connector + nextElement;
    }

    public static void main(String[] args) {
        log.debug("CodingInContext example...");

        final String summary = summary(food);

        final String desiredSummary =
                "carrots & bananas & pumpkins & broccoli & meat";

        log.debug(desiredSummary);
        if(summary.equals(desiredSummary))
            log.debug("yey!!!");
    }
}
