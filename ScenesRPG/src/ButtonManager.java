
package cglosser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
    Manages the various buttons with which the user can interact:

    @author Courtney Glosser
 */

public class ButtonManager {

    public JButton startBtn, loadBtn, exitBtn, infoBtn, loseBtn, winBtn;

    String gameState;
    boolean running = true;

    public ButtonManager(ScenesRPGPanel jp) {
        init();
    }

    private void init() {

        startBtn = new JButton ("Start");
        startBtn.addActionListener( new StartListener());

        loadBtn = new JButton ("Load");
        loadBtn.addActionListener( new LoadListener());

        infoBtn = new JButton ("Info");
        infoBtn.addActionListener( new InfoListener());

        exitBtn = new JButton ("Exit");
        exitBtn.addActionListener( new ExitListener());

        loseBtn = new JButton ("Lose");
        loseBtn.addActionListener( new LoseListener());

        winBtn = new JButton ("Win");
        winBtn.addActionListener( new WinListener());
    }

    public JButton getStartBtn() { return startBtn; }

    public void drawWinButtons() {
    }

    public void drawLoseButtons() {
    }

    public class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Document? ");
        }
    }

    public class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Action: " + e);


            gameState = "load";
        }
    }

    public class InfoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Action: " + e);

            gameState = "info";
        }
    }

    public class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Action: " + e);

            running = false;
        }
    }

    public class LoseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Lose: " + e);
            gameState = "lose";
        }
    }

    public class WinListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Win: " + e);
            gameState = "win";
        }
    }
}
