
import javax.swing.JFrame;

/**
    Primary window management
 */

public class FillFrame extends JFrame {

    private FillPanel fp;

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
