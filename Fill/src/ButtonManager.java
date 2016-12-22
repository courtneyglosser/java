
import java.awt.Color;
import java.awt.Graphics2D;

/**
    Manage an array of buttons the player can manipulate
 */

public class ButtonManager {

    private static final int NUM_BUTTONS = 6;

    private Button[] buttons = new Button[8]; 
    // Available Colors
    private Color[] colors = new Color[6];

    public ButtonManager() {
        init();
    }

    private void init() {
        colors[0] = Color.cyan;
        colors[1] = Color.yellow;
        colors[2] = Color.red;
        colors[3] = Color.green;
        colors[4] = Color.magenta;
        colors[5] = Color.lightGray;

        // Define each button.
        buttons[0] = new Button(25, 25, colors[0]);
        buttons[1] = new Button(25, 65, colors[1]);
        buttons[2] = new Button(25, 105, colors[2]);
        buttons[3] = new Button(25, 145, colors[3]);
        buttons[4] = new Button(25, 185, colors[4]);
        buttons[5] = new Button(25, 225, colors[5]);

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
