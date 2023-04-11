package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Random;

// Represents a list containing multiple Recipes and a fixedMealList
public class RecipeList implements Writable {
    private final ArrayList<Recipe> recipes;
    private final ArrayList<Recipe> fixedMeals;

    // EFFECTS: Constructs a new recipe list.
    public RecipeList() {
        recipes = new ArrayList<>();
        fixedMeals = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds a recipe to the recipe list.
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        EventLog.getInstance().logEvent(new Event("Added " + recipe.getName() + " to your recipe list!"));
    }

    // MODIFIES: this
    // EFFECTS: Adds a recipe to fixed meal list.
    public void addRecipeToFixedMeal(Recipe recipe) {
        fixedMeals.add(recipe);
        EventLog.getInstance().logEvent(new Event("Added " + recipe.getName() + " to your fixed meals list!"));
    }

    public ArrayList<Recipe> getRecipeList() {
        return recipes;
    }

    public ArrayList<Recipe> getFixedMeals() {
        return fixedMeals;
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
        EventLog.getInstance().logEvent(new Event("Filtered for " + timeOfMeal + " recipes!"));
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

    public void printRecipeEventLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
            System.out.println();
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("RecipeList", recipesToJson());
        json.put("FixedMeaList", fixedMealsToJson());
        return json;
    }

    // EFFECTS: returns recipes in this RecipeList as a JSON array
    private JSONArray recipesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe recipe : recipes) {
            jsonArray.put(recipe.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns fixedMeals in this RecipeList as a JSON array
    private JSONArray fixedMealsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe recipe : fixedMeals) {
            jsonArray.put(recipe.toJson());
        }

        return jsonArray;
    }
}

