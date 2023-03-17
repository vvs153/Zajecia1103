package com.sda.zajecia1103.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByTitleContains(String text);//select*from movie where title like ...
}
