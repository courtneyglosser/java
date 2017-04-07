
package cglosser;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
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

/**
    Extendings the Swing JPanel class with My game specific settings.  Also,
    implementing ActionListener to register and handle mouse click events.
    This class contains the primary game loop, handled as a collaboration
    between the actionPerformed function and the doDrawing function. 

    @author Courtney Glosser
 */

public class WormPanel extends JPanel implements ActionListener, Runnable{
    private String gameState; // welcome, active, win, lose
    private int count;
    private int seconds;
    private int period = 1000/100; // ms / FPS;

    private static final int PWIDTH = 500;
    private static final int PHEIGHT = 400;

    private Thread animator;

    private volatile boolean running = false;
    private volatile boolean gameOver = false;
    private volatile boolean isPaused = false;

    private Graphics dbg;
    private Image dbImage = null;

    public WormPanel(WormChase wc, int period) {

        setBackground(Color.black);
        setPreferredSize (new Dimension(PWIDTH, PHEIGHT) );

        setFocusable(true);
        requestFocus();
        readyForTermination();

        addMouseListener(new MAdapter());
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
        }

        System.exit(0);
    }

    private void gameUpdate() {
        if (!gameOver) {
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

            dbg.setColor(Color.white);
            dbg.fillRect(0,0, PWIDTH, PHEIGHT);

            if (gameOver) {
                gameOverMessage(dbg);
            }
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

    private void gameOverMessage(Graphics g) {
        int x = 10;
        int y = 10;
        String msg = "Game Over";
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

    private void testPress(int x, int y) {

        if (!gameOver) {
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
}
