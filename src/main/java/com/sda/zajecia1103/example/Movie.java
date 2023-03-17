package com.sda.zajecia1103.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
class Movie {

    @Id
    private int id;
    private String title;
    private String director;
    private String type;
    private int yearOfRelease;

    public Movie(int id, String title, String director, String type, int yearOfRelease) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.type = type;
        this.yearOfRelease = yearOfRelease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
}
