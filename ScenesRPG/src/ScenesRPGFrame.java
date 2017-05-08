package cglosser;

import javax.swing.JFrame;

/**
    Extending the swing JFrame class with Fill game specific settings.

    @author Courtney Glosser
 */

public class ScenesRPGFrame extends JFrame {

    private ScenesRPGPanel fp;

    // Implementing a 16:9 ratio.
    private static final int WIDTH = 640;
    private static final int HEIGHT = 360;

    public ScenesRPGFrame() {

        fp = new ScenesRPGPanel();
        add(fp);

        setSize(WIDTH, HEIGHT);
        setTitle ("Fill Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
