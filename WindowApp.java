import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class WindowApp implements Windows {

    private JFrame frame;
    private JPanel cookBookPanel;

    private JButton productSearchButton;
    private JFormattedTextField productSearchField;
    private JButton recipeSearchButton;
    private JFormattedTextField recipeSearchField;
    private JButton secondBreakfastIdeasButton;
    private JButton lunchIdeasButton;
    private JButton dinnerIdeasButton;
    private JButton supperIdeasButton;
    private JButton breakfastIdeasButton;

    private ProductProvider productProvider;
    private RecipeProvider recipeProvider;

    private ProductWindow productWindow;
    private EditableProductWindow editableProductWindow;

    private MealChooser mealChooser;

    private String searchingProduct;
    private String searchingRecipe;

    public WindowApp() {

        frame = new JFrame();
        setWindowTitle();
        addMainComponent();
        setWindowPack();
        setWindowSize();
        setWindowLocationRealtivity();
        setWindowLookAndFeel();
        setWindowDefaultCloseOperation();

        productProvider = new ProductProvider();
        recipeProvider = new RecipeProvider();

        productWindow = new ProductWindow();
        editableProductWindow = new EditableProductWindow();

        mealChooser = new MealChooser();

        productSearchButton.addActionListener(actionEvent -> {
            searchingProduct = productSearchField.getText();
            if (itemNotNull(searchingProduct)) {
                findProduct(searchingProduct);
            } else {
                JOptionPane.showMessageDialog(frame, "Nie wpisałeś produktu");
            }
        });

        recipeSearchButton.addActionListener(actionEvent -> {
            searchingRecipe = recipeSearchField.getText();
            if (itemNotNull(searchingRecipe)) {
                findRecipe(searchingRecipe);
            } else {
                JOptionPane.showMessageDialog(frame, "Nie wpisałeś nazwy przepisu");
            }
        });

        breakfastIdeasButton.addActionListener(actionEvent ->
            mealsButtonClicked("breakfast")
        );

        dinnerIdeasButton.addActionListener(actionEvent -> {
            mealsButtonClicked("dinner");
        });
    }

    private boolean itemNotNull(String item) {
        return searchingProduct.length() > 0;
    }

    private void findProduct(String productToFind) {
        String answer = productProvider.findItem(productToFind);
        if (answer.equals("There's no such product")) {
            askAboutAddingNewProduct();
        } else if (answer.equals("SQL Error") || answer.equals("Server Error")) {
            showMessageServerError();
        } else {
            showProductInfo(answer);
        }
    }

    private void findRecipe(String searchingRecipe) {
        String answer = recipeProvider.findItem(searchingRecipe);
        if (answer.equals("There's no such recipe")) {
            askAboutAddingNewRecipe();
        } else if (answer.equals("SQL Error") || answer.equals("Server Error")) {
            showMessageServerError();
        } else {
            showRecipeInfo(answer);
        }
    }

    private void askAboutAddingNewProduct() {
        int clientChoice;
        Object[] options = {"Tak", "Nie"};
        clientChoice = JOptionPane.showOptionDialog(frame, "Podanego produktu nie ma w bazie. Czy chciałbyś dodać " +
                        "ten produkt?", "Dodawanie produktu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[1]);
        if (clientChoice == 0) {
            editableProductWindow.start(searchingProduct);
        }
    }

    private void askAboutAddingNewRecipe() {
        int clientChoice;
        Object[] options = {"Tak", "Nie"};
        clientChoice = JOptionPane.showOptionDialog(frame, "Podanego przepisu nie ma w bazie. Czy chciałbyś dodać " +
                        "ten przepis?", "Dodawanie przepisu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[1]);
        if (clientChoice == 0) {
            // editableRecipeWindow.start(searchingRecipe);
        }
    }

    private void showProductInfo(String serverAnswer) {
        String[] splittedAnswer = serverAnswer.split(";", 5);
        if (splittedAnswer.length != 4) {
            showMessageServerError();
        } else {
            productWindow.start(splittedAnswer[0], splittedAnswer[1], splittedAnswer[2], splittedAnswer[3]);
        }
    }

    private void showRecipeInfo(String serverAnswer) {

    }

    private String[] createRecipesList(String serverAnswer) {
        return serverAnswer.split(";");
    }

    private void mealsButtonClicked(String mealName) {
        String serverAnswer = recipeProvider.showAllMeals(mealName);
        if(serverAnswer.equals("null")){
            showMessageServerError();
        } else {
            String[] recipes = createRecipesList(serverAnswer);
            startMealChooserWindow(mealName, recipes);
        }
    }

    private void startMealChooserWindow(String mealName, String[] recipes) {
        switch (mealName) {
            case "breakfast":
                mealChooser.start("Śniadanie", recipes, new ImageIcon("src/Images/Breakfast.jpg"));
                break;
            case "secondBreakfast":
                mealChooser.start("Drugie śniadanie", recipes, new ImageIcon("src/Images/SecondBreakfast.jpg"));
                break;
            case "lunch":
                mealChooser.start("Lunch", recipes, new ImageIcon("src/Images/Lunch.jpeg"));
                break;
            case "dinner":
                mealChooser.start("Obiad", recipes, new ImageIcon("src/Images/Dinner.jpg"));
                break;
            case "supper":
                mealChooser.start("Kolacja", recipes, new ImageIcon("src/Images/Supper.JPG"));
                break;
        }
    }

    private void showMessageServerError() {
        JOptionPane.showMessageDialog(frame, "Błąd serwera");
    }

    protected void start() {
        setWindowVisibile();
    }

    @Override
    public void setWindowTitle() {
        frame.setTitle("Cook Book");
    }

    @Override
    public void addMainComponent() {
        frame.add(cookBookPanel);
    }

    @Override
    public void setWindowSize() {
        frame.setSize(800, 600);
    }

    @Override
    public void setWindowLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void setWindowDefaultCloseOperation() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void setWindowVisibile() {
        frame.setVisible(true);
    }

    @Override
    public void setWindowPack() {
        frame.pack();
    }

    @Override
    public void setWindowLocationRealtivity() {
        frame.setLocationByPlatform(true);
    }

}

