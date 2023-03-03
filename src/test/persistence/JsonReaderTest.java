package persistence;

import model.Ingredients;
import model.Recipe;
import model.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest {
    RecipeList testRecipeList;
    Recipe testRecipe;


    @BeforeEach
    void setup() {
        testRecipeList = new RecipeList();
        ArrayList<Ingredients> ingredientsList = new ArrayList<>();
        ingredientsList.add(new Ingredients("potato"));
        testRecipe = new Recipe("test", "lunch", ingredientsList, 10, 20, 30, 40);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            testRecipeList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRecipeList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecipeList.json");
        try {
            testRecipeList = reader.read();
            assertEquals(0, testRecipeList.getRecipeList().size());
            assertEquals(0, testRecipeList.getFixedMeals().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneral() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRecipeList.json");
        try {
            testRecipeList = reader.read();
            assertEquals(2, testRecipeList.getRecipeList().size());
            assertEquals(1, testRecipeList.getFixedMeals().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}