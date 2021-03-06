
package cglosser;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
    Extendings the Swing JPanel class with ScenesRPG game specific settings.

    @author Courtney Glosser
 */

public class ScenesRPGPanel extends JPanel implements Runnable{
    public String gameState; // welcome, load, main, info, win, lose
    private Screen gameScreen;
    private Stats gameStats;

    private int period = 1000/100; // ms / FPS;

    int whichCity = 0;

    private Thread animator;

    private volatile boolean running = false;
    private volatile boolean gameOver = false;
    private volatile boolean isPaused = false;

    private Player currPlayer;


    public ScenesRPGPanel() {

        gameState = "welcome";
        gameScreen = new Screen(this);
        gameStats = new Stats();

        setBackground(Color.black);
        setFocusable(true);
        requestFocus();
        readyForTermination();

        this.setLayout(null);

        currPlayer = new Player();

        gameRender();

    }

    public void setGameState(String state) { gameState = state;}
    public void setRunning(boolean running) { this.running = running;}
    public void setCharName(String name) {currPlayer.setName(name);}

    public String getGameState() { return gameState; }
    public boolean getRunning() {return running; }
    public String getCharName() {return currPlayer.getName();}


    public void run() {
        long beforeTime, timeDiff, sleepTime;
        beforeTime = System.currentTimeMillis();

        running = true;
        while(running) {
            gameUpdate();
            gameRender();

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
    public void gameRender() {
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

        if (gameState == "city_select") {
            gameScreen.drawCitySelect(g2d);
        }
        else if (gameState == "character") {
            gameScreen.drawCharacter(g2d);
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

    private class TerminateAdapter extends KeyAdapter {
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
        addKeyListener( new TerminateAdapter());
    }


}
