package persistence;

import model.Ingredients;
import model.Recipe;
import model.RecipeList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RecipeList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private RecipeList parseRecipeList(JSONObject jsonObject) {
        RecipeList parentRecipeList = new RecipeList();
        addRecipeList(parentRecipeList, jsonObject);
        addFixedMealList(parentRecipeList, jsonObject);

        return parentRecipeList;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addRecipeList(RecipeList parentRecipeList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("RecipeList");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(parentRecipeList, nextRecipe, "RecipeList");
        }
    }

    // MODIFIES: RecipeList
    // EFFECTS: parses recipes from JSON object and adds them to RecipeList
    private void addFixedMealList(RecipeList parentRecipeList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("FixedMeaList");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(parentRecipeList, nextRecipe, "FixedMeaList");
        }
    }

    // MODIFIES: RecipeList
    // EFFECTS: parses recipe from JSON object and adds it to RecipeList
    private void addRecipe(RecipeList parentRecipeList, JSONObject jsonObject, String whichList) {
        String name = jsonObject.getString("name");
        String timeOfMeal = jsonObject.getString("timeOfMeal");
        ArrayList<Ingredients> ingredient = getIngredients(jsonObject);
        int calories = jsonObject.getInt("calories");
        int carbs = jsonObject.getInt("carbs");
        int fats = jsonObject.getInt("fats");
        int protein = jsonObject.getInt("protein");
        Recipe recipe = new Recipe(name, timeOfMeal, ingredient, calories, carbs, fats, protein);
        if (Objects.equals(whichList, "RecipeList")) {
            parentRecipeList.addRecipe(recipe);
        } else {
            parentRecipeList.addRecipeToFixedMeal(recipe);
        }
    }

    // MODIFIES: Recipe
    // EFFECTS: parses Ingredients from JSON object and returns ingredient list.
    private ArrayList<Ingredients> getIngredients(JSONObject jsonObject) {
        ArrayList<Ingredients> ingredient = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("ingredient");
        for (Object json : jsonArray) {
            JSONObject nextIngredient = (JSONObject) json;
            addNextIngredient(ingredient, nextIngredient);
        }
        return ingredient;
    }

    // MODIFIES: Recipe
    // EFFECTS: parses ingredient from JSON object and adds it to ingredient list.
    private void addNextIngredient(ArrayList<Ingredients> ingredient, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Ingredients ingredients = new Ingredients(name);
        ingredient.add(ingredients);

    }
}
