
package cglosser;

import java.awt.Graphics2D;
import java.awt.Color;

/**
    Currently, class handles the various colored buttons correspoding to the
    colored tiles on the game board.  Will refactor soon to represent more
    robust OOP design as a parent Button class that will have children to
    handle colored and system / menu buttons (i.e. Start / Restart and Exit).

    @author Courtney Glosser
 */

public class Button {

    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 30;

    private int x;
    private int y;
    private Color clr;

    public Button() {
        init(0, 0, Color.black);
    }

    public Button(int inX, int inY, Color inColor) {
        init(inX, inY, inColor);
    }

    private void init(int inX, int inY, Color inColor) {
        x = inX;
        y = inY;
        clr = inColor;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public Color getColor() {return clr;}


    public void setX(int inX) {x = inX;}
    public void setY(int inY) {y = inY;}
    public void setColor (Color inColor) {clr = inColor;}

    public void drawButton(Graphics2D g2d) {
        g2d.setColor(clr);
        g2d.fillRect(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    public boolean clicked (int inX, int inY) {
        boolean rtn = false;
        if (inX > x && inX < x + BUTTON_WIDTH &&
            inY > y && inY < y + BUTTON_HEIGHT) {
            rtn = true;
        }
        return rtn;
    }
}
