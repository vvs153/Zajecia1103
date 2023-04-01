package com.sda.zajecia1103.vod;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest //stwórz testową bazę danych H2 na potrzeby testowe
class SeriesRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private SeriesRepository seriesRepository;
    //1.0 lementow szukamy : n rezultat brak
    //2. x elementow szukamy n (istinejacy w bazie) rezultat element
    //3. x elementow szukamy n (nieistniejacy w bazie) rezultat brak
    @ParameterizedTest
    @ArgumentsSource(FindSeriesByIDArgumentsProvider.class)
    void given_arg1_elements_repo_when_access_series_by_id_arg2_then_arg3_should_be_returned(
            List<Series> input,
            int arg,
            Optional<Series> expectedResult){
        //given
        input.forEach(item ->{
            testEntityManager.persist(item);
        });
        //when
        Optional<Series> result = seriesRepository.findById(arg);
        //then
        assertEquals(expectedResult,result);
    }


 /*   private static Stream<Arguments> testDataProvider2(){
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
    } */

    @ParameterizedTest
    @ArgumentsSource(FindSeriesByNumberOfEpisodesArgumentProvider.class)
    void given_input_elements_repo_when_access_series_by_number_of_episodes_arg_then_expected_results_should_be_returned(
            List<Series> input,
            int arg,
            List<Series> results
    ){
        //given
        input.forEach(item ->{
            testEntityManager.persist(item);
        });
        //when
        List<Series> result = seriesRepository.findAllByNumberOfEpisodesGreaterThanEqual(arg);
        //then
        assertEquals(results,result);
    }

    @ParameterizedTest
    @ArgumentsSource(FindSeriesBySeasonNumberArgumentProvider.class)
    void given_input_elements_repo_when_access_series_by_season_number_arg_then_expected_results_should_be_returned(
            List<Series> input,
            int season,
            List<Series> results
    ){
        //given
        input.forEach(item ->{
            testEntityManager.persist(item);
        });
        //when
        List<Series> result = seriesRepository.findAllBySeasonNumber(season);
        //then
        assertEquals(results,result);
    }

    @ParameterizedTest
    @ArgumentsSource(FindSeriesByReleaseDateGreaterThanArgumentProvider.class)
    void given_input_elements_repo_when_access_series_by_release_date_greater_than_arg_then_expected_results_should_be_returned(
            List<Series> input,
            LocalDate releaseDate,
            List<Series> results
    ){
        //given
        input.forEach(item ->{
            testEntityManager.persist(item);
        });
        //when
        List<Series> result = seriesRepository.findAllByReleaseDateAfter(releaseDate);
        //then
        assertEquals(results,result);
    }

    @Test
    void given_empty_series_repo_when_access_series_by_id_then_no_item_should_be_returned() {
        //given

        //when
        Optional<Series> result = seriesRepository.findById(1);

        //then
        assertFalse(result.isPresent());
    }

    @Test
    void given_series_repo_with_records_when_access_series_by_id_then_item_with_provided_id_should_be_returned() {
        //given
        testEntityManager.persist(new Series(1, "Test1", "Test1", LocalDate.now(), 10, 1));
        testEntityManager.persist(new Series(2, "Test2", "Test2", LocalDate.now(), 20, 2));

        //when
        Optional<Series> result = seriesRepository.findById(1);

        //then
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals("Test1", result.get().getName());
    }

    @Test
    void given_series_repo_with_records_return_records_with_episodes_greater_than(){
        //given
        testEntityManager.persist(new Series(1, "Test1", "Test1", LocalDate.now(), 10, 1));
        testEntityManager.persist(new Series(2, "Test2", "Test2", LocalDate.now(), 20, 2));
        testEntityManager.persist(new Series(3, "Test3", "Test3", LocalDate.now(), 15, 3));
        //when
        List<Series> seriesList =seriesRepository.findAllByNumberOfEpisodesGreaterThanEqual(15);
        //then
        assertEquals(2,seriesList.size());
        assertEquals("Test2", seriesList.get(0).getName());
        assertEquals("Test3", seriesList.get(1).getName());
    }
    @Test
    void given_series_repo_with_records_return_records_with_episodes_greater_than_no_results(){
        //given

        //when
        List<Series> seriesList =seriesRepository.findAllByNumberOfEpisodesGreaterThanEqual(30);
        //then
        assertTrue(seriesList.isEmpty());
    }
}
