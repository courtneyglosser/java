
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

    public JButton startBtn, loadBtn, exitBtn, infoBtn, loseBtn, winBtn;
    public JButton saveBtn, city1Btn, city2Btn, city3Btn, city4Btn;
    public JButton save1Btn, save2Btn, save3Btn;

    public JButton innBtn, storeBtn, missionBtn, adventureBtn;

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
        startBtn.addActionListener(new NavListener());
        startBtn.setBounds(280, 100, 80, 25);
        startBtn.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(startBtn);

        loadBtn = new JButton ("Load");
        loadBtn.addActionListener( new NavListener());
        loadBtn.setBounds(280, 130, 80, 25);
        loadBtn.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(loadBtn);

        infoBtn = new JButton ("Info");
        infoBtn.addActionListener( new NavListener());
        infoBtn.setBounds(280, 160, 80, 25);
        infoBtn.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(infoBtn);

        exitBtn = new JButton ("Exit");
        exitBtn.addActionListener( new NavListener());
        exitBtn.setBounds(560, 305, 75, 25);
        exitBtn.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(exitBtn);

        loseBtn = new JButton ("Lose");
        loseBtn.addActionListener( new NavListener());
        loseBtn.setBounds(560, 0, 75, 25);
        loseBtn.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(loseBtn);

        winBtn = new JButton ("Win");
        winBtn.addActionListener( new NavListener());
        winBtn.setBounds(560, 30, 75, 25);
        winBtn.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(winBtn);

        saveBtn = new JButton("Save");
        saveBtn.addActionListener( new NavListener());
        saveBtn.setBounds(560, 275, 75, 25);
        saveBtn.setHorizontalAlignment(SwingConstants.CENTER);

        city1Btn = new JButton("City1");
        city1Btn.addActionListener( new NavListener());
        city1Btn.setBounds(280, 100, 80, 25);
        city1Btn.setHorizontalAlignment(SwingConstants.CENTER);

        city2Btn = new JButton("City2");
        city2Btn.addActionListener( new NavListener());
        city2Btn.setBounds(280, 130, 80, 25);
        city2Btn.setHorizontalAlignment(SwingConstants.CENTER);

        city3Btn = new JButton("City3");
        city3Btn.addActionListener( new NavListener());
        city3Btn.setBounds(280, 160, 80, 25);
        city3Btn.setHorizontalAlignment(SwingConstants.CENTER);

        city4Btn = new JButton("City4");
        city4Btn.addActionListener( new NavListener());
        city4Btn.setBounds(280, 190, 80, 25);
        city4Btn.setHorizontalAlignment(SwingConstants.CENTER);

        save1Btn = new JButton("Save1");
        save1Btn.addActionListener( new NavListener());
        save1Btn.setBounds(280, 100, 80, 25);
        save1Btn.setHorizontalAlignment(SwingConstants.CENTER);

        save2Btn = new JButton("Save2");
        save2Btn.addActionListener( new NavListener());
        save2Btn.setBounds(280, 130, 80, 25);
        save2Btn.setHorizontalAlignment(SwingConstants.CENTER);

        save3Btn = new JButton("Save3");
        save3Btn.addActionListener( new NavListener());
        save3Btn.setBounds(280, 160, 80, 25);
        save3Btn.setHorizontalAlignment(SwingConstants.CENTER);

        innBtn = new JButton("Inn");
        innBtn.addActionListener( new NavListener());
        innBtn.setBounds(100, 80, 200, 100);
        innBtn.setHorizontalAlignment(SwingConstants.CENTER);

        storeBtn = new JButton("Store");
        storeBtn.addActionListener( new NavListener());
        storeBtn.setBounds(310, 80, 200, 100);
        storeBtn.setHorizontalAlignment(SwingConstants.CENTER);

        missionBtn = new JButton("Mission");
        missionBtn.addActionListener( new NavListener());
        missionBtn.setBounds(100, 190, 200, 100);
        missionBtn.setHorizontalAlignment(SwingConstants.CENTER);

        adventureBtn = new JButton("Adventure");
        adventureBtn.addActionListener( new NavListener());
        adventureBtn.setBounds(310, 190, 200, 100);
        adventureBtn.setHorizontalAlignment(SwingConstants.CENTER);
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
        else if (gameState == "save") {
            drawSave(g2d);
        }
        else if (gameState == "info") {
            drawInfo(g2d);
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
            drawCity(g2d);
        }
        else if (gameState == "inn") {
            drawInn(g2d);
        }
        else if (gameState == "store") {
            drawStore(g2d);
        }
        else if (gameState == "mission") {
            drawMission(g2d);
        }
        else if (gameState == "adventure") {
            drawAdventure(g2d);
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
        gameScreen.drawCity(g2d, whichCity);

        this.add(innBtn);
        this.add(storeBtn);
        this.add(missionBtn);
        this.add(adventureBtn);

        this.add(saveBtn);
        this.add(exitBtn);
    }

    private void drawInn(Graphics2D g2d) {
        gameScreen.drawInn(g2d);

        this.add(saveBtn);
        this.add(exitBtn);
    }

    private void drawStore(Graphics2D g2d) {
        gameScreen.drawStore(g2d);

        this.add(saveBtn);
        this.add(exitBtn);
    }

    private void drawMission(Graphics2D g2d) {
        gameScreen.drawMission(g2d);

        this.add(saveBtn);
        this.add(exitBtn);
    }

    private void drawAdventure(Graphics2D g2d) {
        gameScreen.drawAdventure(g2d);

        this.add(saveBtn);
        this.add(exitBtn);
    }

    private void drawSave(Graphics2D g2d) {
        gameScreen.drawSave(g2d);

        this.add(save1Btn);
        this.add(save2Btn);
        this.add(save3Btn);
        this.add(exitBtn);
    }

    public class NavListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Start") {
                gameState = "active";
            }
            if (e.getActionCommand() == "Load") {
                gameState = "load";
            }
            if (e.getActionCommand() == "Info") {
                gameState = "info";
            }
            if (e.getActionCommand() == "Lose") {
                gameState = "lose";
            }
            if (e.getActionCommand() == "Win") {
                gameState = "win";
            }
            if (e.getActionCommand() == "Exit") {
                if (gameState == "welcome") {
                    running = false;
                }
                else {
                    gameState = "welcome";
                }
            }
            if (e.getActionCommand() == "Save") {
                gameState = "save";
            }
            if (e.getActionCommand() == "Save1" ||
                e.getActionCommand() == "Save2" ||
                e.getActionCommand() == "Save3") {
                gameState = "active";
            }
            if (e.getActionCommand() == "City1") {
                gameState = "city";
                whichCity = 1;
            }
            if (e.getActionCommand() == "City2") {
                gameState = "city";
                whichCity = 2;
            }
            if (e.getActionCommand() == "City3") {
                gameState = "city";
                whichCity = 3;
            }
            if (e.getActionCommand() == "City4") {
                gameState = "city";
                whichCity = 4;
            }
            if (e.getActionCommand() == "Inn") {
                gameState = "inn";
            }
            if (e.getActionCommand() == "Store") {
                gameState = "store";
            }
            if (e.getActionCommand() == "Mission") {
                gameState = "mission";
            }
            if (e.getActionCommand() == "Adventure") {
                gameState = "adventure";
            }

            ScenesRPGPanel.this.removeAll();
        }
    }

}
