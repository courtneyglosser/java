package cglosser;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JTextField;


/**
    Extending the swing JFrame class with My game specific settings.

    @author Courtney Glosser
 */

public class WormChase extends JFrame implements WindowListener {

    private WormPanel wp;

    // Implementing a 16:9 ratio.
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private boolean done = false;
    private int safety = 0;

    private JTextField jtfBox;
    private JTextField jtfTime;

    public WormChase(long period) {

        wp = new WormPanel();
        add(wp);

        setSize(WIDTH, HEIGHT);
        setTitle ("My Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);

        jtfBox = new JTextField();
        jtfTime = new JTextField();

    }

    public void setBoxNumber (int no) {
        jtfBox.setText("Boxes used: " + no);
    }

    public void setTimeSpent(long t) {
        jtfTime.setText("Time Spent: " + t);
    }

    public void windowActivated(WindowEvent e) {
        wp.resumeGame();
    }

    public void windowDeactivated(WindowEvent e) {
        wp.pauseGame();
    }

    public void windowDeiconified(WindowEvent e) {
        wp.resumeGame();
    }

    public void windowIconified(WindowEvent e) {
        wp.pauseGame();
    }

    public void windowClosing(WindowEvent e) {
        wp.stopGame();
    }

    public void windowClosed(WindowEvent e) {
        wp.stopGame();
    }

    public void windowOpened(WindowEvent e) {
    }

}
