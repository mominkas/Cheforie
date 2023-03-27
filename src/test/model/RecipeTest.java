package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class RecipeTest {
    Recipe testRecipe;



    @BeforeEach
    void setup() {
        ArrayList<Ingredients> ingredientsList = new ArrayList<>();
        ingredientsList.add(new Ingredients("potato"));
        testRecipe = new Recipe("test", "lunch", ingredientsList, 10,20,30,40);

    }

    @Test
    void testConstructor() {
        assertEquals("test", testRecipe.getName());
        assertEquals("lunch", testRecipe.getTimeOfMeal());
        assertEquals(1, testRecipe.getIngredient().size());
        assertEquals(10, testRecipe.getCalories());
        assertEquals(20, testRecipe.getCarbs());
        assertEquals(30, testRecipe.getFats());
        assertEquals(40, testRecipe.getProtein());

        testRecipe.setRecipeName("test2");
        testRecipe.setTimeOfMeal("dinner");

        assertEquals("test2", testRecipe.getName());
        assertEquals("dinner", testRecipe.getTimeOfMeal());

    }

    @Test
    void testToString() {
        assertEquals("Name: test, Time of meal: lunch, Ingredients: [potato], Calories: 10, Carbs: 20," +
                        " Fats: 30, Protein: 40",
                testRecipe.toString());
    }
}