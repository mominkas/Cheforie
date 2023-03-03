package model;


import org.json.JSONObject;
import persistence.Writable;

// Represents an Ingredient with its name.
public class Ingredients implements Writable {
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


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}


