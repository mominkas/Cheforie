package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientsTest {
    Ingredients testIngredients;

    @BeforeEach
    void setup() {
        testIngredients = new Ingredients("test");
    }

    @Test
    void testConstructor() {
        assertEquals("test", testIngredients.getName());
    }

    @Test
    void testToString() {
        assertEquals("test", testIngredients.toString());
    }

}
