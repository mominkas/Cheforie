package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Cheforie: Recipe randomizer application gui
public class CheforieGui extends JFrame implements ActionListener {
    private RecipeList parentRecipeList;
    private static final String JSON_STORE = "./data/recipelist.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    Recipe breakfast;
    Recipe lunch;
    Recipe dinner;
    JPanel buttonPanel;
    CardLayout cardLayout;
    JPanel cardPanel;
    JButton addRecipeButton;
    JButton viewFixedMealsButton;
    JButton viewRecipeButton;
    JButton generateARandomizedMealPlan;
    JButton saveButton;
    JButton loadButton;

    //MODIFIES: this
    //EFFECTS: Starts the gui, makes instantiations and calls init.
    CheforieGui() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        init();

    }

    //MODIFIES: this
    //EFFECTS: sets up the frame and panels, adds all the buttons for the main menu
    public void init() {
        setTitle("Cheforie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the WindowListener to the JFrame
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentRecipeList.printRecipeEventLog();
            }
        });


        getContentPane().setBackground(new Color(233, 220, 211));
        parentRecipeList = new RecipeList();

        JLabel label = createLogoLabel();
        buttonPanel = createButtonPanel();
        cardPanel.add(buttonPanel, "main");
        cardPanel.setOpaque(false);
        addComponents(label, cardPanel);
        addRecipeButton.addActionListener(this);
        viewFixedMealsButton.addActionListener(this);
        viewRecipeButton.addActionListener(this);
        generateARandomizedMealPlan.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);



        setVisible(true);
        pack();
    }

    //MODIFIES: this
    //EFFECTS: Creates the JLabel used to position the cheforie logo with
    private JLabel createLogoLabel() {
        JLabel label = new JLabel();
        ImageIcon logo = new ImageIcon("data/Cheforie.png");
        label.setIcon(logo);
        label.setVerticalAlignment(JLabel.TOP);
        return label;
    }

    //MODIFIES: this
    //EFFECTS: Creates the first Jpanel that has buttons for the main menu
    private JPanel createButtonPanel() {
        buttonPanel = new JPanel(new GridLayout(0, 1));
        addRecipeButton = new JButton("Add a recipe");
        viewFixedMealsButton = new JButton("Fixed daily meals");
        viewRecipeButton = new JButton("View recipes");
        generateARandomizedMealPlan = new JButton("Generate randomized meal plan");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        buttonPanel.add(addRecipeButton);
        buttonPanel.add(viewRecipeButton);
        buttonPanel.add(viewFixedMealsButton);
        buttonPanel.add(generateARandomizedMealPlan);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.setOpaque(false);

        return buttonPanel;
    }

    //MODIFIES: this
    //EFFECTS: adds the label and cardPanel corresponding to main menu panel to the frame
    private void addComponents(JLabel label, JPanel cardPanel) {
        add(label, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }


    //EFFECTS: handles user input and calls appropriate related function.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addRecipeButton)) {
            addRecipe();
        }
        if (e.getSource().equals(viewRecipeButton)) {
            viewRecipes(parentRecipeList);
        }
        if (e.getSource().equals(viewFixedMealsButton)) {
            fixedMealsMenu();
        }
        if (e.getSource().equals(generateARandomizedMealPlan)) {
            mealPlanGenerator();
        }
        if (e.getSource().equals(saveButton)) {
            save();
        }
        if (e.getSource().equals(loadButton)) {
            load();
        }

    }


    //MODIFIES: this
    //EFFECTS: makes new panel that allows user to input a new recipe to the recipe list.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void addRecipe() {
        JPanel inputPanel = new JPanel(new GridLayout(0, 1));
        inputPanel.setOpaque(false);
        JLabel nameLabel = new JLabel("Recipe Name:");
        JTextField nameField = new JTextField();
        JLabel timeLabel = new JLabel("Time of Meal (breakfast, lunch, dinner):");
        JTextField timeField = new JTextField();
        JLabel ingredientLabel = new JLabel("Ingredients (separated by commas):");
        JTextField ingredientField = new JTextField();
        JLabel calsLabel = new JLabel("Calories:");
        JTextField calsField = new JTextField();
        JLabel carbsLabel = new JLabel("Carbs:");
        JTextField carbsField = new JTextField();
        JLabel fatsLabel = new JLabel("Fats:");
        JTextField fatsField = new JTextField();
        JLabel proteinLabel = new JLabel("Protein:");
        JTextField proteinField = new JTextField();
        JButton saveButton = new JButton("Save");


        // Add the components to the input panel
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(timeLabel);
        inputPanel.add(timeField);
        inputPanel.add(ingredientLabel);
        inputPanel.add(ingredientField);
        inputPanel.add(calsLabel);
        inputPanel.add(calsField);
        inputPanel.add(carbsLabel);
        inputPanel.add(carbsField);
        inputPanel.add(fatsLabel);
        inputPanel.add(fatsField);
        inputPanel.add(proteinLabel);
        inputPanel.add(proteinField);
        inputPanel.add(saveButton);



        cardPanel.add(inputPanel, "input");
        cardLayout.show(cardPanel, "input");
        saveButton.addActionListener(e -> {
            ArrayList<Ingredients> ingList = new ArrayList<>();
            String recipeName = nameField.getText();
            String timeOfMeal = timeField.getText();
            String[] ingredients = ingredientField.getText().split(",");
            String strCals = calsField.getText();
            int cals = Integer.parseInt(strCals);
            String strCarbs = carbsField.getText();
            int carbs = Integer.parseInt(strCarbs);
            String strFats = fatsField.getText();
            int fats = Integer.parseInt(strFats);
            String strProtein = proteinField.getText();
            int protein = Integer.parseInt(strProtein);
            for (String str : ingredients) {
                ingList.add(new Ingredients(str));
            }
            Recipe recipe = new Recipe(recipeName, timeOfMeal, ingList, cals, carbs, fats, protein);
            parentRecipeList.addRecipe(recipe);
            cardLayout.show(cardPanel, "main");

        });

        pack();


    }

    //MODIFIES: this
    //EFFECTS: new panel in which users can view all their recipes, also allows users to filter recipe list.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void viewRecipes(RecipeList recList) {
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setOpaque(false);

        JTextArea recipeList = new JTextArea();
        recipeList.setBackground(new Color(233,220,211));
        recipeList.setEditable(false);
        recipeList.setLineWrap(true);
        recipeList.setWrapStyleWord(true);

        // add the recipes to the JTextArea
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < recList.getRecipeList().size(); i++) {
            sb.append(i + 1).append(". ").append(recList.getRecipeList().get(i)).append("\n");
        }
        recipeList.setText(sb.toString());

        cardPanel.add(viewPanel, "view");
        cardLayout.show(cardPanel, "view");

        JScrollPane scrollPane = new JScrollPane(recipeList);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton backButton = new JButton("Back to Main Menu");
        JButton filterButton = new JButton("Filter");
        buttonPanel.add(backButton);
        buttonPanel.add(filterButton);
        viewPanel.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            // remove the recipePanel from the contentPane and show the main menu panel
            getContentPane().remove(viewPanel);
            cardLayout.show(cardPanel, "main");
        });

        filterButton.addActionListener(e -> {
            // remove the recipePanel from the contentPane and show the main menu panel
            JFrame frame = new JFrame();
            String result = JOptionPane.showInputDialog(frame, "Enter breakfast, lunch, or dinner.");
            viewRecipes(parentRecipeList.filterRecipeListForTime(result));
        });

        viewPanel.add(scrollPane);
        pack();
    }

    // MODIFIES: this
    // EFFECTS: loads the fixed meal menu to allow users to choose fixed meals and related functionality.
    private void fixedMealsMenu() {
    }

    // MODIFIES: this
    // EFFECTS: the ultimate randomizer that uses my proprietary recipe randomizing method to make a meal plan
    private void mealPlanGenerator() {
    }

    // EFFECTS: saves the recipes to the specified file.
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(parentRecipeList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Your Recipes have been saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void load() {
        try {
            parentRecipeList = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Your Recipes have been loaded!");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
