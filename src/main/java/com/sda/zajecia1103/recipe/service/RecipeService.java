package com.sda.zajecia1103.recipe.service;

import com.sda.zajecia1103.recipe.exceptions.NoRecipeFoundException;
import com.sda.zajecia1103.recipe.exceptions.RecipeAlreadyExistsException;
import com.sda.zajecia1103.recipe.model.Complexity;
import com.sda.zajecia1103.recipe.model.Recipe;
import com.sda.zajecia1103.recipe.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public
class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRecipes(
            String ingredients,
            Complexity complexity,
            Integer duration,
            SortType sortType,
            Integer page,
            Integer size) {
        Pageable pageable = providePageable(page, size, sortType);
        if (ingredients != null) {
            return recipeRepository.findAllByIngredientsContainsIgnoreCase(ingredients, pageable);
        } else if(complexity != null) {
            return recipeRepository.findAllByComplexity(complexity, pageable);
        } else if(duration != null) {
            return recipeRepository.findAllByDuration(duration, pageable);
        }
        return recipeRepository.findAll(pageable).toList();

//        if (sortType != null && page != null && size != null) {
//            Sort.Direction direction = SortType.DESC == sortType ? Sort.Direction.DESC : Sort.Direction.ASC;
//            Sort sort = Sort.by(direction, "name");
//            Pageable pageable = PageRequest.of(page,size, sort);
//            return recipeRepository.findAll(pageable).toList();
//        } else if(page != null && size != null) {
//            Pageable pageable = PageRequest.of(page,size);
//            return recipeRepository.findAll(pageable).toList();
//        } else if(sortType != null) {
//            Sort.Direction direction = SortType.DESC == sortType ? Sort.Direction.DESC : Sort.Direction.ASC;
//            Sort sort = Sort.by(direction, "name");
//            return recipeRepository.findAll(sort);
//        } else {
//            return recipeRepository.findAll();
//        }
        //return recipeRepository.findAll(pageable).toList();
    }

    public Recipe addRecipe(Recipe recipe){
        String recipeName = recipe.getName();
        checkIfRecipeNameIsUnique(recipeName);
        return recipeRepository.save(recipe);
    }

    public Recipe deleteRecipe(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow(()->new NoRecipeFoundException(id));
        recipeRepository.delete(recipe);
        return recipe;
    }

    public Recipe getRecipeById(Long id){
        return recipeRepository.findById(id).orElseThrow(()->new NoRecipeFoundException(id));
    }

    public Recipe updateRecipe(Long id,Recipe recipe){
        Recipe recipeToUpdate = recipeRepository.findById(id).orElseThrow(()->new NoRecipeFoundException(id));
        String recipeName = recipe.getName();
        if(recipeName != null && !recipeName.equals(recipeToUpdate.getName())) {
            checkIfRecipeNameIsUnique(recipeName);
            recipeToUpdate.setName(recipe.getName());
        }
        String recipeDescription = recipe.getDescription();
        if(recipeDescription != null && !recipeDescription.equals(recipeToUpdate.getDescription()) ){
            recipeToUpdate.setDescription(recipe.getDescription());
        }
        Integer recipeDuration = recipe.getDuration();
        if(recipeDuration != null && !recipeDuration.equals(recipeToUpdate.getDuration())){
            recipeToUpdate.setDuration(recipe.getDuration());
        }
        Integer recipeNumberOfPeople = recipe.getNumberOfPeople();
        if(recipeNumberOfPeople != null && !recipeNumberOfPeople.equals(recipeToUpdate.getNumberOfPeople())){
            recipeToUpdate.setNumberOfPeople(recipe.getNumberOfPeople());
        }
        Complexity recipeComplexity = recipe.getComplexity();
        if(recipeComplexity != null && !recipeComplexity.equals(recipeToUpdate.getComplexity())){
            recipeToUpdate.setComplexity(recipe.getComplexity());
        }
        String recipeIngredients = recipe.getIngredients();
        if(recipeIngredients != null && !recipeIngredients.equals(recipeToUpdate.getIngredients())){
            recipeToUpdate.setIngredients(recipe.getIngredients());
        }
        return recipeRepository.save(recipeToUpdate);

    }

    Pageable providePageable(Integer page, Integer size, SortType sortType) {
        //4 warianty do wsparcia:
        //- paginacja
        //- sortowanie
        //- paginacja + sortowanie
        //- brak paginacji i sortowania
        Sort.Direction direction = SortType.DESC == sortType ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, "name");
        return PageRequest.of(
                page != null && size != null ? page : 0,
                page != null && size != null ? size : (int) recipeRepository.count(), sort);
    }
 private void  checkIfRecipeNameIsUnique(String recipeName){
        recipeRepository.findByName(recipeName)
                .ifPresent(r ->{throw new RecipeAlreadyExistsException(recipeName);
        });

 }
}
