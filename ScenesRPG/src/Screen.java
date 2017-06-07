
package cglosser;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;


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

    private boolean debug = true;

    public Screen(ScenesRPGPanel jp) {
        myPanel = jp;
        LoadImages();

        init();
    }

    private void init() {

        bm = new ButtonManager(myPanel);

        myPanel.add(bm.getStartBtn());
        myPanel.add(bm.getLoadBtn());
        myPanel.add(bm.getInfoBtn());
        myPanel.add(bm.getExitBtn());

        if (debug){
            myPanel.add(bm.getLoseBtn());
            myPanel.add(bm.getWinBtn());
        }
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

        myPanel.add(bm.getStartBtn());
        myPanel.add(bm.getLoadBtn());
        myPanel.add(bm.getInfoBtn());
        myPanel.add(bm.getExitBtn());
    }

    public void drawCharacter(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Character", 100, 50);

        myPanel.add(bm.getCitySelectBtn());
        myPanel.add(bm.getExitBtn());
    }

    public void drawCitySelect(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("City Select", 100, 50);

        myPanel.add(bm.getCity1Btn());
        myPanel.add(bm.getCity2Btn());
        myPanel.add(bm.getCity3Btn());
        myPanel.add(bm.getCity4Btn());
        myPanel.add(bm.getSaveBtn());
        myPanel.add(bm.getExitBtn());
    }

    public void drawLoad(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Load", 100, 50);

        myPanel.add(bm.getSave1Btn());
        myPanel.add(bm.getSave2Btn());
        myPanel.add(bm.getSave3Btn());
        myPanel.add(bm.getExitBtn());
    }

    public void drawInfo(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Info", 100, 50);

        g2d.drawString("Scenes is a simple RPG set in a very basic world.", 150, 80);
        g2d.drawString("Created by GlosserGames", 225, 200);

        myPanel.add(bm.getExitBtn());
    }

    public void drawCity(Graphics2D g2d, int city) {
        g2d.setColor(Color.white);
        g2d.drawString("City " + city, 100, 50);

        myPanel.add(bm.getInnBtn());
        myPanel.add(bm.getStoreBtn());
        myPanel.add(bm.getMissionBtn());
        myPanel.add(bm.getAdventureBtn());

        myPanel.add(bm.getSaveBtn());
        myPanel.add(bm.getExitBtn());
    }

    public void drawInn(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Inn", 100, 50);

        myPanel.add(bm.getSaveBtn());
        myPanel.add(bm.getExitBtn());
    }

    public void drawStore(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Store", 100, 50);

        myPanel.add(bm.getSaveBtn());
        myPanel.add(bm.getExitBtn());
    }

    public void drawMission(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Mission", 100, 50);

        myPanel.add(bm.getSaveBtn());
        myPanel.add(bm.getExitBtn());
    }

    public void drawAdventure(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Adventure", 100, 50);

        myPanel.add(bm.getSaveBtn());
        myPanel.add(bm.getExitBtn());
    }

    public void drawSave(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Save", 100, 50);

        myPanel.add(bm.getSave1Btn());
        myPanel.add(bm.getSave2Btn());
        myPanel.add(bm.getSave3Btn());
        myPanel.add(bm.getExitBtn());
    }

    public void drawWin(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Winner!", 100, 50);
    }

    public void drawLose(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Loser!", 100, 50);
    }


}
