package com.lukasz.functional.pg4;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class CollectionsExample {

    private static final Predicate<String> NON_EMPTY = new Predicate<String>() {
        @Override
        public boolean apply(String s) {
            return !s.isEmpty();
        }
    };

    public static final Function<String, String> LAST_WORD_TRANSFORMER = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return lastWord(s);
        }
    };
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

//        final Iterable<String> nonEmpties = Iterables.filter(Arrays.asList(food),
//                NON_EMPTY);
//
//        final Iterable<String> lastWords = Iterables.transform(nonEmpties, LAST_WORD_TRANSFORMER);

        FluentIterable<String> lastWords =
                FluentIterable.from(Arrays.asList(food))
                .filter(NON_EMPTY)
                .transform(LAST_WORD_TRANSFORMER);

        return Joiner.on(" & ").join(lastWords);
    }

    private static String lastWord(String s) {
        return s.substring(s.lastIndexOf(" ")+1);
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
