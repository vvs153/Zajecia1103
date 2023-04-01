package com.sda.zajecia1103.recipe;

import com.sda.zajecia1103.recipe.exceptions.NoRecipeFoundException;
import com.sda.zajecia1103.recipe.exceptions.RecipeAlreadyExistsException;
import com.sda.zajecia1103.recipe.model.Recipe;
import com.sda.zajecia1103.recipe.repository.RecipeRepository;
import com.sda.zajecia1103.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Test
    void given_repo_with_items_when_get_recipe_by_id_then_return_recipe_from_repo(){
        //given
        RecipeRepository recipeRepository = Mockito.mock(RecipeRepository.class);
        RecipeService recipeService = new RecipeService(recipeRepository);
        Recipe expectedResult = new Recipe();
        Mockito.when(recipeRepository.findById(1L)).thenReturn(Optional.of(expectedResult));
        //when
        Recipe result  = recipeService.getRecipeById(1L);
        //then
        assertEquals(expectedResult,result);
    }

    @Test
   void given_repo_with_items_when_get_recipe_by_nonexisting_id_then_return_exception(){
        //given
        RecipeRepository recipeRepository = Mockito.mock(RecipeRepository.class);
        RecipeService recipeService = new RecipeService(recipeRepository);
        Mockito.when(recipeRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(NoRecipeFoundException.class, ()->recipeService.getRecipeById(1L));
      /*  try {
            recipeService.getRecipeById(1L);
        }catch (RuntimeException e) {
            assertTrue(e instanceof NoRecipeFoundException);
        } */

    }
    @Test
    void given_repo_without_recipe_to_delete_when_delete_recipe_by_not_existing_id_then_throw_exception(){
      //given
        RecipeRepository recipeRepository = Mockito.mock(RecipeRepository.class);
        RecipeService recipeService = new RecipeService(recipeRepository);
        Mockito.when(recipeRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(NoRecipeFoundException.class, ()->recipeService.deleteRecipe(1L));
     /*   try{
            recipeService.deleteRecipe(1L);
        } catch (RuntimeException e){
            assertTrue(e instanceof NoRecipeFoundException);
        }*/
    }
    @Test
    void given_repo_with_recipe_to_delete_when_recipe_by_existing_id_then_return_deleted_element(){
        //given
       Recipe expectedResult = Mockito.mock(Recipe.class);
        RecipeRepository recipeRepository = Mockito.mock(RecipeRepository.class);
        RecipeService recipeService = new RecipeService(recipeRepository);
        Mockito.when(recipeRepository.findById(1L)).thenReturn(Optional.of(expectedResult));
        //when
        Recipe result = recipeService.deleteRecipe(1L);
        //then
        assertEquals(expectedResult,result);
    }

    @Test
    void given_repo_with_recipe_to_add_when_recipe_by_existing_name_then_return_added_element()  {
        //given
        Recipe expectedResult = Mockito.mock(Recipe.class);
        Mockito.when(expectedResult.getName()).thenReturn("kek");

        RecipeRepository recipeRepository = Mockito.mock(RecipeRepository.class);
        RecipeService recipeService = new RecipeService(recipeRepository);
        Mockito.when(recipeRepository.findByName("kek")).thenReturn(Optional.of(expectedResult));

        //when
        //then
        assertThrows(RecipeAlreadyExistsException.class, ()->recipeService.addRecipe(expectedResult));

    }
    @Test
    void given_repo_with_non_existing_recipe_to_add_when_recipe_with_unique_name_then_recipe_should_be_added(){
       //given
        Recipe expectedResult = Mockito.mock(Recipe.class);
        Mockito.when(expectedResult.getName()).thenReturn("kek");

        RecipeRepository recipeRepository = Mockito.mock(RecipeRepository.class);
        RecipeService recipeService = new RecipeService(recipeRepository);
        Mockito.when(recipeRepository.findByName("kek")).thenReturn(Optional.empty());
        Mockito.when(recipeRepository.save(expectedResult)).thenReturn(expectedResult);
        //when
        Recipe result = recipeService.addRecipe(expectedResult);
        //then
        assertEquals(expectedResult,result);
    }


}
