
import javax.swing.JFrame;

/**
    Primary window management
 */

public class FillFrame extends JFrame {

    public FillFrame() {
        System.out.println("GameFrame Constructor");

        setSize(650, 400);
        setTitle ("Fill Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

    }
}
