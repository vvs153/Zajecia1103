package com.sda.zajecia1103.vod;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class FindSeriesByReleaseDateGreaterThanArgumentProvider implements ArgumentsProvider {
  //daty moga by localdate.parse i w argumencie string z data "
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        List<Series> seriesList = Arrays.asList(
                new Series(1, "Test1", "Test1", LocalDate.of(2010,12,12), 10, 1),
                new Series(2, "Test2", "Test2", LocalDate.of(2012,1,1), 15, 2),
                new Series(3, "Test3", "Test3", LocalDate.of(2015,5,5), 20, 3),
                new Series(4, "Test4", "Test4", LocalDate.of(2017,5,5), 25, 4),
                new Series(5, "Test5", "Test5", LocalDate.of(2019,5,5), 15, 5)
        );
        return Stream.of(
                Arguments.of(Collections.emptyList(), "2001-02-02", Collections.emptyList()),
                Arguments.of(seriesList,
                        LocalDate.of(2013,5,5),
                        List.of(new Series(3, "Test3", "Test3", LocalDate.of(2015,5,5), 20, 3),
                                new Series(4, "Test4", "Test4", LocalDate.of(2017,5,5), 25, 4),
                                new Series(5, "Test5", "Test5", LocalDate.of(2019,5,5), 15, 5))),
                Arguments.of(seriesList,
                        LocalDate.of(2023,4,1),
                        Collections.emptyList()),
                Arguments.of(seriesList,
                        LocalDate.of(2016,11,11),
                        List.of(new Series(4, "Test4", "Test4", LocalDate.of(2017,5,5), 25, 4),
                                new Series(5, "Test5", "Test5", LocalDate.of(2019,5,5), 15, 5))
                ),
                Arguments.of(seriesList,
                        LocalDate.of(2018,12,12),
                        List.of(new Series(5, "Test5", "Test5", LocalDate.of(2019,5,5), 15, 5))
                ),
                Arguments.of(seriesList,
                        LocalDate.of(1999,12,12),
                        seriesList
                ));
    }
}
