# Personal Project: *Cheforie*

## An app that picks your meals for you.

This purpose of this application is to generate a random meal plan based on the recipes provided by the user. The
meal plan will include breakfast, lunch, and dinner. The application will make sure that the generated meal plan stays
within the calorie limit set by the user.

This application is for people that have trouble deciding what to eat every day as it randomly generates a new meal
plan for each day. It is also for people who have restricted their calories to a certain amount due to fitness goals.
In this scenario people usually tend to eat the same meal every day since they know the exact macros and calories in
their
meal; however, they eventually get tired of eating the same thing over and over again. My application will help them in
this
endeavour as they can add multiple recipes that are suitable for their fitness
goals and the application will help them by generating a new combination of meals every day that stays within the
desired
calorie limit.

This project is of interest to me as I have faced similar problems when trying to stay within a given caloric limit
for my fitness goals. I found it very hard to decide what to eat every day, due to which I started having the same meal
each day over a long period of time. After the first week or so, I would get tired of eating the same meal, but
it would always feel like a lot of work to add new meals and decide what to eat every day while also making sure about 
tracking my calories. I believe this application will be beneficial to me and people with similar struggles as it 
automates the entire process of choosing what to eat while making sure to target the provided caloric limit.



## User Stories

- As a *user* I want to be able to add **new recipes** to my recipe list.
- As a *user* I want to be able to set a **calorie limit** for each day.
- As a *user* I want to set certain items that I want to **eat every day** such as a protein bar or shake.
- As a *user* I want to be able to see my total macros for **carbs, protein, and fat** per daily meal plan.

- As a *user*, I want to be able to **save my recipe list and fixed meals list** to file (if I so choose)
- As a *user*, I want to be able to be able to **load my recipe list and fixed meal list** from file (if I so choose)

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by clicking the add Recipe button and following
the provided instructions. To view if a recipe has been added, click on view recipes.
- You can generate the second required action related to adding Xs to a Y by going to view recipes and clicking on
filter on the bottom right. Type in either breakfast, lunch, or dinner, to filter your recipes accordingly and get a new 
filtered list that you can view now.
- You can locate my visual component by looking at the top left, this is my logo
- You can save the state of my application by clicking save on the main menu.
- You can reload the state of my application by clicking load from the main menu.

# Phase 4: Task 2

Sample of Events for:

1. Add recipe named test1
2. Add recipe named test2
3. Filter for lunch
4. Filter for dinner
5. Filter for breakfast
6. Add recipe named test3

Results: 

Tue Apr 11 05:09:54 PDT 2023
Added test1 to your recipe list!

Tue Apr 11 05:10:14 PDT 2023
Added test2 to your recipe list!

Tue Apr 11 05:10:22 PDT 2023
Filtered for lunch recipes!

Tue Apr 11 05:10:25 PDT 2023
Filtered for dinner recipes!

Tue Apr 11 05:10:30 PDT 2023
Filtered for breakfast recipes!

Tue Apr 11 05:10:46 PDT 2023
Added test3 to your recipe list!

# Phase 4: Task 3

The current structure of my UMl class diagram seems really intuitive. My gui class has fields for the JsonWriter,
JsonReader, RecipeList and Recipe classes. There is only one instance of the RecipeList class that the gui class calls,
which allows it to have two lists of recipes through this class, one as the main list and one as a fixed recipe list.
There are three instances for the Recipe class in the gui for the separate meal times breakfast, lunch, dinner.
The relation between the RecipeList class and the Recipe class is an aggregation as a Recipe is a core part of the 
RecipeList class. Similarly, the relation between Recipe and Ingredients is also an aggregation since ingredients are 
a part of a recipe. The RecipeList, Recipe, and Ingredients class implement the Writable interface through which they 
each have their own implementation of the tojson method which helps in converting an object to a JSONOBJECT.

During the course of my project, I have already done some refactoring. The main refactoring I did was to add an extra
ArrayList for fixed meals in the RecipeList class. Initially, it only had a single list of recipes, and I was making a
new RecipeList object for the fixed meals when I had to add items from the main list to the fixed meals list. I ended up
adding another ArrayList of Recipes as a field to the class which now acts as a fixed meals list and there is no need
to create a new object for the fixed meals. It is also much easier to move recipes between the fixed meals and the main
recipe lists and everything is housed in a single object. 

Some refactoring I wish to do is with the Ingredients class. Currently, the Ingredients class only makes an ingredient
that has a String field for it's name and nothing else. I want to add fields for the macros for the ingredient as well. 
Then, I will make a similar structure to my RecipeList and Recipe classes. I will make a new class called
IngredientList. This class will have an aggregation with the Ingredients class and will have a field that stores a
collection of Ingredients. Then, instead of my Recipe class having an aggregation with a collection of Ingredients as it
currently does, it would effectively only have a regular association with the IngredientList class. This will allow each
ingredient to have its own macros and allow more functionality in the Recipe class where the macros will now be the 
sum of the macros for each ingredient present in the list. Additionally, I believe that the current association 
between by gui class and Recipe class could also work as a dependency since those 3 fields are only used to make a
new Recipe that is eventually added to the RecipeList class. 



