package model;


// Represents an Ingredient with its name.
public class Ingredients {
    private final String name;

    public Ingredients(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return this.name;
    }
}


