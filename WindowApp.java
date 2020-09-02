import javax.swing.*;

public class WindowApp implements Windows {

    private JFrame frame;
    private JPanel cookBookPanel;

    private JButton submitSearchProductButton;
    private JFormattedTextField searchProductField;
    private JButton submitSearchRecipeButton;
    private JFormattedTextField searchRecipeField;
    private JButton secondBreakfastIdeasButton;
    private JButton lunchIdeasButton;
    private JButton dinnerIdeasButton;
    private JButton supperIdeasButton;
    private JButton breakfastIdeasButton;

    private ProductProvider productProvider;
    private ProductWindow productWindow;
    private EditableProductWindow editableProductWindow;
    private MealChooser mealChooser;

    private String searchingProduct;

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
        productWindow = new ProductWindow();
        editableProductWindow = new EditableProductWindow();
        mealChooser = new MealChooser();

        submitSearchProductButton.addActionListener(actionEvent -> {
            searchingProduct = searchProductField.getText();
            if (searchingProduct.length() > 0) {
                findProduct(searchingProduct);
            } else {
                JOptionPane.showMessageDialog(frame, "Nie wpisałeś produktu");
            }
        });

        breakfastIdeasButton.addActionListener(actionEvent -> {
            String[] breakfasts = {"Jajecznica", "Omlet", "Owsianka z bananami"};
            mealChooser.start("Śniadania", breakfasts, new ImageIcon("src/Images/Breakfast.jpg"));
        });

        dinnerIdeasButton.addActionListener(actionEvent -> {
        });
    }

    private void findProduct(String productToFind) {
        String answer = productProvider.findItem(productToFind);
        if(answer.equals("There's no such product")){
            askAboutAddingNewProduct();
        }
        else if(answer.equals("SQL Error") || answer.equals("Server Error")){
            showMessageServerError();
        } else {
            showProductInfo(answer);
        }
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

    private void askAboutAddingNewProduct() {
        Integer clientChoice;
        Object[] options = {"Tak", "Nie"};
        clientChoice = JOptionPane.showOptionDialog(frame, "Podanego produktu nie ma w bazie. Czy chciałbyś dodać " +
                        "ten produkt?", "Dodawanie produktu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[1]);
        if(clientChoice == 0){
            editableProductWindow.start(searchingProduct);
        }
    }

    private void showProductInfo(String serverAnswer) {
        String[] splittedAnswer = serverAnswer.split(";", 5);
        if(splittedAnswer.length != 4){
            showMessageServerError();
        }
        else {
            productWindow.start(splittedAnswer[0], splittedAnswer[1], splittedAnswer[2], splittedAnswer[3]);
        }
    }

    private void showMessageServerError() {
        JOptionPane.showMessageDialog(frame, "Błąd serwera");
    }

}

