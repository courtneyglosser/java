
package cglosser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;


/**
    Sreen management class to handle drawing the appropriate display based on
    the current state of the game.  Screen also acts as an ButtonManager class
    wrapper to provide appropriate game state information to the ButtonManager
    class.

    @author Courtney Glosser
 */

public class Screen {

    private Image StartBtn;
    private Image RestartBtn;
    private Image ExitBtn;

    private ButtonManager bm;

    private ScenesRPGPanel myPanel;

    public JButton startBtn, loadBtn, exitBtn, infoBtn, loseBtn, winBtn;
    public JButton saveBtn, city1Btn, city2Btn, city3Btn, city4Btn;
    public JButton save1Btn, save2Btn, save3Btn;

    public JButton innBtn, storeBtn, missionBtn, adventureBtn;


    public Screen(ScenesRPGPanel jp) {
        myPanel = jp;
        bm = new ButtonManager(jp);

        LoadImages();

        init();
    }

    private void init() {
        NavListener myNavListener = new NavListener(myPanel);

        startBtn = new JButton ("Start");
        startBtn.addActionListener(myNavListener);
        startBtn.setBounds(280, 100, 80, 25);
        startBtn.setHorizontalAlignment(SwingConstants.CENTER);
        myPanel.add(startBtn);

        loadBtn = new JButton ("Load");
        loadBtn.addActionListener( myNavListener);
        loadBtn.setBounds(280, 130, 80, 25);
        loadBtn.setHorizontalAlignment(SwingConstants.CENTER);
        myPanel.add(loadBtn);

        infoBtn = new JButton ("Info");
        infoBtn.addActionListener( myNavListener);
        infoBtn.setBounds(280, 160, 80, 25);
        infoBtn.setHorizontalAlignment(SwingConstants.CENTER);
        myPanel.add(infoBtn);

        exitBtn = new JButton ("Exit");
        exitBtn.addActionListener( myNavListener);
        exitBtn.setBounds(560, 305, 75, 25);
        exitBtn.setHorizontalAlignment(SwingConstants.CENTER);
        myPanel.add(exitBtn);

        loseBtn = new JButton ("Lose");
        loseBtn.addActionListener( myNavListener);
        loseBtn.setBounds(560, 0, 75, 25);
        loseBtn.setHorizontalAlignment(SwingConstants.CENTER);
        myPanel.add(loseBtn);

        winBtn = new JButton ("Win");
        winBtn.addActionListener( myNavListener);
        winBtn.setBounds(560, 30, 75, 25);
        winBtn.setHorizontalAlignment(SwingConstants.CENTER);
        myPanel.add(winBtn);

        saveBtn = new JButton("Save");
        saveBtn.addActionListener( myNavListener);
        saveBtn.setBounds(560, 275, 75, 25);
        saveBtn.setHorizontalAlignment(SwingConstants.CENTER);

        city1Btn = new JButton("City1");
        city1Btn.addActionListener( myNavListener);
        city1Btn.setBounds(280, 100, 80, 25);
        city1Btn.setHorizontalAlignment(SwingConstants.CENTER);

        city2Btn = new JButton("City2");
        city2Btn.addActionListener( myNavListener);
        city2Btn.setBounds(280, 130, 80, 25);
        city2Btn.setHorizontalAlignment(SwingConstants.CENTER);

        city3Btn = new JButton("City3");
        city3Btn.addActionListener( myNavListener);
        city3Btn.setBounds(280, 160, 80, 25);
        city3Btn.setHorizontalAlignment(SwingConstants.CENTER);

        city4Btn = new JButton("City4");
        city4Btn.addActionListener( myNavListener);
        city4Btn.setBounds(280, 190, 80, 25);
        city4Btn.setHorizontalAlignment(SwingConstants.CENTER);

        save1Btn = new JButton("Save1");
        save1Btn.addActionListener( myNavListener);
        save1Btn.setBounds(280, 100, 80, 25);
        save1Btn.setHorizontalAlignment(SwingConstants.CENTER);

        save2Btn = new JButton("Save2");
        save2Btn.addActionListener( myNavListener);
        save2Btn.setBounds(280, 130, 80, 25);
        save2Btn.setHorizontalAlignment(SwingConstants.CENTER);

        save3Btn = new JButton("Save3");
        save3Btn.addActionListener( myNavListener);
        save3Btn.setBounds(280, 160, 80, 25);
        save3Btn.setHorizontalAlignment(SwingConstants.CENTER);

        innBtn = new JButton("Inn");
        innBtn.addActionListener( myNavListener);
        innBtn.setBounds(100, 80, 200, 100);
        innBtn.setHorizontalAlignment(SwingConstants.CENTER);

        storeBtn = new JButton("Store");
        storeBtn.addActionListener( myNavListener);
        storeBtn.setBounds(310, 80, 200, 100);
        storeBtn.setHorizontalAlignment(SwingConstants.CENTER);

        missionBtn = new JButton("Mission");
        missionBtn.addActionListener( myNavListener);
        missionBtn.setBounds(100, 190, 200, 100);
        missionBtn.setHorizontalAlignment(SwingConstants.CENTER);

        adventureBtn = new JButton("Adventure");
        adventureBtn.addActionListener( myNavListener);
        adventureBtn.setBounds(310, 190, 200, 100);
        adventureBtn.setHorizontalAlignment(SwingConstants.CENTER);

    }

