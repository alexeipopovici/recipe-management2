package com.example.recipeapp.service;

import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private static final double CHEAP_PRICE_THRESHOLD = 10.0;
    private static final int FEW_INGREDIENTS_THRESHOLD = 5;

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public List<Recipe> getVeganRecipes() {
        return recipeRepository.findByIsVegan(true);
    }

    public List<Recipe> getCheapRecipes() {
        return recipeRepository.findByPriceLessThan(CHEAP_PRICE_THRESHOLD);
    }

    public List<Recipe> getRecipesWithFewIngredients() {
        return recipeRepository.findByNumberOfIngredientsLessThan(FEW_INGREDIENTS_THRESHOLD);
    }
}


