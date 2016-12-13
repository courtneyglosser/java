
import java.awt.EventQueue;
import javax.swing.JFrame;


/**
    Simple Snake game Frame
 */

public class SnakeFrame extends JFrame {
    private SnakePanel sp;

    public SnakeFrame() {
        System.out.println("Hello World Snake!");

        sp = new SnakePanel();

        add(sp);

        setSize(650, 400);
        setTitle("Simple Snake Game!");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    public void render() {
        sp.repaint();
    }
}
