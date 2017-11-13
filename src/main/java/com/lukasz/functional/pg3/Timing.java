package com.lukasz.functional.pg3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Timing {

    private static Logger log = LoggerFactory.getLogger(Timing.class);

    public static <T> T timed(String description, Supplier<T> code){

//        Consumer<String> defaultOutput =
//                System.out::println;
//
        Consumer<String> defaultOutput =
                (s) -> {};

        return timed(description, code, defaultOutput);
    }

    public static <T> T timed(String description, Supplier<T> code, Consumer<String> output){
        final Date dateBefore = new Date();
        T ret = code.get();
        final Date dateAfter = new Date();
        final Long timeElapsed = dateAfter.getTime() - dateBefore.getTime();
        output.accept(description + ": took " + timeElapsed + " [milliseconds]");
        return ret;
    }
}
