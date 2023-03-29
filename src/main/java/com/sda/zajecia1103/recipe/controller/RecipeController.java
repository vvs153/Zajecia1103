package com.sda.zajecia1103.recipe.controller;



import com.sda.zajecia1103.recipe.errors.AddRecipe;
import com.sda.zajecia1103.recipe.errors.Error;
import com.sda.zajecia1103.recipe.errors.ErrorType;
import com.sda.zajecia1103.recipe.errors.UpdateRecipe;
import com.sda.zajecia1103.recipe.exceptions.NoRecipeFoundException;
import com.sda.zajecia1103.recipe.exceptions.RecipeAlreadyExistsException;
import com.sda.zajecia1103.recipe.model.Complexity;
import com.sda.zajecia1103.recipe.model.Recipe;
import com.sda.zajecia1103.recipe.service.RecipeService;
import com.sda.zajecia1103.recipe.service.SortType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//http://localhost:8080/swagger-ui/
//http://localhost:8080/swagger-ui/index.html
@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Recipe addRecipe(@Validated (AddRecipe.class) @RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Recipe deleteRecipe(@PathVariable Long id) {
        return recipeService.deleteRecipe(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Recipe> getRecipes(@RequestParam(required = false) String ingredient,
                            @RequestParam(required = false) Complexity complexity,
                            @RequestParam(required = false) Integer duration,
                            @RequestParam(required = false) SortType sortType,
                            @RequestParam(required = false) Integer page,
                            @RequestParam(required = false) Integer size
    ) {
        return recipeService.getRecipes(ingredient, complexity, duration, sortType, page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Recipe getRecipeByID(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    Recipe updateRecipe(@PathVariable Long id, @Validated (UpdateRecipe.class)  @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @ExceptionHandler(NoRecipeFoundException.class)
    private ResponseEntity<Error<String>> mapNoSuchElementException(NoRecipeFoundException ex) {
        return new ResponseEntity<>(
                new Error<>(HttpStatus.NOT_FOUND.value(),ex.getMessage() ), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RecipeAlreadyExistsException.class)
    private ResponseEntity<Error<String>> mapRecipeAlreadyExistsException(RecipeAlreadyExistsException ex){
        return new ResponseEntity<>(
                new Error<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<Error<Map<String,String>>> handleMethodArgumentNotValidException (MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
                });
        return new ResponseEntity<>(new Error(
                HttpStatus.BAD_REQUEST.value(),
                errors,
                ErrorType.VALIDATION
        ), HttpStatus.BAD_REQUEST);
    }
}


