package ui;

import model.EmptyRecipeList;
import model.Ingredients;
import model.Recipe;
import model.RecipeList;

import java.util.ArrayList;
import java.util.Scanner;

// Cheforie: Recipe randomizer application
public class RecipeApp {
    private RecipeList parentRecipeList;
    private Scanner input;
    private ArrayList<Recipe> fixedMealsList;
    Recipe breakfast;
    Recipe lunch;
    Recipe dinner;


    //EFFECTS: Runs Cheforie.
    public RecipeApp() {
        runRecipe();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runRecipe() {
        boolean keepRunning = true;
        String userCommand;
        initialize();


        while (keepRunning) {
            displayMenu();
            userCommand = input.next();
            userCommand = userCommand.toLowerCase();

            if (userCommand.equals("s")) {
                keepRunning = false;
            } else {
                useCommand(userCommand);
            }
        }

        System.out.println("\n See you tomorrow, keep on cooking!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command and calls corresponding method.
    private void useCommand(String command) {
        switch (command) {
            case "c":
                addRecipeName();
                break;
            case "h":
                fixedDailyMeal();
                break;
            case "e":
                viewRecipes();
                break;
            case "f":
                startRandomMealPlan();
                break;
            default:
                System.out.println("Invalid Input :(");
                break;
        }

    }


    // EFFECTS: displays the user menu.
    private void displayMenu() {
        System.out.println("\n Welcome to Cheforie! Please choose from one of the options below:");
        System.out.println("\t C -> Add a recipe");
        System.out.println("\t H -> Fixed daily meals");
        System.out.println("\t E -> View all recipes");
        System.out.println("\t F -> Generate a randomized meal plan");
        System.out.println("\t S -> Exit");

    }

    // MODIFIES: this
    // EFFECTS: Initializes the required recipe list, fixed meal lists, adds recipes, and starts the scanner.
    private void initialize() {
        ArrayList<Ingredients> ingredientsList = new ArrayList<>();
        ingredientsList.add(new Ingredients("potato"));
        Recipe recipe = new Recipe("Test1",
                "lunch", ingredientsList, 500, 120, 120, 120);
        Recipe recipe1 = new Recipe("Test2",
                "lunch", ingredientsList, 800, 120, 120, 120);
//        Recipe recipe2 = new Recipe("Test3",
//                "lunch", ingredientsList, 1000, 120, 120, 120);
//        Recipe recipe3 = new Recipe("Test4",
//                "breakfast", ingredientsList, 500, 120, 120, 120);
//        Recipe recipe4 = new Recipe("Test5",
//                "breakfast", ingredientsList, 700, 120, 120, 120);
//        Recipe recipe5 = new Recipe("Test6",
//                "breakfast", ingredientsList, 900, 120, 120, 120);
//        Recipe recipe6 = new Recipe("Test7",
//                "dinner", ingredientsList, 300, 120, 120, 120);
//        Recipe recipe7 = new Recipe("Test8",
//                "dinner", ingredientsList, 800, 120, 120, 120);
//        Recipe recipe8 = new Recipe("Test9",
//                "dinner", ingredientsList, 900, 120, 120, 120);
//        Recipe recipe9 = new Recipe("Test10",
//                "breakfast", ingredientsList, 150, 120, 120, 120);

        fixedMealsList = new ArrayList<>();
        parentRecipeList = new RecipeList();
        parentRecipeList.addRecipe(recipe);
        parentRecipeList.addRecipe(recipe1);
//        parentRecipeList.addRecipe(recipe2);
//        parentRecipeList.addRecipe(recipe3);
//        parentRecipeList.addRecipe(recipe4);
//        parentRecipeList.addRecipe(recipe5);
//        parentRecipeList.addRecipe(recipe6);
//        parentRecipeList.addRecipe(recipe7);
//        parentRecipeList.addRecipe(recipe8);
//        parentRecipeList.addRecipe(recipe9);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: Takes user input as the name of the recipe.
    private void addRecipeName() {
        String name = "";  // force entry into loop

        while (name.length() == 0) {
            System.out.println("\nPlease add your recipe based on the following instructions:");
            System.out.println("\nWhat is the NAME of the recipe?");
            name = input.next();
        }

        addRecipeTimeOfMeal(name);

    }

    // EFFECTS: Takes user input as the time of meal of the recipe.
    private void addRecipeTimeOfMeal(String name) {
        String timeOfMeal = "";  // force entry into loop


        while (!(timeOfMeal.equals("breakfast") || timeOfMeal.equals("lunch") || timeOfMeal.equals("dinner"))) {
            System.out.println("\nWhat is the TIME of the meal (breakfast, lunch, or dinner)");
            timeOfMeal = input.next();
        }

        addRecipeIngredients(name, timeOfMeal);

    }

    // EFFECTS: Takes user input as the Ingredients of the recipe and adds them to the list.
    private void addRecipeIngredients(String name, String timeOfMeal) {
        String ingredients = "";  // force entry into loop
        ArrayList<Ingredients> newRecipeIngredients = new ArrayList<>();
        System.out.println("\nPlease enter the INGREDIENTS for the recipe. Type 'done' when completed.");

        while (!(ingredients.equals("done"))) {
            Ingredients ing = new Ingredients(ingredients);
            ingredients = input.next();
            newRecipeIngredients.add(ing);
        }
        newRecipeIngredients.remove(0);
        addRecipeCalories(name, timeOfMeal, newRecipeIngredients);

    }

    // EFFECTS: Takes user input as the Calories of the recipe.
    private void addRecipeCalories(String name, String timeOfMeal, ArrayList<Ingredients> ing) {
        int calories = -1;  // force entry into loop
        System.out.println("\nPlease enter the CALORIES for this recipe.");

        while (calories == -1) {
            String inputString = input.next();

            if (!inputString.isEmpty()) {
                try {
                    calories = Integer.parseInt(inputString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number for Calories.");

                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for Calories.");
            }
        }

        addRecipeCarbs(name, timeOfMeal, ing, calories);

    }

    // EFFECTS: Takes user input as the Carbs of the recipe.
    public void addRecipeCarbs(String name, String timeOfMeal, ArrayList<Ingredients> ing, int calories) {
        int carbs = -1;  // force entry into loop
        System.out.println("\nPlease enter the CARBS for this recipe.");


        while (carbs == -1) {
            String inputString = input.next();

            if (!inputString.isEmpty()) {
                try {
                    carbs = Integer.parseInt(inputString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number for Carbs.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for Carbs.");
            }
        }

        addRecipeFats(name, timeOfMeal, ing, calories, carbs);

    }

    // EFFECTS: Takes user input as the Fats of the recipe.
    public void addRecipeFats(String name, String timeOfMeal, ArrayList<Ingredients> ing, int calories, int carbs) {
        int fats = -1;  // force entry into loop
        System.out.println("\nPlease enter the FATS for this recipe.");

        while (fats == -1) {
            String inputString = input.next();

            if (!inputString.isEmpty()) {
                try {
                    fats = Integer.parseInt(inputString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number for Fats.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for Fats.");
            }
        }

        addRecipeProtein(name, timeOfMeal, ing, calories, carbs, fats);

    }

    // EFFECTS: Takes user input as the Protein of the recipe.
    public void addRecipeProtein(String name, String timeOfMeal, ArrayList<Ingredients> ing,
                                 int calories, int carbs, int fats) {
        int protein = -1;  // force entry into loop
        System.out.println("\nPlease enter the PROTEIN for this recipe.");

        while (protein == -1) {
            String inputString = input.next();

            if (!inputString.isEmpty()) {
                try {
                    protein = Integer.parseInt(inputString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number for Protein.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for Protein.");
            }
        }

        addNewRecipe(name, timeOfMeal, ing, calories, carbs, fats, protein);
    }


    // MODIFIES: this
    // EFFECTS: Takes user input all the acquired user inputs to create a new recipe, adds the recipe to the parent list
    public void addNewRecipe(String name, String timeOfMeal, ArrayList<Ingredients> ing,
                             int calories, int carbs, int fats, int protein) {
        String backToMenu = "";
        Recipe recipe = new Recipe(name, timeOfMeal, ing, calories, carbs, fats, protein);
        parentRecipeList.getRecipeList().add(recipe);
        System.out.println("\nYour Recipe has been added, here is your current list:");

        viewRecipes();
        while (!backToMenu.equals("e")) {
            System.out.println("\nPlease type E to go back to the main menu");
            backToMenu = input.next();
            backToMenu = backToMenu.toLowerCase();
        }

    }

    // EFFECTS: Takes user input and calls corresponding function
    private void fixedDailyMeal() {
        String fixedDailyMeal = "";  // force entry into loop

        while (!(fixedDailyMeal.equals("r") || fixedDailyMeal.equals("a") || fixedDailyMeal.equals("v")
                || fixedDailyMeal.equals("b"))) {
            System.out.println("\nPlease select from one of the options below:");
            System.out.println("A -> Add a fixed meal");
            System.out.println("V -> View fixed meals");
            System.out.println("R -> Remove a fixed meal");
            System.out.println("B -> Back to Main Menu");
            fixedDailyMeal = input.next();
            fixedDailyMeal = fixedDailyMeal.toLowerCase();
        }

        switch (fixedDailyMeal) {
            case "a":
                addFixedMeal();
                break;
            case "v":
                viewFixedMealsAndReturnToMenu();
                break;
            case "r":
                removeFixedMeal();
                break;
        }

    }

    // MODIFIES: this (fixed meals list and parentRecipeList)
    // EFFECTS: Allows user to pick a recipe from the parent list and add it to the fixed meal list.
    private void addFixedMeal() {
        int fixedMeal = -1;  // force entry into loop
        System.out.println("\nPlease choose the recipe number that you would like to add to your fixed meals:");
        viewRecipes();
        while (fixedMeal == -1) {
            String inputString = input.next();
            if (!inputString.isEmpty()) {
                try {
                    fixedMeal = Integer.parseInt(inputString);
                    int listBounds = Integer.parseInt(inputString);
                    if (listBounds <= 0 || listBounds > parentRecipeList.getRecipeList().size()) {
                        fixedMeal = -1;
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    fixedMeal = -1;
                }
            }

        }
        Recipe changeToFixedMeal = parentRecipeList.getRecipeList().remove(fixedMeal - 1);
        fixedMealsList.add(changeToFixedMeal);
        System.out.println("Your recipe has been removed from the list and added to your fixed meals!");
        fixedDailyMeal();

    }

    // MODIFIES: this (fixed meals list and parentRecipeList)
    // EFFECTS: Allows user to remove a recipe from the fixed meals list and add it back to the parent list.
    private void removeFixedMeal() {
        int fixedMeal = -1;  // force entry into loop
        System.out.println("\nPlease choose the fixed meal you would like to remove:");
        viewFixedMealsForRemoval();
        while (fixedMeal == -1) {
            String inputString = input.next();
            if (!inputString.isEmpty()) {
                try {
                    fixedMeal = Integer.parseInt(inputString);
                    int listBounds = Integer.parseInt(inputString);
                    if (listBounds <= 0 || listBounds > fixedMealsList.size()) {
                        fixedMeal = -1;
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    fixedMeal = -1;
                }
            }

        }
        Recipe changeToFixedMeal = fixedMealsList.remove(fixedMeal - 1);
        parentRecipeList.getRecipeList().add(changeToFixedMeal);
        System.out.println("Your recipe has been removed and added back to your recipe list!");
        fixedDailyMeal();


    }

    // EFFECTS: Allows the user to view their current list of recipes.
    private void viewRecipes() {
        ArrayList<Recipe> printList = parentRecipeList.getRecipeList();
        int counter = 1;
        if (printList.size() == 0) {
            System.out.println("Recipe list is empty :(");
        } else {
            for (Recipe recipe : printList) {
                System.out.println(counter + ". " + recipe);
                counter++;
            }


        }

    }

    // EFFECTS: Allows the user to view the list of fixed meals.
    private void viewFixedMealsAndReturnToMenu() {
        int counter = 1;
        if (fixedMealsList.size() == 0) {
            System.out.println("Fixed meals list is empty :(");
        } else {
            for (Recipe recipe : fixedMealsList) {
                System.out.println(counter + ". " + recipe);
                counter++;
            }

        }

        fixedDailyMeal();

    }

    // EFFECTS: Allows the user to view the list of fixed meals.
    private void viewFixedMealsForRemoval() {
        int counter = 1;
        if (fixedMealsList.size() == 0) {
            System.out.println("Fixed meals list is empty :(");
        } else {
            for (Recipe recipe : fixedMealsList) {
                System.out.println(counter + ". " + recipe);
                counter++;
            }

        }

    }

    // EFFECTS: Gets the macros from all recipes in the fixed meals list and returns as an int list
    //          where index 0 = calories, 1 = carbs, 2 = fats, 3 = protein
    private int[] fixedMealMacros() {
        int fixedMealCals = 0;
        int fixedMealProtein = 0;
        int fixedMealFats = 0;
        int fixedMealCarbs = 0;
        for (Recipe recipe : fixedMealsList) {
            fixedMealCals = fixedMealCals + recipe.getCalories();
            fixedMealProtein = fixedMealProtein + recipe.getProtein();
            fixedMealFats = fixedMealFats + recipe.getFats();
            fixedMealCarbs = fixedMealCarbs + recipe.getCarbs();
        }
        return new int[]{fixedMealCals, fixedMealCarbs, fixedMealFats, fixedMealProtein};


    }

    // REQUIRES: Non-empty parent recipe list with at least one breakfast, lunch, and dinner recipe each.
    // EFFECTS: Generates a randomized meal plan adhering to the calorie limit set by the user.
    private void startRandomMealPlan() {
        float randomMealCalories = -1;  // force entry into loop
        System.out.println("\nInput your calorie limit and Cheforie will cook up your meal plan for the day!");
        while (randomMealCalories == -1) {
            String inputString = input.next();

            if (!inputString.isEmpty()) {
                try {
                    randomMealCalories = Integer.parseInt(inputString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
            }
            printFinalMacros(randomMealCalories);
        }
    }

    private void getRandomBreakfast() {
        try {
            breakfast = parentRecipeList.filterRecipeListForTime("breakfast").randomRecipeChooser();
        } catch (EmptyRecipeList e) {
            ArrayList<Ingredients> ingredientsList = new ArrayList<>();
            breakfast = new Recipe("No Breakfast Added!", "breakfast", ingredientsList, 0, 0, 0, 0);
        }
    }

    private void getRandomLunch() {
        try {
            lunch = parentRecipeList.filterRecipeListForTime("lunch").randomRecipeChooser();
        } catch (EmptyRecipeList e) {
            ArrayList<Ingredients> ingredientsList = new ArrayList<>();
            lunch = new Recipe("No Lunch Added!", "breakfast", ingredientsList, 0, 0, 0, 0);
        }
    }

    private void getRandomDinner() {
        try {
            dinner = parentRecipeList.filterRecipeListForTime("dinner").randomRecipeChooser();
        } catch (EmptyRecipeList e) {
            ArrayList<Ingredients> ingredientsList = new ArrayList<>();
            dinner = new Recipe("No Dinner Added!", "breakfast", ingredientsList, 0, 0, 0, 0);
        }
    }

    private int[] randomMealPlan(float cals) {
        int totalCalories;
        int totalCarbs;
        int totalFats;
        int totalProtein;

        do {
            getRandomBreakfast();
            getRandomLunch();
            getRandomDinner();
            int[] fixedMealStats = fixedMealMacros();
            totalCalories = fixedMealStats[0] + breakfast.getCalories() + lunch.getCalories() + dinner.getCalories();
            totalProtein = fixedMealStats[1] + breakfast.getProtein() + lunch.getProtein() + dinner.getProtein();
            totalFats = fixedMealStats[2] + breakfast.getFats() + lunch.getFats() + dinner.getFats();
            totalCarbs = fixedMealStats[3] + breakfast.getCarbs() + lunch.getCarbs() + dinner.getCarbs();
        } while (!(totalCalories <= cals));
        System.out.println("\nToday's BREAKFAST is: " + breakfast);
        System.out.println("\nToday's LUNCH is: " + lunch);
        System.out.println("\nToday's DINNER is: " + dinner);
        if (fixedMealsList.isEmpty()) {
            System.out.println("\nYou have no fixed meals for today.");
        } else {
            System.out.println("\nYour fixed meals are: " + fixedMealsList);
        }
        return new int[]{totalCalories, totalCarbs, totalFats, totalProtein};

    }

    private void printFinalMacros(float cals) {
        int[] finalMealMacros = randomMealPlan(cals);
        String backToMenu = "";
        System.out.println("\nYour total CALORIES for the day are: " + finalMealMacros[0]);
        System.out.println("\nYour total CARBS for the day are: " + finalMealMacros[1]);
        System.out.println("\nYour total FATS for the day are: " + finalMealMacros[2]);
        System.out.println("\nYour total Protein for the day is: " + finalMealMacros[3]);
        System.out.println("\nIT'S TIME TO COOK!");

        while (!backToMenu.equals("e")) {
            System.out.println("\nPlease type E to return to the main menu");
            backToMenu = input.next();
            backToMenu = backToMenu.toLowerCase();

        }
    }
}
