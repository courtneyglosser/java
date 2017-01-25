package cglosser;

import java.awt.Color;

/**
    The color options for Tiles and buttons must be kept synchronized
    throughout the code.  The ColorManager class is responsible for maintaining
    a list of available colors for use when drawing tiles and buttons

    @author Courtney Glosser
 */

public class ColorManager {
    private static final int NUM_COLORS = 6;

    private Color[] colors = new Color[NUM_COLORS];

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
        if (idx >= 0 && idx < NUM_COLORS) {
            rtn = colors[idx];
        }
        else {
            System.out.println("Attempt at invalid index for color get");
        }

        return rtn;
    }

    public void setColor(int idx, Color inColor) {
        if (idx >= 0 && idx < NUM_COLORS) {
            colors[idx] = inColor;
        }
        else {
            System.out.println("Attempt at invalid index for color set");
        }
    }

}
