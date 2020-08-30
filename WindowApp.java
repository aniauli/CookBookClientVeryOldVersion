import javax.swing.*;
import java.io.IOException;

public class WindowApp implements Windows {

    private JFrame frame;
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
    private ProductProvider productProvider;
    private ProductWindow productWindow;

    public WindowApp() {
        frame = new JFrame();
        setWindowTitle();
        addMainComponent();
        setWindowPack();
        setWindowSize();
        setWindowLocationRealtivity();
        setWindowLookAndFeel();
        setWindowDefaultCloseOperation();

        //   productProvider = new ProductProvider();
        productWindow = new ProductWindow();

        submitSearchProductButton.addActionListener(actionEvent ->
                productWindow.start());
    }

    protected void start() {
        setWindowVisibile();
    }

    public static void main(String[] args) {
        WindowApp windowApp = new WindowApp();
        windowApp.start();
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
        frame.setSize(700, 600);
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

