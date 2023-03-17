package com.sda.zajecia1103.recipe.exceptions;

public class NoRecipeFoundException extends RuntimeException{
    public NoRecipeFoundException(Long id) {
        super(String.format("Can't find recipe with id %d", id));
    }

}
