package cglosser;

import javax.swing.JFrame;

/**
    Extending the swing JFrame class with My game specific settings.

    @author Courtney Glosser
 */

public class MyFrame extends JFrame {

    private MyPanel fp;

    // Implementing a 16:9 ratio.
    private static final int WIDTH = 640;
    private static final int HEIGHT = 360;

    public MyFrame() {

        fp = new MyPanel();
        add(fp);

        setSize(WIDTH, HEIGHT);
        setTitle ("My Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
