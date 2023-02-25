package model;


// Represents an Ingredient with its name.
public class Ingredients {
    private final String name;

    // EFFECTS: Creates an Ingredient with a name.
    public Ingredients(String name) {
        this.name = name;
    }

    // EFFECTS: Returns a string with the name of the ingredient.
    public String toString() {
        return name;
    }

    public String getName() {
        return this.name;
    }
}


