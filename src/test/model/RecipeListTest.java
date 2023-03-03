package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeListTest {
    RecipeList testRecipeList;
    Recipe testRecipe;
    Recipe testRecipe2;
    Recipe testRecipe3;



    @BeforeEach
    void setup() {
        testRecipeList = new RecipeList();
        ArrayList<Ingredients> ingredientsList = new ArrayList<>();
        testRecipe = new Recipe("", "breakfast", ingredientsList, 0,0, 0, 0);
        testRecipe2 = new Recipe("", "lunch", ingredientsList, 0,0, 0, 0);
        testRecipe3 = new Recipe("", "dinner", ingredientsList, 0,0, 0, 0);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testRecipeList.getRecipeList().size());
    }

    @Test
    void testAddRecipe() {
        assertEquals(0, testRecipeList.getRecipeList().size());

        testRecipeList.addRecipe(testRecipe);

        assertEquals(1, testRecipeList.getRecipeList().size());
    }

    @Test
    void testAddFixedMealRecipe() {
        assertEquals(0, testRecipeList.getRecipeList().size());

        testRecipeList.addRecipeToFixedMeal(testRecipe);

        assertEquals(1, testRecipeList.getFixedMeals().size());
    }

    @Test
    void testAddMultipleRecipes() {
        assertEquals(0, testRecipeList.getRecipeList().size());

        testRecipeList.addRecipe(testRecipe);
        testRecipeList.addRecipe(testRecipe);

        assertEquals(2, testRecipeList.getRecipeList().size());
    }

    @Test
    void testRandomRecipeForGivenTime() {
        RecipeList filteredRecipeList;
        assertEquals(0, testRecipeList.getRecipeList().size());

        testRecipeList.addRecipe(testRecipe);
        testRecipeList.addRecipe(testRecipe2);
        testRecipeList.addRecipe(testRecipe3);
        testRecipeList.addRecipe(testRecipe3);

        assertEquals(4, testRecipeList.getRecipeList().size());

        filteredRecipeList = testRecipeList.filterRecipeListForTime("breakfast");

        assertEquals(1, filteredRecipeList.getRecipeList().size());

        filteredRecipeList = testRecipeList.filterRecipeListForTime("lunch");

        assertEquals(1, filteredRecipeList.getRecipeList().size());

        filteredRecipeList = testRecipeList.filterRecipeListForTime("dinner");

        assertEquals(2, filteredRecipeList.getRecipeList().size());

        filteredRecipeList = testRecipeList.filterRecipeListForTime("");

        assertEquals(0, filteredRecipeList.getRecipeList().size());

    }

    @Test
    void testRandomRecipeChooser() throws EmptyRecipeList {
        Recipe randomChosenRecipe;
        testRecipeList.addRecipe(testRecipe);
        testRecipeList.addRecipe(testRecipe2);
        testRecipeList.addRecipe(testRecipe3);

        randomChosenRecipe = testRecipeList.randomRecipeChooser();

        assertTrue(testRecipeList.getRecipeList().contains(randomChosenRecipe));

    }

    @Test  void testRandomRecipeEmptyChooser() throws EmptyRecipeList {

        assertThrows(EmptyRecipeList.class, () -> testRecipeList.randomRecipeChooser());




    }

}
