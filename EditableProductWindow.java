import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class EditableProductWindow implements Windows {
    private JFrame frame;
    private JPanel productPanel;
    private JPanel MainIngredient;
    private JTextArea MainIngredientTextArea;
    private JPanel CaloriesPer100GramsPanel;
    private JTextArea CaloriesPer100GramsTextArea;
    private JTextArea CaloriesPerServingTextArea;
    private JPanel GramsPerServingPanel;
    private JTextArea GramsPerServingTextArea;
    private JButton submitButton;
    private JTextArea ProductNameTextArea;
    private ProductProvider productProvider;

    private String editedCalories;
    private String editedProductName;
    private String editedGrams;
    private String editedIngredient;

    public EditableProductWindow() {
        frame = new JFrame();
        setWindowTitle();
        addMainComponent();
        setWindowPack();
        setWindowSize();
        setWindowLocationRealtivity();
        setWindowLookAndFeel();
        setWindowDefaultCloseOperation();

        productProvider = new ProductProvider();

        submitButton.addActionListener(ActionEvent -> {

            if (!AreSomeTextAreasEmpty()) {
                JOptionPane.showMessageDialog(frame, sendProductToServerAndReceiveAnswer());

                CaloriesPer100GramsTextArea.setText("");
                GramsPerServingTextArea.setText("");
                MainIngredientTextArea.setText("");

                frame.dispose();
            } else{
                JOptionPane.showMessageDialog(frame, "Nie wypełniłeś wszystkich pól");
            }
        });
    }

    public void start(String productName){
        ProductNameTextArea.setText(productName);
        setMainComponentTitle(productName);
        setWindowVisibile();
    }

    private String sendProductToServerAndReceiveAnswer() {
        String toSend = ProductNameTextArea.getText() + ";" + CaloriesPer100GramsTextArea.getText() + ";" +
                GramsPerServingTextArea.getText() + ";" + MainIngredientTextArea.getText();
        return productProvider.addItem(toSend);
    }

    private boolean AreSomeTextAreasEmpty() {
        return isEmpty(ProductNameTextArea) || isEmpty(CaloriesPer100GramsTextArea) ||
                isEmpty(GramsPerServingTextArea) || isEmpty(MainIngredientTextArea);
    }

    private boolean isEmpty(JTextArea textArea) {
        return (textArea.getText().length() <= 0);
    }

    @Override
    public void setWindowTitle() {
        frame.setTitle("Dodawanie produktu");
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
        frame.setSize(650, 450);
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
        productPanel.setBorder(title);
    }
}
