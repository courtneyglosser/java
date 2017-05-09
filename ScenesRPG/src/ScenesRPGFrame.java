package cglosser;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
    Extending the swing JFrame class with Fill game specific settings.

    @author Courtney Glosser
 */

public class ScenesRPGFrame extends JFrame implements WindowListener {

    private ScenesRPGPanel sp;

    // Implementing a 16:9 ratio.
    private static final int WIDTH = 640;
    private static final int HEIGHT = 360;

    public ScenesRPGFrame() {

        sp = new ScenesRPGPanel();
        add(sp);

        setSize(WIDTH, HEIGHT);
        setTitle ("Fill Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void windowActivated(WindowEvent e) {
        sp.resumeGame();
    }

    public void windowDeactivated(WindowEvent e) {
        sp.pauseGame();
    }

    public void windowDeiconified(WindowEvent e) {
        sp.resumeGame();
    }

    public void windowIconified(WindowEvent e) {
        sp.pauseGame();
    }

    public void windowClosing(WindowEvent e) {
        sp.stopGame();
    }

    public void windowClosed(WindowEvent e) {
        sp.stopGame();
    }

    public void windowOpened(WindowEvent e) {
    }
}
