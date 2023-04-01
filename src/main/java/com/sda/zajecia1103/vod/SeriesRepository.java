package com.sda.zajecia1103.vod;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SeriesRepository extends JpaRepository<Series, Integer> {
    List<Series> findAllByNumberOfEpisodesGreaterThanEqual(int number);
    List<Series> findAllBySeasonNumber(int season);
    List<Series> findAllByReleaseDateAfter(LocalDate localDate);
}
