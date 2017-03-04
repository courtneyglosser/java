
package cglosser;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import javax.imageio.ImageIO;

/**
    Manages the various buttons with which the user can interact:
    <ul>
    <li>Manage placement of buttons on the screen for drawing
    <li>Manage which buttons are displayed based on game state
    <li>Identify if a user's "click" event is on a visible button
    </ul>

    @author Courtney Glosser
 */

public class ButtonManager {

    private Image StartBtn;
    private Image RestartBtn;
    private Image ExitBtn;

    private boolean showSingle;
    private boolean showTens;
    private boolean showHundreds;
    private boolean showThousands;

    public ButtonManager() {
        init();
    }

    private void init() {
        LoadImages();
    }

    private void LoadImages() {
        try {
            InputStream startResource =
                ButtonManager.class.getResourceAsStream("/Start.png");
            InputStream restartResource =
                ButtonManager.class.getResourceAsStream("/Restart.png");
            InputStream exitResource =
                ButtonManager.class.getResourceAsStream("/Exit.png");

            StartBtn = ImageIO.read(startResource);
            RestartBtn = ImageIO.read(restartResource);
            ExitBtn = ImageIO.read(exitResource);
        }
        catch (IOException e) {
            // Handle exception
            System.out.println("Exception loading button images: " +
                e.getMessage());
        }
    }

    public void drawBoardButtons(Graphics2D g2d) {
        g2d.drawRect(500, 65, 50, 20);
        g2d.drawImage(RestartBtn, 500, 65, null);

        g2d.drawRect(500, 95, 50, 20);
        g2d.drawImage(ExitBtn, 500, 95, null);
    }

    public void drawWelcomeButtons(Graphics2D g2d) {

        g2d.drawRect(100, 65, 50, 20);
        g2d.drawImage(StartBtn, 100, 65, null);

        g2d.drawRect(100, 95, 50, 20);
        g2d.drawImage(ExitBtn, 100, 95, null);

        g2d.drawRect(100, 125, 50, 20);
        g2d.drawString("Cont", 102, 140);
    }

    public void drawWinButtons(Graphics2D g2d) {

        g2d.drawRect(100, 65, 50, 20);
        g2d.drawImage(RestartBtn, 100, 65, null);

        g2d.drawRect(100, 95, 50, 20);
        g2d.drawImage(ExitBtn, 100, 95, null);

    }

    public void drawLoseButtons(Graphics2D g2d) {

        g2d.drawRect(100, 65, 50, 20);
        g2d.drawImage(RestartBtn, 100, 65, null);

        g2d.drawRect(100, 95, 50, 20);
        g2d.drawImage(ExitBtn, 100, 95, null);

    }

    public boolean checkStart(String gameState, int x, int y) {
        boolean rtn = false;

        if (gameState != "active") {
            if (x > 100 && x < 150 && y > 65 && y < 85) {
                rtn = true;
            }
        }
        else {
            if (x > 500 && x < 650 && y > 65 && y < 85) {
                rtn = true;
            }
        }

        return rtn;
    }

    public boolean checkExit(String gameState, int x, int y) {
        boolean rtn = false;

        if (gameState != "active") {
            if (x > 100 && x < 150 && y > 95 && y < 115) {
                rtn = true;
            }
        }
        else {
            if (x > 500 && x < 650 && y > 95 && y < 115) {
                rtn = true;
            }
        }

        return rtn;
    }

}
