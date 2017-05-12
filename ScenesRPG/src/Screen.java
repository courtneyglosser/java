
package cglosser;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


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

    public Screen(ScenesRPGPanel jp) {
        bm = new ButtonManager(jp);

        LoadImages();
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

    }

    public void drawActive(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Active", 100, 50);

    }

    public void drawLoad(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Load", 100, 50);

    }

    public void drawInfo(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Info", 100, 50);

    }

    public void drawCity(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("City", 100, 50);

    }

    public void drawWin() {
//        g2d.setColor(Color.white);
//        g2d.drawString("Winner!", 100, 50);

        bm.drawWinButtons();
    }

    public void drawLose() {
//        g2d.setColor(Color.white);
//        g2d.drawString("Loser!", 100, 50);

        bm.drawLoseButtons();
    }
}
