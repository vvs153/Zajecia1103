package com.sda.zajecia1103.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer {
    private final MovieRepository movieRepository;

    @Autowired
    public AppInitializer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    void init(){
        movieRepository.save(new Movie(1,"star wars", "G. Lucas","action",1977));
        movieRepository.save(new Movie(1,"batman", "C. Nolan","comics",2005));
    }
}
