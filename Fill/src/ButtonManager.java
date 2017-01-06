
import java.awt.Color;
import java.awt.Graphics2D;

/**
    Manage an array of buttons the player can manipulate
 */

public class ButtonManager {

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
}
