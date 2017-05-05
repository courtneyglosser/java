
package cglosser;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
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
import java.awt.Toolkit;
import java.text.DecimalFormat;

/**
    Extendings the Swing JPanel class with My game specific settings.  Also,
    implementing ActionListener to register and handle mouse click events.
    This class contains the primary game loop, handled as a collaboration
    between the actionPerformed function and the doDrawing function. 

    @author Courtney Glosser
 */

public class WormPanel extends JPanel implements ActionListener, Runnable{
    private static final int PWIDTH = 500;
    private static final int PHEIGHT = 400;

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


    private String gameState; // welcome, active, win, lose
    private int count;
    private int seconds;
    private int period = 1000/100; // ms / FPS;

    private Thread animator;

    private volatile boolean running = false;
    private volatile boolean gameOver = false;
    private volatile boolean isPaused = false;

    private WormChase wcTop;
    private Obstacles obs;
    private Worm fred;
    private Font font;
    private FontMetrics metrics;
    private int score = 0;

    private Graphics dbg;
    private Image dbImage = null;

    public WormPanel(WormChase wc, int period) {
        wcTop = wc;
        this.period = period;

        setBackground(Color.white);
        setPreferredSize (new Dimension(PWIDTH, PHEIGHT) );

        setFocusable(true);
        requestFocus();
        readyForTermination();

        // Create the game components
        obs = new Obstacles(wcTop);
        fred = new Worm(PWIDTH, PHEIGHT, obs);

        addMouseListener(new MAdapter());

        font = new Font("SansSerif", Font.BOLD, 24);
        metrics = this.getFontMetrics(font);

        // Timing elements
        fpsStore = new double[NUM_FPS];
        upsStore = new double[NUM_FPS];

        for (int i=0; i < NUM_FPS; i++) {
            fpsStore[i] = 0.0;
            upsStore[i] = 0.0;
        }
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

    private void gameUpdate() {
        if (!isPaused && !gameOver) {
            fred.move();
        }
    }

    private void gameRender() {
        if (dbImage == null) {
            dbImage = createImage(PWIDTH, PHEIGHT);
            if (dbImage == null) {
                System.out.println("dbImage is null");
                return;
            }
            else {
                dbg = dbImage.getGraphics();
            }
        }

        dbg.setColor(Color.white);
        dbg.fillRect(0,0, PWIDTH, PHEIGHT);

        // report frame count & average FPS and UPS at top left
        // dbg.drawString("Frame Count " + frameCount, 10, 25);
        dbg.setColor(Color.blue);
        dbg.setFont(font);

        dbg.drawString("Average FPS/UPS: " + df.format(averageFPS) + ", " +
                            df.format(averageUPS), 20, 25);  // was (10,55)

        dbg.setColor(Color.black);

        // draw game elements: the obstacles and the worm
            obs.draw(dbg);
            fred.draw(dbg);


        if (gameOver) {
            gameOverMessage(dbg);
        }
    }

    private void paintScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if (g != null && dbImage != null) {
                g.drawImage(dbImage, 0, 0, null);
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }
        catch (Exception e) {
            System.out.println("Graphics context error: " + e);
        }
    }

    // center the game-over message in the panel
    private void gameOverMessage(Graphics g) {
        String msg = "Game Over. Your Score: " + score;
        int x = (PWIDTH - metrics.stringWidth(msg))/2; 
        int y = (PHEIGHT - metrics.getHeight())/2;
        g.setColor(Color.red);
        g.setFont(font);
        g.drawString(msg, x, y);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (dbImage != null) {
            g.drawImage(dbImage, 0, 0, null);
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
/**/
    /**
        Class extends the MouseAdapter abstract class.  This class will
        register mousePressed events and utilize helper classes to execute
        appropriate game responses.

        @author Courtney Glosser
     */

    private class MAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {

            testPress(e.getX(), e.getY());

        }

    }

    /**
        Is the (x,y) close enough to the snake head to count a point?
        - If yes, score.
        - If no, is it on the snake's body?
        -- if yes, do nothing.
        -- if no, add an obstacle to the board.
     */
    private void testPress(int x, int y) {

        if (!isPaused && !gameOver) {
/**/
            if (fred.nearHead(x, y)) {
                gameOver = true;

                int intTime = (int)timeSpentInGame;
                score = (40 - intTime) + 40 - obs.getNumObstacles();
            }
            else {
                if (!fred.touchedAt(x, y) ) {
                    obs.add(x,y);
                }
            }
/**/
            System.out.println("Clicked: ("+x+","+y+")");
        }
    }


    private void readyForTermination() {

        addKeyListener( new KeyAdapter() {
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
        });
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
            wcTop.setTimeSpent( timeSpentInGame );

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
        System.out.println("Boxes used: " + obs.getNumObstacles());
    }  // end of printStats()

}
