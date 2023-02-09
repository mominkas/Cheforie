package ui;

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


    // Runs Cheforie
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
    // EFFECTS: processes user command
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
                randomMealPlan();
                break;
            default:
                System.out.println("Invalid Input :(");
                break;
        }

    }


    private void displayMenu() {
        System.out.println("\n Welcome to Cheforie! Please choose from one of the options below:");
        System.out.println("\t C -> Add a recipe");
        System.out.println("\t H -> Fixed daily meals");
        System.out.println("\t E -> View all recipes");
        System.out.println("\t F -> Generate a randomized meal plan");
        System.out.println("\t S -> Exit");

    }

    private void initialize() {
        ArrayList<Ingredients> ingredientsList = new ArrayList<>();
        fixedMealsList = new ArrayList<>();
        ingredientsList.add(new Ingredients("potato"));
        Recipe recipe = new Recipe("Test1",
                "lunch", ingredientsList, 500, 120, 120, 120);
        Recipe recipe1 = new Recipe("Test2",
                "lunch", ingredientsList, 800, 120, 120, 120);
        Recipe recipe2 = new Recipe("Test3",
                "lunch", ingredientsList, 1000, 120, 120, 120);
        Recipe recipe3 = new Recipe("Test4",
                "breakfast", ingredientsList, 500, 120, 120, 120);
        Recipe recipe4 = new Recipe("Test5",
                "breakfast", ingredientsList, 700, 120, 120, 120);
        Recipe recipe5 = new Recipe("Test6",
                "breakfast", ingredientsList, 900, 120, 120, 120);
        Recipe recipe6 = new Recipe("Test7",
                "dinner", ingredientsList, 300, 120, 120, 120);
        Recipe recipe7 = new Recipe("Test8",
                "dinner", ingredientsList, 800, 120, 120, 120);
        Recipe recipe8 = new Recipe("Test9",
                "dinner", ingredientsList, 900, 120, 120, 120);
        Recipe recipe9 = new Recipe("Test10",
                "breakfast", ingredientsList, 150, 120, 120, 120);

        parentRecipeList = new RecipeList();
        parentRecipeList.addRecipe(recipe);
        parentRecipeList.addRecipe(recipe1);
        parentRecipeList.addRecipe(recipe2);
        parentRecipeList.addRecipe(recipe3);
        parentRecipeList.addRecipe(recipe4);
        parentRecipeList.addRecipe(recipe5);
        parentRecipeList.addRecipe(recipe6);
        parentRecipeList.addRecipe(recipe7);
        parentRecipeList.addRecipe(recipe8);
        parentRecipeList.addRecipe(recipe9);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void addRecipeName() {
        String name = "";  // force entry into loop

        while (name.length() == 0) {
            System.out.println("\nPlease add your recipe based on the following instructions:");
            System.out.println("\nWhat is the NAME of the recipe?");
            name = input.next();
        }

        addRecipeTimeOfMeal(name);

    }

    private void addRecipeTimeOfMeal(String name) {
        String timeOfMeal = "";  // force entry into loop

        while (!(timeOfMeal.equals("breakfast") || timeOfMeal.equals("lunch") || timeOfMeal.equals("dinner"))) {
            System.out.println("\nWhat is the TIME of the meal (breakfast, lunch, or dinner)");
            timeOfMeal = input.next();
        }

        addRecipeIngredients(name, timeOfMeal);

    }

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
                    calories = -1;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for Calories.");
            }
        }

        addRecipeCarbs(name, timeOfMeal, ing, calories);

    }

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
                    carbs = -1;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for Carbs.");
            }
        }

        addRecipeFats(name, timeOfMeal, ing, calories, carbs);

    }

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
                    fats = -1;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for Fats.");
            }
        }

        addRecipeProtein(name, timeOfMeal, ing, calories, carbs, fats);

    }

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
                    protein = -1;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for Protein.");
            }
        }

        addNewRecipe(name, timeOfMeal, ing, calories, carbs, fats, protein);
    }


    public void addNewRecipe(String name, String timeOfMeal, ArrayList<Ingredients> ing,
                             int calories, int carbs, int fats, int protein) {
        Recipe recipe = new Recipe(name, timeOfMeal, ing, calories, carbs, fats, protein);
        parentRecipeList.getRecipeList().add(recipe);


    }

    private void fixedDailyMeal() {
        String fixedDailyMeal = "";  // force entry into loop

        while (!(fixedDailyMeal.equals("r") || fixedDailyMeal.equals("a") || fixedDailyMeal.equals("v")
                || fixedDailyMeal.equals("b"))) {
            System.out.println("Please select from one of the options below:");
            System.out.println("A -> Add a fixed meal");
            System.out.println("V -> View fixed meals");
            System.out.println("R -> Remove a fixed meal");
            System.out.println("B -> Back to Main Menu");
            fixedDailyMeal = input.next();
            fixedDailyMeal = fixedDailyMeal.toLowerCase();
        }

        if (fixedDailyMeal.equals("a")) {
            addFixedMeal();
        } else if (fixedDailyMeal.equals("v")) {
            viewFixedMeals();
        } else if (fixedDailyMeal.equals("b")) {
            displayMenu();
        } else {
            removeFixedMeal();
        }

    }

    private void addFixedMeal() {
        int fixedMeal = -1;  // force entry into loop
        System.out.println("\nPlease choose the recipe number that you would like to add to your fixed meals:");
        viewRecipes();
        while (fixedMeal == -1) {
            String inputString = input.next();
            int length = parentRecipeList.getRecipeList().size();
            if (!inputString.isEmpty()) {
                try {
                    fixedMeal = Integer.parseInt(inputString);
                    int listBounds = Integer.parseInt(inputString);
                    if (listBounds <= 0 || listBounds > length) {
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

    }

    private void removeFixedMeal() {
        int fixedMeal = -1;  // force entry into loop
        System.out.println("\nPlease choose the fixed meal you would like to remove:");
        viewFixedMeals();
        while (fixedMeal == -1) {
            String inputString = input.next();
            int length = fixedMealsList.size();
            if (!inputString.isEmpty()) {
                try {
                    fixedMeal = Integer.parseInt(inputString);
                    int listBounds = Integer.parseInt(inputString);
                    if (listBounds <= 0 || listBounds > length) {
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


    }

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

    private void viewFixedMeals() {
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

    private void randomMealPlan() {
        float randomMealCalories = -1;  // force entry into loop
        int totalCalories;
        int totalCarbs;
        int totalFats;
        int totalProtein;
        Recipe breakfast;
        Recipe lunch;
        Recipe dinner;

        System.out.println("\nInput your calorie limit and Cheforie will cook up your meal plan for the day!");
        while (randomMealCalories == -1) {
            String inputString = input.next();

            if (!inputString.isEmpty()) {
                try {
                    randomMealCalories = Integer.parseInt(inputString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    randomMealCalories = -1;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        do {
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
            breakfast = parentRecipeList.randomRecipeForGivenTime("breakfast").randomRecipeChooser();
            lunch = parentRecipeList.randomRecipeForGivenTime("lunch").randomRecipeChooser();
            dinner = parentRecipeList.randomRecipeForGivenTime("dinner").randomRecipeChooser();

            totalCalories = fixedMealCals + breakfast.getCalories() + lunch.getCalories() + dinner.getCalories();
            totalProtein = fixedMealProtein + breakfast.getProtein() + lunch.getProtein() + dinner.getProtein();
            totalFats = fixedMealFats + breakfast.getFats() + lunch.getFats() + dinner.getFats();
            totalCarbs = fixedMealCarbs + breakfast.getCarbs() + lunch.getCarbs() + dinner.getCarbs();

        } while (!(totalCalories <= randomMealCalories));
        System.out.println("Today's BREAKFAST is: " + breakfast);
        System.out.println("Today's LUNCH is: " + lunch);
        System.out.println("Today's DINNER is: " + dinner);
        System.out.println("Your fixed meals are: " + fixedMealsList);
        System.out.println("Your total CALORIES for the day are: " + totalCalories);
        System.out.println("Your total CARBS for the day are: " + totalCarbs);
        System.out.println("Your total FATS for the day are: " + totalFats);
        System.out.println("Your total Protein for the day is: " + totalProtein);
        System.out.println("IT'S TIME TO COOK!");


    }
}
