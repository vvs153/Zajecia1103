package com.sda.zajecia1103.vod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;


@Entity
public class Series {

    @Id
    private int id;
    private String name;
    private String author;
    private LocalDate releaseDate;

    private int numberOfEpisodes;
    private int seasonNumber;

    public Series(int id, String name, String author, LocalDate releaseDate, int numberOfEpisodes, int seasonNumber) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.releaseDate = releaseDate;
        this.numberOfEpisodes = numberOfEpisodes;
        this.seasonNumber = seasonNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return id == series.id && numberOfEpisodes == series.numberOfEpisodes && seasonNumber == series.seasonNumber && Objects.equals(name, series.name) && Objects.equals(author, series.author) && Objects.equals(releaseDate, series.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, releaseDate, numberOfEpisodes, seasonNumber);
    }

    public Series() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
}