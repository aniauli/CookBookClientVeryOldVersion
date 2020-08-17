import javax.swing.*;
import java.awt.*;

public class WindowApp {

    private JButton startButton;
    public final JFrame frame;
    private JPanel cookBookPanel;
    private JButton submitSearchProductButton;
    private JButton submitSearchRecipeButton;
    private JFormattedTextField searchRecipeField;
    private JFormattedTextField searchProductField;
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

        startButton.addActionListener(actionEvent -> {
            try {
                Object[] options = {"Yes", "No"};
                JOptionPane.showOptionDialog(frame, "Do you want to add a recipe?", "Adding a recipe",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                        options[1]);
            } catch (HeadlessException e) {
                e.printStackTrace();
            }
        });
    }

    public void start() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }

    public static void main(String[] args) {
        WindowApp windowApp = new WindowApp();
        windowApp.start();
    }

}