    private void LoadImages() {
        try {
            StartBtn = ImageIO.read(new File("build/Start.png"));
            RestartBtn = ImageIO.read(new File("build/Restart.png"));
            ExitBtn = ImageIO.read(new File("build/Exit.png"));
        }
        catch (IOException e) {
            // Handle exception
            System.out.println("Exception loading button images: " +
                e.getMessage());
        }
    }

    public void drawWelcome(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Welcome", 100, 50);

        myPanel.add(startBtn);
        myPanel.add(loadBtn);
        myPanel.add(infoBtn);
        myPanel.add(exitBtn);
    }

    public void drawActive(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Active", 100, 50);

        myPanel.add(city1Btn);
        myPanel.add(city2Btn);
        myPanel.add(city3Btn);
        myPanel.add(city4Btn);
        myPanel.add(saveBtn);
        myPanel.add(exitBtn);
    }

    public void drawLoad(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Load", 100, 50);

        myPanel.add(save1Btn);
        myPanel.add(save2Btn);
        myPanel.add(save3Btn);
        myPanel.add(exitBtn);
    }

    public void drawInfo(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Info", 100, 50);

        g2d.drawString("Scenes is a simple RPG set in a very basic world.", 150, 80);
        g2d.drawString("Created by GlosserGames", 225, 200);

        myPanel.add(exitBtn);
    }

    public void drawCity(Graphics2D g2d, int city) {
        g2d.setColor(Color.white);
        g2d.drawString("City " + city, 100, 50);

        myPanel.add(innBtn);
        myPanel.add(storeBtn);
        myPanel.add(missionBtn);
        myPanel.add(adventureBtn);

        myPanel.add(saveBtn);
        myPanel.add(exitBtn);
    }

    public void drawInn(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Inn", 100, 50);

        myPanel.add(saveBtn);
        myPanel.add(exitBtn);
    }

    public void drawStore(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Store", 100, 50);

        myPanel.add(saveBtn);
        myPanel.add(exitBtn);
    }

    public void drawMission(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Mission", 100, 50);

        myPanel.add(saveBtn);
        myPanel.add(exitBtn);
    }

    public void drawAdventure(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Adventure", 100, 50);

        myPanel.add(saveBtn);
        myPanel.add(exitBtn);
    }

    public void drawSave(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Save", 100, 50);

        myPanel.add(save1Btn);
        myPanel.add(save2Btn);
        myPanel.add(save3Btn);
        myPanel.add(exitBtn);
    }

    public void drawWin(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Winner!", 100, 50);

        bm.drawWinButtons();
    }

    public void drawLose(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Loser!", 100, 50);

        bm.drawLoseButtons();
    }


    public class NavListener implements ActionListener {
        ScenesRPGPanel panel;
        NavListener(ScenesRPGPanel myPanel) {
            panel = myPanel;
        }
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "Start") {
                myPanel.gameState = "active";
            }
            if (e.getActionCommand() == "Load") {
                myPanel.gameState = "load";
            }
            if (e.getActionCommand() == "Info") {
                myPanel.gameState = "info";
            }
            if (e.getActionCommand() == "Lose") {
                myPanel.gameState = "lose";
            }
            if (e.getActionCommand() == "Win") {
                myPanel.gameState = "win";
            }
            if (e.getActionCommand() == "Exit") {
                if (myPanel.gameState == "welcome") {
                    myPanel.setRunning(false);
                }
                else {
                    myPanel.gameState = "welcome";
                }
            }
            if (e.getActionCommand() == "Save") {
                myPanel.gameState = "save";
            }
            if (e.getActionCommand() == "Save1" ||
                e.getActionCommand() == "Save2" ||
                e.getActionCommand() == "Save3") {
                myPanel.gameState = "active";
            }
            if (e.getActionCommand() == "City1") {
                myPanel.gameState = "city";
                myPanel.whichCity = 1;
            }
            if (e.getActionCommand() == "City2") {
                myPanel.gameState = "city";
                myPanel.whichCity = 2;
            }
            if (e.getActionCommand() == "City3") {
                myPanel.gameState = "city";
                myPanel.whichCity = 3;
            }
            if (e.getActionCommand() == "City4") {
                myPanel.gameState = "city";
                myPanel.whichCity = 4;
            }
            if (e.getActionCommand() == "Inn") {
                myPanel.gameState = "inn";
            }
            if (e.getActionCommand() == "Store") {
                myPanel.gameState = "store";
            }
            if (e.getActionCommand() == "Mission") {
                myPanel.gameState = "mission";
            }
            if (e.getActionCommand() == "Adventure") {
                myPanel.gameState = "adventure";
            }

            myPanel.removeAll();
        }
    }
}
