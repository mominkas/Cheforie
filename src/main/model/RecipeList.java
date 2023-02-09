package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Represents a list containing multiple Recipes.
public class RecipeList {
    private ArrayList<Recipe> recipes;

    // EFFECTS: Constructs a new recipe list.
    public RecipeList() {
        recipes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds a recipe to the recipe list.
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public ArrayList<Recipe> getRecipeList() {
        return recipes;
    }

    // REQUIRES: non-empty list
    // EFFECTS:generates random recipe corresponding to time of day provided
    public RecipeList randomRecipeForGivenTime(String timeOfMeal) {
        RecipeList filteredRecipes = new RecipeList();
        if (timeOfMeal.equals("breakfast")) {
            for (Recipe r : recipes) {
                if (r.getTimeOfMeal().equals("breakfast")) {
                    filteredRecipes.getRecipeList().add(r);
                }
            }

        } else if (timeOfMeal.equals("lunch")) {
            for (Recipe r : recipes) {
                if (r.getTimeOfMeal().equals("lunch")) {
                    filteredRecipes.getRecipeList().add(r);
                }
            }

        } else if (timeOfMeal.equals("dinner")) {
            for (Recipe r : recipes) {
                if (r.getTimeOfMeal().equals("dinner")) {
                    filteredRecipes.getRecipeList().add(r);
                }
            }
        }
        return filteredRecipes;
    }

    // REQUIRES: Non-empty list
    // MODIFIES: filtered Recipe List
    // EFFECTS: Picks one random recipe from the given list
    public Recipe randomRecipeChooser() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(recipes.size());
        return recipes.get(randomIndex);

    }

}

