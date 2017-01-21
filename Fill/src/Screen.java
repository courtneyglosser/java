
package cglosser;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;


/**
 * Class to manage the non-active game screens, welcome, win, and lose
 */

public class Screen {

    private Image StartBtn;
    private Image RestartBtn;
    private Image ExitBtn;

    private ButtonManager bm;

    public Screen() {
        bm = new ButtonManager();

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

        System.out.println("Welcome!");

        g2d.setColor(Color.white);
        g2d.drawString("Welcome", 100, 50);

        bm.drawWelcomeButtons(g2d);

    }

    public void drawWin(Graphics2D g2d) {

        System.out.println("Winner!");

        g2d.setColor(Color.white);
        g2d.drawString("Winner!", 100, 50);

        bm.drawWinButtons(g2d);
    }

    public void drawLose(Graphics2D g2d) {

        System.out.println("Loser!");

        g2d.setColor(Color.white);
        g2d.drawString("Loser!", 100, 50);

        bm.drawLoseButtons(g2d);
    }
}
