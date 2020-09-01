import javax.swing.*;

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
            editedCalories = CaloriesPer100GramsTextArea.getText();
            editedProductName = ProductNameTextArea.getText();
            editedGrams = GramsPerServingTextArea.getText();
            editedIngredient = MainIngredientTextArea.getText();

            if(!AreSomeTextAreasEmpty()) {
                if(sendProductToServerAndReceiveAnswer().equals("Product added")) {
                    JOptionPane.showMessageDialog(frame, "Pomyślnie dodano produkt");
                    frame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Błąd! Nie udało się dodać produktu. Sprawdź, czy podałeś prawidłowe wartości.");
                }
            }
            else{
                JOptionPane.showMessageDialog(frame, "Nie wypełniłeś wszystkich pól");
            }
        });
    }

    public void start(String productName){
        ProductNameTextArea.setText(productName);
        setWindowVisibile();
    }

    private String sendProductToServerAndReceiveAnswer() {
        String toSend = editedProductName + ";" + editedCalories + ";" + editedGrams + ";" + editedIngredient;
        return productProvider.addItem(toSend);
    }

    private boolean AreSomeTextAreasEmpty() {
        return editedProductName.length() <= 0 || editedCalories.length() <=0 || editedGrams.length() <= 0 || editedIngredient.length() <= 0;
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
        frame.setSize(450, 400);
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
}
