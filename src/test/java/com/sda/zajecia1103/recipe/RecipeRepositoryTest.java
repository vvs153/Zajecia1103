package com.sda.zajecia1103.recipe;

import static org.junit.jupiter.api.Assertions.*;

import com.sda.zajecia1103.recipe.model.Complexity;
import com.sda.zajecia1103.recipe.model.Recipe;
import com.sda.zajecia1103.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void given_db_is_empty_when_execute_find_all_then_return_empty_list() {
        //given

        //when
        List<Recipe> results = recipeRepository.findAll();

        //then
        assertTrue(results.isEmpty());
    }
    @Test
    void  given_db_contains_record_when_execute_find_all_then_return_all_records_from_db(){
        //given
        //niepoprawnie -> testujemy metode findall a nie metode saveALl i findAll
       // recipeRepository.saveAll(Arrays.asList(
         //       new Recipe("name","description", 100 ,4,Complexity.EASY, null),
           //     new Recipe("name1","description1", 70 ,2,Complexity.STANDARD,null)
       // ));

        // entity maganger ignoruje validacje
        testEntityManager.persist(
                new Recipe(
                        "name",
                        "description",
                        100, 4,
                        Complexity.STANDARD,
                        null)
        );
        testEntityManager.persist(
                new Recipe("name1",
                        "description1",
                        120,
                        3,
                        Complexity.HARD,
                        null)
        );
        //when
        List<Recipe> results = recipeRepository.findAll();
        //then
        assertEquals(2,results.size());
    }

    @Test
    void  given_db_with_record_with_id_1_when_execute_find_by_id_then_return_record_from_db(){
        //given
       Recipe recipe= testEntityManager.persist(new Recipe(
                "namae",
                "desc",
                50,
                2,
                Complexity.HARD,
                null
        ));
        //when
        Recipe result  =recipeRepository.findById(recipe.getId()).get();
        //then
        assertEquals(recipe,result);
    }
    @Test
    void given_db_without_record_with_provided_id_when_execute_find_by_id_then_return_empty_optionale(){
        //given
        long id =1;
        //when
        Optional<Recipe> recipe = recipeRepository.findById(id);
        //then
        assertTrue(recipe.isEmpty());
    }

    @Test
    void  findallbycomplexity(){
        //given
        Recipe recipe= testEntityManager.persist(
                new Recipe("namae",
                "desc",
                50,
                2,
                Complexity.HARD,
                null
        ));
        Recipe recipe2= testEntityManager.persist(
                new Recipe("nama2e",
                        "desc",
                        120,
                        2,
                        Complexity.HARD,
                        null
                ));
        Recipe recipe3= testEntityManager.persist(
                new Recipe("namae3",
                        "desc",
                        50,
                        2,
                        Complexity.EASY,
                        null
                ));
        //then
        List<Recipe> results = recipeRepository.findAllByComplexity(Complexity.HARD, Pageable.unpaged());
        //then
        assertEquals(2,results.size());
    }
    @Test
    void  findallbycomplexity_negative(){
        //given
        Recipe recipe= testEntityManager.persist(
                new Recipe("namae",
                        "desc",
                        50,
                        2,
                        Complexity.HARD,
                        null
                ));
        Recipe recipe2= testEntityManager.persist(
                new Recipe("nama2e",
                        "desc",
                        120,
                        2,
                        Complexity.HARD,
                        null
                ));
        Recipe recipe3= testEntityManager.persist(
                new Recipe("namae3",
                        "desc",
                        50,
                        2,
                        Complexity.EASY,
                        null
                ));
        //then
        List<Recipe> results = recipeRepository.findAllByComplexity(Complexity.STANDARD, Pageable.unpaged());
        //then
        assertTrue(results.isEmpty());
    }
    @Test
    void  findallbyingredients(){
        //given
        Recipe recipe= testEntityManager.persist(
                new Recipe("namae",
                        "desc",
                        50,
                        2,
                        Complexity.HARD,
                        "carrot"
                ));
        Recipe recipe2= testEntityManager.persist(
                new Recipe("nama2e",
                        "desc",
                        120,
                        2,
                        Complexity.HARD,
                        "meat"
                ));
        Recipe recipe3= testEntityManager.persist(
                new Recipe("namae3",
                        "desc",
                        50,
                        2,
                        Complexity.EASY,
                        "cheese"
                ));
        //then
        List<Recipe> results = recipeRepository.findAllByIngredientsContainsIgnoreCase("cheese",Pageable.unpaged());
        //then
        assertEquals(1,results.size());
        assertEquals("namae3", results.get(0).getName());
    }

    @Test
    void  findallbyingredients_negative(){
        //given
        Recipe recipe= testEntityManager.persist(
                new Recipe("namae",
                        "desc",
                        50,
                        2,
                        Complexity.HARD,
                        "carrot"
                ));
        Recipe recipe2= testEntityManager.persist(
                new Recipe("nama2e",
                        "desc",
                        120,
                        2,
                        Complexity.HARD,
                        "meat"
                ));
        Recipe recipe3= testEntityManager.persist(
                new Recipe("namae3",
                        "desc",
                        50,
                        2,
                        Complexity.EASY,
                        "cheese"
                ));
        //then
        List<Recipe> results = recipeRepository.findAllByIngredientsContainsIgnoreCase("tomato",Pageable.unpaged());
        //then
        assertTrue(results.isEmpty());

    }

    @Test
    void  findallbyduration(){
        //given
        Recipe recipe= testEntityManager.persist(
                new Recipe("namae",
                        "desc",
                        50,
                        2,
                        Complexity.HARD,
                        "carrot"
                ));
        Recipe recipe2= testEntityManager.persist(
                new Recipe("nama2e",
                        "desc",
                        120,
                        2,
                        Complexity.HARD,
                        "meat"
                ));
        Recipe recipe3= testEntityManager.persist(
                new Recipe("namae3",
                        "desc",
                        50,
                        2,
                        Complexity.EASY,
                        "cheese"
                ));
        //then
        List<Recipe> results = recipeRepository.findAllByDuration(50,Pageable.unpaged());
        //then
        assertEquals(2,results.size());
        assertEquals("namae", results.get(0).getName());
        assertEquals("namae3", results.get(1).getName());
    }
    @Test
    void  findallbyduration_negative(){
        //given
        Recipe recipe= testEntityManager.persist(
                new Recipe("namae",
                        "desc",
                        50,
                        2,
                        Complexity.HARD,
                        "carrot"
                ));
        Recipe recipe2= testEntityManager.persist(
                new Recipe("nama2e",
                        "desc",
                        120,
                        2,
                        Complexity.HARD,
                        "meat"
                ));
        Recipe recipe3= testEntityManager.persist(
                new Recipe("namae3",
                        "desc",
                        50,
                        2,
                        Complexity.EASY,
                        "cheese"
                ));
        //then
        List<Recipe> results = recipeRepository.findAllByDuration(90,Pageable.unpaged());
        //then
        assertTrue(results.isEmpty());

    }
}

