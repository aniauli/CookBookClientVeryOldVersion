import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ProductWindow implements Windows {

    private JFrame frame;
    private JPanel productPanel;
    private JTextArea CaloriesPer100GramsTextArea;
    private JTextArea MainIngredientTextArea;
    private JTextArea CaloriesPerServingTextArea;
    private JTextArea GramsPerServingTextArea;
    private JPanel MainIngredient;
    private JPanel CaloriesPer100GramsPanel;
    private JPanel GramsPerServingPanel;
    private JPanel CaloriesPerServingPanel;


    public ProductWindow() {
        frame = new JFrame();
        setWindowTitle();
        addMainComponent();
        setWindowPack();
        setWindowSize();
        setWindowLocationRealtivity();
        setWindowLookAndFeel();
        setWindowDefaultCloseOperation();
    }

    public void start(String productName, String caloriesPer100Grams, String gramsPerServing, String mainIngredient){
        setMainComponentTitle(productName);
        CaloriesPer100GramsTextArea.setText(caloriesPer100Grams);
        GramsPerServingTextArea.setText(gramsPerServing);
        CaloriesPerServingTextArea.setText(caloriesPerServing(caloriesPer100Grams, gramsPerServing));
        MainIngredientTextArea.setText(mainIngredient);
        setWindowVisibile();
    }

    private String caloriesPerServing(String caloriesPer100Grams, String gramsPerServing) {
        Double caloriesPerServing = ((Double.parseDouble(caloriesPer100Grams) * Double.parseDouble(gramsPerServing) / 100.0));
        return String.format("%s", caloriesPerServing);
    }

    @Override
    public void setWindowTitle() {
        frame.setTitle("Produkt Info");
    }

    @Override
    public void addMainComponent() {
        frame.add(productPanel);
    }

    @Override
    public void setWindowPack() {
        frame.pack();
    }

    @Override
    public void setWindowSize() {
        frame.setSize(500, 450);
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
    public void setWindowLocationRealtivity() {
        frame.setLocationByPlatform(true);
    }

    @Override
    public void setWindowDefaultCloseOperation() {
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void setWindowVisibile() {
        frame.setVisible(true);
    }

    private void setMainComponentTitle(String productName) {
        TitledBorder title;
        title = BorderFactory.createTitledBorder(productName);
        title.setTitleFont(new Font("Segoe Print", Font.BOLD, 36));
        productPanel.setBorder(title);
    }
}
