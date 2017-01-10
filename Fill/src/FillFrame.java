package cglosser;

import javax.swing.JFrame;

/**
    Primary window management
 */

public class FillFrame extends JFrame {

    private FillPanel fp;

    // Implementing a 16:9 ratio.
    private static final int WIDTH = 640;
    private static final int HEIGHT = 360;

    public FillFrame() {
        System.out.println("GameFrame Constructor");

        fp = new FillPanel();
        add(fp);

        setSize(WIDTH, HEIGHT);
        setTitle ("Fill Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
