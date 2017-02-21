
package cglosser;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
    Extendings the Swing JPanel class with My game specific settings.  Also,
    implementing ActionListener to register and handle mouse click events.
    This class contains the primary game loop, handled as a collaboration
    between the actionPerformed function and the doDrawing function. 

    @author Courtney Glosser
 */

public class MyPanel extends JPanel implements ActionListener{
    private String gameState; // welcome, active, win, lose
    private ButtonManager bm;
    private Screen gameScreen;
    private int count;
    private int seconds;

    public MyPanel() {

        gameState = "welcome";
        bm = new ButtonManager();
        gameScreen = new Screen();

        addMouseListener(new MAdapter());

        setBackground(Color.black);
        setFocusable(true);

        count = 0;
        seconds = 0;

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.drawString("Count: " + count, 20, 20);
        g2d.drawString("Seconds: " + seconds, 20, 50);
        g2d.drawString("FPS: " + count / seconds, 200, 20);

        if (gameState == "active") {
            bm.drawBoardButtons(g2d);
        }
        else if (gameState == "welcome") {
            // Draw a welcome screen
            gameScreen.drawWelcome(g2d);
        }
        else if (gameState == "win") {
            // Draw a win screen
            gameScreen.drawWin(g2d);
        }
        else if (gameState == "lose") {
            // Draw a lose screen
            gameScreen.drawLose(g2d);
        }

    }

    public void update(int updateSeconds) {
        System.out.println("Updating the Panel!");
        seconds = updateSeconds;
        count++;
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    /**
        Class extends the MouseAdapter abstract class.  This class will
        register mousePressed events and utilize helper classes to execute
        appropriate game responses.

        @author Courtney Glosser
     */

    private class MAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            if (gameState == "active") {
            }
            if (bm.checkStart(gameState, e.getX(), e.getY())) {
                gameState = "active";
                repaint();
            }
            if (bm.checkExit(gameState, e.getX(), e.getY())) {
                System.exit(0);
            }
        }

    }
}
