
package cglosser;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

/**
    Extendings the Swing JPanel class with My game specific settings.  Also,
    implementing ActionListener to register and handle mouse click events.
    This class contains the primary game loop, handled as a collaboration
    between the actionPerformed function and the doDrawing function. 

    @author Courtney Glosser
 */

public class MyPanel extends JPanel implements KeyListener, ActionListener{
    private String gameState; // welcome, active, win, lose
    private ButtonManager bm;
    private Screen gameScreen;
    private Board board;

    private int count;
    private int seconds;

    public MyPanel() {

        gameState = "welcome";
        bm = new ButtonManager();
        gameScreen = new Screen();
        board = new Board();

        addMouseListener(new MAdapter());
        addKeyListener(this);

        setBackground(Color.black);
        setFocusable(true);

        count = seconds = 0;

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.drawString("Count: " + count, 20, 20);
        g2d.drawString("Seconds: " + seconds, 20, 50);
        if (seconds > 0) {
            g2d.drawString("FPS: " + count / seconds, 200, 20);
        }
        
        if (gameState == "active") {
            bm.drawBoardButtons(g2d);
            board.updateBoard(count);
            board.drawBoard(g2d);
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
        if (seconds != updateSeconds) {
            // ASSERT:  ticked over a second:
            seconds++;
        }
        else {
        }
        count++;
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
    }

    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == 40) {
            // Down
            board.playerDown();
        }
        if (e.getKeyCode() == 38) {
            // Up
            board.playerUp();
        }
    }

    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
    }

    /**
        Class extends the MouseAdapter abstract class.  This class will
        register mousePressed events and utilize helper classes to execute
        appropriate game responses.

        @author Courtney Glosser
     */

    private class MAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            if (bm.checkStart(gameState, e.getX(), e.getY())) {
                gameState = "active";
                board.restartBoard();
                repaint();
            }
            if (bm.checkExit(gameState, e.getX(), e.getY())) {
                System.exit(0);
            }
        }

    }
}
