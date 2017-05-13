
package cglosser;

import javax.swing.JPanel;
import javax.swing.JButton;
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

    public JButton startBtn, loadBtn, exitBtn, infoBtn, loseBtn, winBtn;
    public JButton saveBtn, city1Btn, city2Btn, city3Btn, city4Btn;
    public JButton save1Btn, save2Btn, save3Btn;

    public ScenesRPGPanel() {

        gameState = "welcome";
        bm = new ButtonManager(this);
        gameScreen = new Screen(this);
        gameStats = new Stats();

        initBtns();

        setBackground(Color.black);
        setFocusable(true);
        requestFocus();
        readyForTermination();

        this.setLayout(null);

        repaint();
    }

    public void initBtns() {
        startBtn = new JButton ("Start");
        startBtn.addActionListener( new StartListener());
        startBtn.setBounds(280, 100, 80, 25);
        this.add(startBtn);

        loadBtn = new JButton ("Load");
        loadBtn.addActionListener( new LoadListener());
        loadBtn.setBounds(280, 130, 80, 25);
        this.add(loadBtn);

        infoBtn = new JButton ("Info");
        infoBtn.addActionListener( new InfoListener());
        infoBtn.setBounds(280, 160, 80, 25);
        this.add(infoBtn);

        exitBtn = new JButton ("Exit");
        exitBtn.addActionListener( new ExitListener());
        exitBtn.setBounds(580, 305, 55, 25);
        this.add(exitBtn);

        loseBtn = new JButton ("Lose");
        loseBtn.addActionListener( new LoseListener());
        loseBtn.setBounds(580, 0, 55, 25);
        this.add(loseBtn);

        winBtn = new JButton ("Win");
        winBtn.addActionListener( new WinListener());
        winBtn.setBounds(580, 30, 55, 25);
        this.add(winBtn);

        saveBtn = new JButton("Save");
        saveBtn.addActionListener( new SaveListener());
        saveBtn.setBounds(580, 275, 55, 25);

        city1Btn = new JButton("City1");
        city1Btn.addActionListener( new CityListener(1));
        city1Btn.setBounds(280, 100, 80, 25);

        city2Btn = new JButton("City2");
        city2Btn.addActionListener( new CityListener(2));
        city2Btn.setBounds(280, 130, 80, 25);

        city3Btn = new JButton("City3");
        city3Btn.addActionListener( new CityListener(3));
        city3Btn.setBounds(280, 160, 80, 25);

        city4Btn = new JButton("City4");
        city4Btn.addActionListener( new CityListener(4));
        city4Btn.setBounds(280, 190, 80, 25);

        save1Btn = new JButton("Save1");
        save1Btn.addActionListener( new SaveListener(1));
        save1Btn.setBounds(280, 100, 80, 25);

        save2Btn = new JButton("Save2");
        save2Btn.addActionListener( new SaveListener(2));
        save2Btn.setBounds(280, 130, 80, 25);

        save3Btn = new JButton("Save3");
        save3Btn.addActionListener( new SaveListener(3));
        save3Btn.setBounds(280, 160, 80, 25);

    }

    public void setGameState(String state) {
        gameState = state;
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
            drawActive(g2d);
        }
        else if (gameState == "welcome") {
            // Draw a welcome screen
            drawWelcome(g2d);
        }
        else if (gameState == "load") {
            drawLoad(g2d);
        }
        else if (gameState == "info") {
            drawInfo(g2d);
        }
        else if (gameState == "win") {
            // Draw a win screen
            gameScreen.drawWin();
        }
        else if (gameState == "lose") {
            // Draw a lose screen
            gameScreen.drawLose();
        }
        else if (gameState == "city") {
            drawCity(g2d);
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


    private void drawWelcome(Graphics2D g2d) {
        gameScreen.drawWelcome(g2d);

        this.add(startBtn);
        this.add(loadBtn);
        this.add(infoBtn);
        this.add(exitBtn);
    }

    private void drawActive(Graphics2D g2d) {
        gameScreen.drawActive(g2d);

        this.add(city1Btn);
        this.add(city2Btn);
        this.add(city3Btn);
        this.add(city4Btn);
        this.add(saveBtn);
        this.add(exitBtn);
    }

    private void drawLoad(Graphics2D g2d) {
        gameScreen.drawLoad(g2d);

        this.add(save1Btn);
        this.add(save2Btn);
        this.add(save3Btn);
        this.add(exitBtn);
    }

    private void drawInfo(Graphics2D g2d) {
        gameScreen.drawInfo(g2d);

        this.add(exitBtn);
    }

    private void drawCity(Graphics2D g2d) {
        gameScreen.drawCity(g2d);

        this.add(saveBtn);
        this.add(exitBtn);
    }

    public class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameState = "active";
            ScenesRPGPanel.this.removeAll();
        }
    }

    public class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameState = "load";
            ScenesRPGPanel.this.removeAll();
        }
    }

    public class InfoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameState = "info";
            ScenesRPGPanel.this.removeAll();
        }
    }

    public class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (gameState == "welcome") {
                running = false;
            }
            else {
                gameState = "welcome";
            }
            ScenesRPGPanel.this.removeAll();
        }
    }

    public class LoseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameState = "lose";
            ScenesRPGPanel.this.removeAll();
        }
    }

    public class WinListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameState = "win";
            ScenesRPGPanel.this.removeAll();
        }
    }

    public class SaveListener implements ActionListener {
        int save = 0;
        //Create constructors to support taking an integer.
        public SaveListener(int save) {
            super();
            this.save = save;
        }
        public SaveListener() {
            super();
            this.save = -1;
        }
        public void actionPerformed(ActionEvent e) {
            if (save > 0) {
                gameState = "active";
            }
            else {
                gameState = "save";
            }
            ScenesRPGPanel.this.removeAll();
        }
    }

    public class CityListener implements ActionListener {
        int city = 0;
        public CityListener(int city) {
            super();
            this.city = city;
        }
        public void actionPerformed(ActionEvent e) {
            gameState = "city";
            whichCity = city;
            ScenesRPGPanel.this.removeAll();
        }
    }

}
