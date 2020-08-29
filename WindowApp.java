import javax.swing.*;
import java.io.IOException;

public class WindowApp {

    private final JFrame frame;
    private JPanel cookBookPanel;
    private JButton submitSearchProductButton;
    public String productToFind;
    private JButton submitSearchRecipeButton;
    public String recipeToFind;
    private JFormattedTextField searchRecipeField;
    private JFormattedTextField searchProductField;
    private JButton secondBreakfastIdeasButton;
    private JButton lunchIdeasButton;
    private JButton dinnerIdeasButton;
    private JButton supperIdeasButton;
    private JButton breakfastIdeasButton;
    private JList list1;
    private ProductProvider productProvider;

    public WindowApp() throws IOException {
        frame = new JFrame("Cook Book");
        frame.add(cookBookPanel);
        frame.pack();
        frame.setSize(1000, 500);
        frame.setLocationByPlatform(true);
        setLookAndFeel();

        productProvider = new ProductProvider();

        submitSearchProductButton.addActionListener(actionEvent ->
         JOptionPane.showMessageDialog(frame, productProvider.findProduct(searchProductField.getText())));
    }

    protected void start() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
    }

}

