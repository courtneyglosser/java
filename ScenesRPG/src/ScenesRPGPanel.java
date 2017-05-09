
package cglosser;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
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
    Extendings the Swing JPanel class with Fill game specific settings.  Also,
    implementing ActionListener to register and handle mouse click events.
    This class contains the primary Fill game loop, handled as a collaboration
    between the actionPerformed function and the doDrawing function.  The game
    loop is not timer based, and simply awaits user input (actionPerformed),
    calculates the user's impact on the overall game state (within the private
    MAdapter class below), and provides the user an updated game state drawing
    as feedback (doDrawing).

    @author Courtney Glosser
 */

public class ScenesRPGPanel extends JPanel implements ActionListener, Runnable{
    private String gameState; // welcome, active, win, lose
    private ButtonManager bm;
    private Screen gameScreen;
    private Stats gameStats;


    private int count;
    private int seconds;
    private int period = 1000/100; // ms / FPS;

    private Thread animator;

    private volatile boolean running = false;
    private volatile boolean gameOver = false;
    private volatile boolean isPaused = false;

    private int score = 0;

    private Graphics dbg;
    private Image dbImage = null;

    public ScenesRPGPanel() {

        gameState = "welcome";
        bm = new ButtonManager();
        gameScreen = new Screen();
        gameStats = new Stats();

        JButton myBtn = new JButton("My Button");
        myBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action: " + e);
            }
        });
        this.add(myBtn);

        JButton startBtn = new JButton ("Start");
        startBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action: " + e);

                gameState = "active";
            }
        });
        this.add(startBtn);

        JButton exitBtn = new JButton ("Exit");
        exitBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action: " + e);

                running = false;
            }
        });
        this.add(exitBtn);

        JButton loseBtn = new JButton ("Lose");
        loseBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lose: " + e);
                gameState = "lose";
            }
        });
        this.add(loseBtn);

        JButton winBtn = new JButton ("Win");
        winBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Win: " + e);
                gameState = "win";
            }
        });
        this.add(winBtn);

        addMouseListener(new MAdapter());

        setBackground(Color.black);
        setFocusable(true);
        requestFocus();
        readyForTermination();

        repaint();


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
/**
                if (bm.clickedColoredButton( e.getX(), e.getY() ) == true) {

                    Button clickBtn = bm.registerClick(e.getX(), e.getY());
                    gameBoard.handleClick(clickBtn.getColor());
                    if (gameBoard.checkWin()) {
                        // ASSERT:  Winner!
                        gameState = "win";
                    }
                    else if (gameBoard.checkLose()) {
                        gameState = "lose";
                    }
                    repaint();
                }
                else {
                }
/**/
            }
            if (bm.checkStart(gameState, e.getX(), e.getY())) {
                gameState = "active";
//                gameBoard = new Board();
                repaint();
            }
            if (bm.checkExit(gameState, e.getX(), e.getY())) {
                running = false;
            }
        }

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
