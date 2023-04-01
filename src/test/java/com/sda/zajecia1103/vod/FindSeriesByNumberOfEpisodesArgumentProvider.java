package com.sda.zajecia1103.vod;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class FindSeriesByNumberOfEpisodesArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            List<Series> seriesList = Arrays.asList(
                    new Series(1, "Test1", "Test1", LocalDate.now(), 10, 1),
                    new Series(2, "Test2", "Test2", LocalDate.now(), 15, 2),
                    new Series(3, "Test3", "Test3", LocalDate.now(), 20, 3),
                    new Series(4, "Test4", "Test4", LocalDate.now(), 25, 4)
            );
            return Stream.of(
                    Arguments.of(Collections.emptyList(), 10, Collections.emptyList()),
                    Arguments.of(seriesList,
                            16,
                            List.of(new Series(3, "Test3", "Test3", LocalDate.now(), 20, 3),
                                    new Series(4, "Test4", "Test4", LocalDate.now(), 25, 4))),
                    Arguments.of(seriesList,
                            30,
                            Collections.emptyList()),
                    Arguments.of(seriesList,
                            25,
                            List.of(new Series(4, "Test4", "Test4", LocalDate.now(), 25, 4))
                    ),
                    Arguments.of(seriesList,
                            5,
                            seriesList
                    ));
        }
    }

