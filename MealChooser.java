import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MealChooser implements Windows {

    private JFrame frame;
    private JPanel mealChooserPanel;
    private JList<String> recipesList;
    private JPanel recipePanel;
    private JPanel info;
    private JPanel calories;
    private JPanel ingredients;
    private JPanel picture;
    private JPanel recipe;
    private JTextArea caloriesTextArea;
    private JTextArea instructionsTextArea;
    private JScrollPane scrollPaneForRecipesList;
    private JList ingredientsList;
    private JPanel listPanel;
    private JLabel pictureLabel;

    public MealChooser() {
        frame = new JFrame();
        setWindowTitle();
        addMainComponent();
        setWindowPack();
        setWindowSize();
        setWindowLocationRealtivity();
        setWindowLookAndFeel();
        setWindowDefaultCloseOperation();
    }

    public void start(String mealName, String[] mealList, Icon icon){
        setMainComponentTitle(mealName);
        recipesList.setListData(mealList);
        pictureLabel.setIcon(icon);
        setWindowVisibile();
    }

    private ImageIcon loadIcon(String path) {
        java.net.URL imgPath = getClass().getResource(path);
        if (imgPath != null) {
            return new ImageIcon(imgPath);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public void setWindowTitle() {
        frame.setTitle("Pomysł na ...");
    }

    @Override
    public void addMainComponent() {
        frame.add(mealChooserPanel);
    }

    @Override
    public void setWindowPack() {
        frame.pack();
    }

    @Override
    public void setWindowSize() {
        frame.setSize(750, 500);
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void setWindowVisibile() {
        frame.setVisible(true);
    }

    private void setMainComponentTitle(String productName) {
        TitledBorder title;
        title = BorderFactory.createTitledBorder(productName);
        title.setTitleFont(new Font("Segoe Print", Font.BOLD, 36));
        mealChooserPanel.setBorder(title);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
