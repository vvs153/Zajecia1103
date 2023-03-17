package com.sda.zajecia1103.recipe.model;

import com.sda.zajecia1103.recipe.NullOrNotBlank;
import com.sda.zajecia1103.recipe.errors.AddRecipe;
import com.sda.zajecia1103.recipe.errors.UpdateRecipe;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(groups = AddRecipe.class)
    @NullOrNotBlank(groups = {AddRecipe.class, UpdateRecipe.class})
    @Size(min = 2, max = 30,groups = {AddRecipe.class, UpdateRecipe.class} )
    private String name;
    @NotNull(groups = AddRecipe.class)
    @NullOrNotBlank(groups = {AddRecipe.class, UpdateRecipe.class})
    @Size(min = 10, max = 100, groups = {AddRecipe.class, UpdateRecipe.class})
    private String description;
    @NotNull(groups = AddRecipe.class)
    @PositiveOrZero(groups = {AddRecipe.class, UpdateRecipe.class})
    private Integer duration;

    @PositiveOrZero(groups = {AddRecipe.class, UpdateRecipe.class})
    @Max(value = 20, message = "za duzo", groups = {AddRecipe.class, UpdateRecipe.class})
    private Integer numberOfPeople;
    @NotNull(groups = AddRecipe.class)
    private Complexity complexity;
    private String ingredients;
    interface AddRecipe {}
    interface UpdateRecipe {}
}

