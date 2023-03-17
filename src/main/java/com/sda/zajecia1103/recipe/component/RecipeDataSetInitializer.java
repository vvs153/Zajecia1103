package com.sda.zajecia1103.recipe.component;

import com.sda.zajecia1103.recipe.model.Complexity;
import com.sda.zajecia1103.recipe.model.Recipe;
import com.sda.zajecia1103.recipe.repository.RecipeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RecipeDataSetInitializer {
private final RecipeRepository recipeRepository;

    public RecipeDataSetInitializer(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    @PostConstruct
    void init(){
        recipeRepository.saveAll(Arrays.asList(
                new Recipe(1L,"Gołąbki", "Gołąbki z kapustą", 120, 4, Complexity.EASY, "Kapusta,Mięso wieprzowe,Ryż"),
                new Recipe(2L,"Pizza", "Pizza domowa", 60, 3, Complexity.STANDARD,"Ser,Sos pomidorowy, szynka" ),
                new Recipe(3L,"Gołąbki 1", "Gołąbki z kapustą", 120, 4, Complexity.EASY, "Kapusta,Mięso wieprzowe,Ryż"),
                new Recipe(4L,"Pizza 1", "Pizza domowa", 60, 3, Complexity.STANDARD,"Ser,Sos pomidorowy, szynka" ),
                new Recipe(5L,"Gołąbki 3", "Gołąbki z kapustą", 120, 4, Complexity.EASY, "Kapusta,Mięso wieprzowe,Ryż"),
                new Recipe(6L,"Pizza 3", "Pizza domowa", 60, 3, Complexity.STANDARD,"Ser,Sos pomidorowy, szynka" ),
                new Recipe(7L,"Gołąbki 2", "Gołąbki z kapustą", 120, 4, Complexity.EASY, "Kapusta,Mięso wieprzowe,Ryż"),
                new Recipe(8L,"Pizza 2", "Pizza domowa", 60, 3, Complexity.STANDARD,"Ser,Sos pomidorowy, szynka" ),
                new Recipe(9L,"Gołąbki 4", "Gołąbki z kapustą", 120, 4, Complexity.EASY, "Kapusta,Mięso wieprzowe,Ryż"),
                new Recipe(10L,"Pizza 4", "Pizza domowa", 60, 3, Complexity.STANDARD,"Ser,Sos pomidorowy, szynka" ),
                new Recipe(11L,"Gołąbki 5", "Gołąbki z kapustą", 120, 4, Complexity.EASY, "Kapusta,Mięso wieprzowe,Ryż"),
                new Recipe(12L,"Pizza 5", "Pizza domowa", 60, 3, Complexity.STANDARD,"Ser,Sos pomidorowy, szynka" )

        ));
    }
}
