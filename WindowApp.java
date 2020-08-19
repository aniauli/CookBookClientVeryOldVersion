import javax.swing.*;
import java.awt.*;

public class WindowApp {

    private final JFrame frame;
    private JPanel cookBookPanel;
    public JButton submitSearchProductButton;
    private JButton submitSearchRecipeButton;
    public JFormattedTextField searchRecipeField;
    public JFormattedTextField searchProductField;
    private JButton secondBreakfastIdeasButton;
    private JButton lunchIdeasButton;
    private JButton dinnerIdeasButton;
    private JButton supperIdeasButton;
    private JButton breakfastIdeasButton;
    private JList list1;

    public WindowApp() {
        frame = new JFrame("Cook Book");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(cookBookPanel);
        frame.pack();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public void start() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        WindowApp windowApp = new WindowApp();
        windowApp.start();
    }

}

