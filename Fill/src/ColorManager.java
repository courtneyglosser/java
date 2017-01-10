package cglosser;

import java.awt.Color;

/**
 * Manage the shared colorset for tiles and buttons
 */

public class ColorManager {

    private Color[] colors = new Color[6];

    public ColorManager () {
        colors[0] = Color.cyan;
        colors[1] = Color.yellow;
        colors[2] = Color.red;
        colors[3] = Color.green;
        colors[4] = Color.magenta;
        colors[5] = Color.lightGray;
    }

    public Color getColor(int idx) {
        Color rtn = Color.black;
        if (idx >= 0 && idx < 6) {
            rtn = colors[idx];
        }
        else {
            System.out.println("Attempt at invalid index for color get");
        }

        return rtn;
    }

    public void setColor(int idx, Color inColor) {
        if (idx >= 0 && idx < 6) {
            colors[idx] = inColor;
        }
        else {
            System.out.println("Attempt at invalid index for color set");
        }
    }

}
