
package cglosser;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Dimension;

/**
    Extendings the Swing JPanel class with My game specific settings.  Also,
    implementing ActionListener to register and handle mouse click events.
    This class contains the primary game loop, handled as a collaboration
    between the actionPerformed function and the doDrawing function. 

    @author Courtney Glosser
 */

public class MyPanel extends JPanel implements ActionListener, Runnable{
    private String gameState; // welcome, active, win, lose
    private int count;
    private int seconds;

    private static final int PWIDTH = 500;
    private static final int PHEIGHT = 400;

    private Thread animator;
    private volatile boolean running = false;

    private volatile boolean gameOver = false;

    public MyPanel() {

        setBackground(Color.black);
        setPreferredSize (new Dimension(PWIDTH, PHEIGHT) );

/**
        gameState = "welcome";
        addMouseListener(new MAdapter());

        setBackground(Color.black);
        setFocusable(true);

        count = seconds = 0;

        repaint();
/**/
    }

    /**
        Wait for the jPanel to be added to the JFrame / Applet before starting
        the game.
     */
    public void addNotify() {
        super.addNotify();
        startGame();
    }

    private void startGame() {
        if (animator == null || !running) {
            animator = new Thread(this);
            animator.start();
        }
    }

    public void stopGame() {
        running = false;
    }

    public void run() {
        running = true;
        while(running) {
            gameUpdate();
            gameRender();
            repaint();

            try {
                Thread.sleep(20);
            }
            catch(InterruptedException ex) {

            }
        }

        System.exit(0);
    }

    private void gameUpdate() {
        if (!gameOver) {
        }
    }

    private void gameRender() {

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
        if (seconds > 0) {
            g2d.drawString("FPS: " + count / seconds, 200, 20);
        }
        
        if (gameState == "active") {
        }
        else if (gameState == "welcome") {
            // Draw a welcome screen
//            gameScreen.drawWelcome(g2d);
        }
        else if (gameState == "win") {
            // Draw a win screen
//            gameScreen.drawWin(g2d);
        }
        else if (gameState == "lose") {
            // Draw a lose screen
//            gameScreen.drawLose(g2d);
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
/**
            if (bm.checkStart(gameState, e.getX(), e.getY())) {
                gameState = "active";
                repaint();
            }
            if (bm.checkExit(gameState, e.getX(), e.getY())) {
                System.exit(0);
            }
/**/
        }

    }
}
