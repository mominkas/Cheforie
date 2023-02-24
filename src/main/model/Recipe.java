package model;

import java.util.ArrayList;


// Represents a recipe having a name, time of day, list of ingredients, calories, amount of carbs, fat, and protein.
public class Recipe {
    private String name;
    private String timeOfMeal; // breakfast, lunch, dinner, anytime.
    private final ArrayList<Ingredients> ingredient;
    private final int calories;
    private final int carbs;
    private final int fats;
    private final int protein;

    // EFFECTS: Constructs a new recipe
    public Recipe(String name, String timeOfMeal, ArrayList<Ingredients> ingredient, int calories,
                  int carbs, int fats, int protein) {
        this.name = name;
        this.timeOfMeal = timeOfMeal;
        this.ingredient = ingredient;
        this.calories = calories;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;

    }

    public String getName() {
        return name;
    }

    public String getTimeOfMeal() {
        return timeOfMeal;
    }

    public ArrayList<Ingredients> getIngredient() {
        return ingredient;
    }

    public int getCalories() {
        return calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getFats() {
        return fats;
    }

    public int getProtein() {
        return protein;
    }

    public void setRecipeName(String name) {
        this.name = name;
    }

    public void setTimeOfMeal(String timeOfMeal) {
        this.timeOfMeal = timeOfMeal;
    }

    public String toString() {
        return name + " " + timeOfMeal + " Ingredients: "
                + ingredient + " Calories: " + calories + " Carbs: " + carbs + " Fats: " + fats
                + " Protein: " + protein;
    }

}


