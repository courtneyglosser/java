
package cglosser;

import javax.swing.JPanel;
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
import java.text.DecimalFormat;

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

    // record stats every 1 second (roughly)
    private static int MAX_STATS_INTERVAL = 1000;
    // number of FPS values stored to get an average
    private static int NUM_FPS = 10;

    // used for gathering statistics
    private int statsInterval = 0;    // in ms
    private long prevStatsTime = 0;
    private int totalElapsedTime = 0;
    private int gameStartTime;
    private long timeSpentInGame = 0;    // in seconds

    private int frameCount = 0;
    private double fpsStore[];
    private int statsCount = 0;
    private double averageFPS = 0.0;

    private int framesSkipped = 0;
    private int totalFramesSkipped = 0;
    private double upsStore[];
    private double averageUPS = 0.0;

    private DecimalFormat df = new DecimalFormat("0.##");  // 2 dp
    private DecimalFormat timedf = new DecimalFormat("0.####");  // 4 dp

    private int count;
    private int seconds;
    private int period = 1000/100; // ms / FPS;

    private Thread animator;

    private volatile boolean running = false;
    private volatile boolean gameOver = false;
    private volatile boolean isPaused = false;

    private Font font;
    private FontMetrics metrics;
    private int score = 0;

    private Graphics dbg;
    private Image dbImage = null;

    public ScenesRPGPanel() {

        gameState = "welcome";
        bm = new ButtonManager();
        gameScreen = new Screen();

        addMouseListener(new MAdapter());

        setBackground(Color.black);
        setFocusable(true);
        requestFocus();
        readyForTermination();

        repaint();


        // Timing elements
        fpsStore = new double[NUM_FPS];
        upsStore = new double[NUM_FPS];

        for (int i=0; i < NUM_FPS; i++) {
            fpsStore[i] = 0.0;
            upsStore[i] = 0.0;
        }
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

            storeStats();
        }

        printStats();

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
                System.exit(0);
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



    /**
        The statistics:
        - the summed periods for all the iterations in this interval
        (period is the amount of time a single frame iteration should take),
        the actual elapsed time in this interval,
        the error between these two numbers;

        - the total frame count, which is the total number of calls to run();

        - the frames skipped in this interval, the total number of frames
        skipped. A frame skip is a game update without a corresponding render;

        - the FPS (frames/sec) and UPS (updates/sec) for this interval,
        the average FPS & UPS over the last NUM_FPSs intervals.

        The data is collected every MAX_STATS_INTERVAL  (1 sec).
     */
    private void storeStats()
    {
        frameCount++;
        statsInterval += period;

        if (prevStatsTime == 0) {
            prevStatsTime = System.currentTimeMillis();
        }

        // record stats every MAX_STATS_INTERVAL
        if (statsInterval >= MAX_STATS_INTERVAL) {
            long timeNow = System.currentTimeMillis();
            timeSpentInGame = (timeNow - gameStartTime);

            // time since last stats collection
            long realElapsedTime = timeNow - prevStatsTime;
            totalElapsedTime += realElapsedTime / 1000;

            double timingError =
            ((double)(realElapsedTime - statsInterval) / statsInterval) * 100.0;

            totalFramesSkipped += framesSkipped;

            // calculate the latest FPS and UPS
            double actualFPS = 0;
            double actualUPS = 0;
            if (totalElapsedTime > 0) {
                actualFPS = ((double)frameCount / totalElapsedTime);
                actualUPS = ((double)(frameCount + totalFramesSkipped) / totalElapsedTime);
            }

            // store the latest FPS and UPS
            fpsStore[ (int)statsCount%NUM_FPS ] = actualFPS;
            upsStore[ (int)statsCount%NUM_FPS ] = actualUPS;
            statsCount++;

            // total the stored FPSs and UPSs
            double totalFPS = 0.0;
            double totalUPS = 0.0;

            for (int i=0; i < NUM_FPS; i++) {
                totalFPS += fpsStore[i];
                totalUPS += upsStore[i];
            }

            // obtain the average FPS and UPS
            if (statsCount < NUM_FPS) {
                averageFPS = totalFPS/statsCount;
                averageUPS = totalUPS/statsCount;
            }
            else {
                averageFPS = totalFPS/NUM_FPS;
                averageUPS = totalUPS/NUM_FPS;
            }

            /**/
            System.out.println(timedf.format( (double) statsInterval/1000) + " " +
            timedf.format((double) realElapsedTime/1000) + "s " +
            df.format(timingError) + "% " +
            frameCount + "c " +
            framesSkipped + "/" + totalFramesSkipped + " skip; " +
            df.format(actualFPS) + " " + df.format(averageFPS) + " afps; " +
            df.format(actualUPS) + " " + df.format(averageUPS) + " aups" );
            /**/


            framesSkipped = 0;
            prevStatsTime = timeNow;
            statsInterval = 0;   // reset
        }
    }  // end of storeStats()


    private void printStats()
    {
        System.out.println("Frame Count/Loss: " + frameCount + " / " + totalFramesSkipped);
        System.out.println("Average FPS: " + df.format(averageFPS));
        System.out.println("Average UPS: " + df.format(averageUPS));
        System.out.println("Time Spent: " + timeSpentInGame + " secs");
    }  // end of printStats()

}
