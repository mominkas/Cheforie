package model;


import java.util.ArrayList;
import java.util.Random;

// Represents a list containing multiple Recipes.
public class RecipeList {
    private final ArrayList<Recipe> recipes;

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
    public RecipeList filterRecipeListForTime(String timeOfMeal) {
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

    // MODIFIES: filtered Recipe List
    // EFFECTS: Picks one random recipe from the given list
    public Recipe randomRecipeChooser() throws EmptyRecipeList {
        Random rand = new Random();
        if (recipes.size() == 0) {
            throw new EmptyRecipeList();
        }
        int randomIndex = rand.nextInt(recipes.size());
        return recipes.get(randomIndex);
    }

}

