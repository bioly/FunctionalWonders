package com.lukasz.functional.pg4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionsExample {

    private static final Predicate<String> NON_EMPTY = s -> !s.isEmpty();

    private static BinaryOperator<String> toLastOne =
            (allSoFar, nexElement) -> nexElement;

    private static final Function<String, String> LAST_WORD_TRANSFORMER =
                phrase -> Arrays.asList(phrase.split(" ")).stream()
                    .reduce(toLastOne).orElse("");


    private static Logger log = LoggerFactory.getLogger(CollectionsExample.class);

    final static String[] food = {
            "Crunchy carrots",
            "Golden-hued bananas",
            "",
            "Bright orange pumpkins",
            "Little trees of broccoli",
            "meat"
    };


    private static String summary(String[] food) {

        return Arrays.asList(food).stream()
                .filter(NON_EMPTY)
                .map(LAST_WORD_TRANSFORMER)
                .reduce(joinOn(" & "))
                .orElse("");
    }

    private static BinaryOperator<String> joinOn(String connector) {
        return (allSoFar, nextElement) ->
                allSoFar + connector + nextElement;
    }

    public static void main(String[] args) {
        log.debug("Collection example...");

        final String summary = summary(food);

        final String desiredSummary =
                "carrots & bananas & pumpkins & broccoli & meat";

        log.debug(desiredSummary);
        if(summary.equals(desiredSummary))
            log.debug("yey!!!");
    }
}
