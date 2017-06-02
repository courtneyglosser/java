
package cglosser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
    Manages the various buttons with which the user can interact:

    @author Courtney Glosser
 */

public class ButtonManager {

    public JButton startBtn, loadBtn, exitBtn, infoBtn, loseBtn, winBtn;
    public JButton saveBtn, city1Btn, city2Btn, city3Btn, city4Btn;
    public JButton save1Btn, save2Btn, save3Btn;
    public JButton innBtn, storeBtn, missionBtn, adventureBtn;

    private boolean debug = true;
    private ScenesRPGPanel myPanel;

    public ButtonManager(ScenesRPGPanel mp) {
        myPanel = mp;
        init();
    }

    private void init() {
        NavListener myNavListener = new NavListener(myPanel);

        startBtn = new JButton ("Start");
        startBtn.addActionListener(myNavListener);
        startBtn.setBounds(280, 100, 80, 25);
        startBtn.setHorizontalAlignment(SwingConstants.CENTER);

        loadBtn = new JButton ("Load");
        loadBtn.addActionListener( myNavListener);
        loadBtn.setBounds(280, 130, 80, 25);
        loadBtn.setHorizontalAlignment(SwingConstants.CENTER);

        infoBtn = new JButton ("Info");
        infoBtn.addActionListener( myNavListener);
        infoBtn.setBounds(280, 160, 80, 25);
        infoBtn.setHorizontalAlignment(SwingConstants.CENTER);

        exitBtn = new JButton ("Exit");
        exitBtn.addActionListener( myNavListener);
        exitBtn.setBounds(560, 305, 75, 25);
        exitBtn.setHorizontalAlignment(SwingConstants.CENTER);

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

        if (debug) {
            loseBtn = new JButton ("Lose");
            loseBtn.addActionListener( myNavListener);
            loseBtn.setBounds(560, 0, 75, 25);
            loseBtn.setHorizontalAlignment(SwingConstants.CENTER);

            winBtn = new JButton ("Win");
            winBtn.addActionListener( myNavListener);
            winBtn.setBounds(560, 30, 75, 25);
            winBtn.setHorizontalAlignment(SwingConstants.CENTER);
        }

    }

    public JButton getStartBtn() {return startBtn;}
    public JButton getLoadBtn() {return loadBtn;}
    public JButton getExitBtn() {return exitBtn;}
    public JButton getInfoBtn() {return infoBtn;}
    public JButton getLoseBtn() {return loseBtn;}
    public JButton getWinBtn() {return winBtn;}
    public JButton getSaveBtn() {return saveBtn;}
    public JButton getCity1Btn() {return city1Btn;}
    public JButton getCity2Btn() {return city2Btn;}
    public JButton getCity3Btn() {return city3Btn;}
    public JButton getCity4Btn() {return city4Btn;}
    public JButton getSave1Btn() {return save1Btn;}
    public JButton getSave2Btn() {return save2Btn;}
    public JButton getSave3Btn() {return save3Btn;}
    public JButton getInnBtn() {return innBtn;}
    public JButton getStoreBtn() {return storeBtn;}
    public JButton getMissionBtn() {return missionBtn;}
    public JButton getAdventureBtn() {return adventureBtn;}


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
