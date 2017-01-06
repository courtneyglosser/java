
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Class to manage the non-active game screens, welcome, win, and lose
 */

public class Screen {

    public Screen() {}

    public void drawWelcome(Graphics2D g2d) {

        System.out.println("Welcome!");

        g2d.setColor(Color.white);
        g2d.drawString("Welcome", 100, 50);

        g2d.drawRect(100, 65, 50, 20);
        g2d.drawString("Start", 100, 80);

        g2d.drawRect(100, 95, 50, 20);
        g2d.drawString("Exit", 100, 110);
    }

    public void drawWin(Graphics2D g2d) {

        System.out.println("Winner!");

        g2d.setColor(Color.white);
        g2d.drawString("Winner!", 100, 50);

        g2d.drawRect(100, 65, 50, 20);
        g2d.drawString("Restart?", 100, 80);

        g2d.drawRect(100, 95, 50, 20);
        g2d.drawString("Exit", 100, 110);
    }

    public boolean checkStart(String gameState, int x, int y) {
        boolean rtn = false;

        if (gameState != "active") {
            if (x > 100 && x < 150 && y > 65 && y < 85) {
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
        return rtn;
    }
}
