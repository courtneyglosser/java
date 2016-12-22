
import javax.swing.JFrame;

/**
    Primary window management
 */

public class FillFrame extends JFrame {

    private FillPanel fp;

    public FillFrame() {
        System.out.println("GameFrame Constructor");

        fp = new FillPanel();
        add(fp);

        setSize(650, 400);
        setTitle ("Fill Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);

    }
}
