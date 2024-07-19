package com.example.recipeapp.repository;

import com.example.recipeapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByIsVegan(boolean isVegan);
    List<Recipe> findByPriceLessThan(Double price);
    List<Recipe> findByNumberOfIngredientsLessThan(int numberOfIngredients);
}
