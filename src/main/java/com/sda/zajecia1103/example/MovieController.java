package com.sda.zajecia1103.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/movies")
@RestController
public class MovieController {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    List<Movie> getMovies(@RequestParam(required = false) String title) {
        if (title == null) {
            return movieRepository.findAll();
        }else {
            return movieRepository.findAllByTitleContains(title);
        }
    }

    @GetMapping("/title")
    List<Movie> getMoviesByTitle(@RequestParam String title) {
        return movieRepository.findAllByTitleContains(title);
    }

    @PostMapping
    Movie addMovies(@RequestBody Movie movie) {
        movieRepository.save(movie);
        return movie;
    }

    @DeleteMapping("/{id}")
    Movie deleteMovie(@PathVariable Long id) {
        Movie movieToDelete = movieRepository.findById(id)
                .orElseThrow();
        movieRepository.delete(movieToDelete);
        return movieToDelete;
    }

}
