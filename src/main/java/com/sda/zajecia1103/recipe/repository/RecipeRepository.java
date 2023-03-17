package com.sda.zajecia1103.recipe.repository;


import com.sda.zajecia1103.recipe.model.Complexity;
import com.sda.zajecia1103.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByDuration(Integer duration, Pageable pageable);
    List<Recipe> findAllByComplexity(Complexity complexity, Pageable pageable) ;

    List<Recipe> findAllByIngredientsContainsIgnoreCase(String ingredients, Pageable pageable);

    Optional<Recipe> findByName(String name); //select * from recipe where name = 'name'
}
