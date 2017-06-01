
package cglosser;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
    Extendings the Swing JPanel class with ScenesRPG game specific settings.

    @author Courtney Glosser
 */

public class ScenesRPGPanel extends JPanel implements ActionListener, Runnable{
    public String gameState; // welcome, load, main, info, win, lose
    private ButtonManager bm;
    private Screen gameScreen;
    private Stats gameStats;

    private int period = 1000/100; // ms / FPS;

    int whichCity = 0;

    private Thread animator;

    private volatile boolean running = false;
    private volatile boolean gameOver = false;
    private volatile boolean isPaused = false;

    private Graphics dbg;
    private Image dbImage = null;

    public ScenesRPGPanel() {

        gameState = "welcome";
        bm = new ButtonManager(this);
        gameScreen = new Screen(this);
        gameStats = new Stats();

        setBackground(Color.black);
        setFocusable(true);
        requestFocus();
        readyForTermination();

        this.setLayout(null);

        repaint();
    }

    public void setGameState(String state) {
        gameState = state;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        long beforeTime, timeDiff, sleepTime;
        beforeTime = System.currentTimeMillis();

        running = true;
        while(running) {
            gameUpdate();
            gameRender();
            paintScreen();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleepTime = period - timeDiff;

            if (sleepTime <= 0) {// loop too long
                System.out.println("lag");
                sleepTime = 5; // Must still sleep to free CPU
            }

            try {
                Thread.sleep(sleepTime);
            }
            catch(InterruptedException ex) {

            }

            beforeTime = System.currentTimeMillis();

            gameStats.storeStats();
            requestFocus();
//            System.out.println("Game State: " + gameState);
        }

        gameStats.printStats();

        System.exit(0);
    }

    public void gameUpdate() {}
    public void gameRender() {}
    // TODO:  Update to take control of this, rather than just using queue
    public void paintScreen() {
        repaint();
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

    public void pauseGame() {
        isPaused = true;
    }

    public void resumeGame() {
        isPaused = false;
    }

    public void stopGame() {
        running = false;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (gameState == "active") {
            gameScreen.drawActive(g2d);
        }
        else if (gameState == "welcome") {
            // Draw a welcome screen
            gameScreen.drawWelcome(g2d);
        }
        else if (gameState == "load") {
            gameScreen.drawLoad(g2d);
        }
        else if (gameState == "save") {
            gameScreen.drawSave(g2d);
        }
        else if (gameState == "info") {
            gameScreen.drawInfo(g2d);
        }
        else if (gameState == "win") {
            // Draw a win screen
            gameScreen.drawWin(g2d);
        }
        else if (gameState == "lose") {
            // Draw a lose screen
            gameScreen.drawLose(g2d);
        }
        else if (gameState == "city") {
            gameScreen.drawCity(g2d, whichCity);
        }
        else if (gameState == "inn") {
            gameScreen.drawInn(g2d);
        }
        else if (gameState == "store") {
            gameScreen.drawStore(g2d);
        }
        else if (gameState == "mission") {
            gameScreen.drawMission(g2d);
        }
        else if (gameState == "adventure") {
            gameScreen.drawAdventure(g2d);
        }
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private class KAdapter extends KeyAdapter {
        // Listen for esc, q, end, and ctrl-c
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if ((keyCode == KeyEvent.VK_ESCAPE) ||
                (keyCode == KeyEvent.VK_Q) ||
                (keyCode == KeyEvent.VK_END) ||
                ((keyCode == KeyEvent.VK_C) && e.isControlDown()) ) {

                running = false;
            }
        }

    }

    private void readyForTermination() {
        addKeyListener( new KAdapter());
    }


}
