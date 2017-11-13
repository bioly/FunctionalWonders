package com.lukasz.functional.pg3;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class TimingTest {
    @Test
    public void timed() throws Exception {
        final String description = "Testing string";

        AtomicReference<String> output = new AtomicReference<>();

        Timing.timed(description,
                () -> "carrot",
                output::set);

        assert(output.get().contains(description));
    }
}