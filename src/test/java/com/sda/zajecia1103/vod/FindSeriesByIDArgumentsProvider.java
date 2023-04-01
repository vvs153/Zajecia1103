package com.sda.zajecia1103.vod;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FindSeriesByIDArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        List<Series> availableSeries = Arrays.asList(
                new Series(1, "Test1", "Test1", LocalDate.now(), 10, 1),
                new Series(2, "Test2", "Test2", LocalDate.now(), 10, 1)
        );
        return Stream.of(
                Arguments.of(Collections.emptyList(), 10, Optional.empty()),
                Arguments.of(
                        availableSeries,
                        2,
                        Optional.of(new Series(2, "Test2", "Test2", LocalDate.now(), 10, 1))
                ),
                Arguments.of(
                        availableSeries,
                        3,
                        Optional.empty()
                )
        );
    }
}
