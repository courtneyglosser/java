package cglosser;

import java.awt.Container;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
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


    public WormChase(int period) {
        makeGUI(period);

        addWindowListener( this );
        pack();
        setResizable(false);
        setVisible(true);

        setSize(WIDTH, HEIGHT);
        setTitle ("Worm Chase Game");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
    }

    private void makeGUI(int period)
    {
        Container c = getContentPane();    // default BorderLayout used

        wp = new WormPanel(this, period);
        c.add(wp, "Center");

        JPanel ctrls = new JPanel();   // a row of textfields
        ctrls.setLayout( new BoxLayout(ctrls, BoxLayout.X_AXIS));

        jtfBox = new JTextField("Boxes used: 0");
        jtfBox.setEditable(false);
        ctrls.add(jtfBox);

        jtfTime = new JTextField("Time Spent: 0 secs");
        jtfTime.setEditable(false);
        ctrls.add(jtfTime);

        c.add(ctrls, "South");
    }  // end of makeGUI()



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
