
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

    private AssetButton singles;
    private AssetButton tens;
    private AssetButton hundreds;
    private AssetButton thousands;

    public ButtonManager() {
        init();
    }

    private void init() {
        showSingle = true;
        showTens = showHundreds = showThousands = false;

        singles = new AssetButton(1, 1);
        tens = new AssetButton(10, 10);
        hundreds = new AssetButton(100, 100);
        thousands = new AssetButton(1000, 1000);

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

    public boolean checkTens() {
        return showTens;
    }

    public boolean checkHundreds() {
        return showHundreds;
    }

    public boolean checkThousands() {
        return showThousands;
    }

    public void showTens() {
        showTens = true;
    }
    public void showHundreds() {
        showHundreds = true;
    }
    public void showThousands() {
        showThousands = true;
    }

    public void drawBoardButtons(Graphics2D g2d) {
        if (showSingle) {
            g2d.setColor(Color.white);
            g2d.drawString("Single: " + singles.getPrice(), 20, 120);
            g2d.drawRect(18, 100, 150, 30);
        }
        if (showTens) {
            g2d.setColor(Color.white);
            g2d.drawString("Tens: " + tens.getPrice(), 20, 150);
            g2d.drawRect(18, 130, 150, 30);
        }
        if (showHundreds) {
            g2d.setColor(Color.white);
            g2d.drawString("Hundreds: " + hundreds.getPrice(), 20, 180);
            g2d.drawRect(18, 160, 150, 30);
        }
        if (showThousands) {
            g2d.setColor(Color.white);
            g2d.drawString("Thousands: " + thousands.getPrice(), 20, 210);
            g2d.drawRect(18, 190, 150, 30);
        }

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

    public Purchase doPurchase(int x, int y, int money) {
        Purchase rtn = new Purchase();
        // Check for single:
        if (x > 18 && x < 168 && y > 100 && y < 130) {
            if (singles.getPrice() <= money) {
                rtn.setPrice(singles.getPrice());
                rtn.setPerSecond(singles.getPerSecond());
                singles.updatePriceForPurchase();
            }
        }
        // Check for tens:
        if (x > 18 && x < 168 && y > 130 && y < 160) {
            if (tens.getPrice() <= money) {
                rtn.setPrice(tens.getPrice());
                rtn.setPerSecond(tens.getPerSecond());
                tens.updatePriceForPurchase();
            }
        }
        // Check for hundreds:
        if (x > 18 && x < 168 && y > 160 && y < 190) {
            if (hundreds.getPrice() <= money) {
                rtn.setPrice(hundreds.getPrice());
                rtn.setPerSecond(hundreds.getPerSecond());
                hundreds.updatePriceForPurchase();
            }
        }
        // Check for thousands:
        if (x > 18 && x < 168 && y > 190 && y < 220) {
            if (thousands.getPrice() <= money) {
                rtn.setPrice(thousands.getPrice());
                rtn.setPerSecond(thousands.getPerSecond());
                thousands.updatePriceForPurchase();
            }
        }

        return rtn;
    }
    public int applyPurchase(int money, int x, int y) {
        // Check for single:
        if (x > 18 && x < 168 && y > 100 && y < 130) {
            return money - 1;
        }
        // Check for tens:
        if (x > 18 && x < 168 && y > 130 && y < 160) {
            return money - 10;
        }
        // Check for hundreds:
        if (x > 18 && x < 168 && y > 160 && y < 190) {
            return money - 100;
        }
        // Check for thousands:
        if (x > 18 && x < 168 && y > 190 && y < 220) {
            return money - 1000;
        }

        return money;
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
