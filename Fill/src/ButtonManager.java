
package cglosser;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

/**
    Manage an array of buttons the player can manipulate
 */

public class ButtonManager {

    private Image StartBtn;
    private Image RestartBtn;
    private Image ExitBtn;

    private static final int NUM_BUTTONS = 6;

    private Button[] buttons = new Button[8]; 
    // Available Colors
    private ColorManager cm;

    public ButtonManager() {
        init();
    }

    private void init() {

        cm = new ColorManager();

        // Define each button.
        buttons[0] = new Button(25, 25, cm.getColor(0));
        buttons[1] = new Button(25, 65, cm.getColor(1));
        buttons[2] = new Button(25, 105, cm.getColor(2));
        buttons[3] = new Button(25, 145, cm.getColor(3));
        buttons[4] = new Button(25, 185, cm.getColor(4));
        buttons[5] = new Button(25, 225, cm.getColor(5));

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

    public void drawButtons(Graphics2D g2d) {
        for (int i = 0; i < NUM_BUTTONS; i++) {
            buttons[i].drawButton(g2d);
        }
    }

    public boolean clickedButton(int x, int y) {
        boolean rtn = false;

        for (int i = 0; i < NUM_BUTTONS; i++) {
            if (!rtn) {
                rtn = buttons[i].clicked(x, y);
            }
        }
        return rtn;
    }

    public Button registerClick(int x, int y) {
        Button rtn = new Button();

        for (int i = 0; i < NUM_BUTTONS; i++) {
            if(buttons[i].clicked(x, y)) {
                rtn = buttons[i];
            }
        }
        return rtn;
    }


    public void drawBoardButtons(Graphics2D g2d) {
        drawButtons(g2d);

        g2d.drawRect(500, 65, 50, 20);
        g2d.drawImage(RestartBtn, 500, 65, null);

        g2d.drawRect(500, 95, 50, 20);
        g2d.drawImage(ExitBtn, 500, 95, null);
    }
}
