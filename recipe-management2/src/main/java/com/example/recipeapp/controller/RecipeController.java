package com.example.recipeapp.controller;

import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.getRecipeById(id);
        return recipe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/vegan")
    public List<Recipe> getVeganRecipes() {
        return recipeService.getVeganRecipes();
    }

    @GetMapping("/cheap")
    public List<Recipe> getCheapRecipes() {
        return recipeService.getCheapRecipes();
    }

    @GetMapping("/few-ingredients")
    public List<Recipe> getRecipesWithFewIngredients() {
        return recipeService.getRecipesWithFewIngredients();
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.saveRecipe(recipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipeDetails) {
        Optional<Recipe> recipeOptional = recipeService.getRecipeById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            recipe.setName(recipeDetails.getName());
            recipe.setPrice(recipeDetails.getPrice());
            recipe.setVegan(recipeDetails.isVegan());
            recipe.setNumberOfIngredients(recipeDetails.getNumberOfIngredients());
            return ResponseEntity.ok(recipeService.saveRecipe(recipe));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        Optional<Recipe> recipeOptional = recipeService.getRecipeById(id);
        if (recipeOptional.isPresent()) {
            recipeService.deleteRecipe(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

