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

class JsonWriterTest {
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
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/nonexis\0ten:tfile.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testWriterEmptyRecipeList() {
        try {

            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRecipeList.json");
            writer.open();
            writer.write(testRecipeList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRecipeList.json");
            testRecipeList = reader.read();
            assertEquals(0, testRecipeList.getRecipeList().size());
            assertEquals(0, testRecipeList.getFixedMeals().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            testRecipeList.addRecipe(testRecipe);
            testRecipeList.addRecipe(testRecipe);
            testRecipeList.addRecipeToFixedMeal(testRecipe);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRecipeList.json");
            writer.open();
            writer.write(testRecipeList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRecipeList.json");
            testRecipeList = reader.read();
            assertEquals(2, testRecipeList.getRecipeList().size());
            assertEquals(1, testRecipeList.getFixedMeals().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

