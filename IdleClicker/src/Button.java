
package cglosser;

import java.awt.Graphics2D;
import java.awt.Color;

/**
    Handle basic button responsibilities: Display, Click-checking

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

    /**
        @return X-axis pixel coordinate
     */
    public int getX() {return x;}
    /**
        @return Y-axis pixel coordinate
     */
    public int getY() {return y;}
    /**
        @return Color of this button
     */

    /**
        @param inX X-axis pixel coordinate
     */
    public void setX(int inX) {x = inX;}
    /**
        @param inY Y-axis pixel coordinate
     */
    public void setY(int inY) {y = inY;}

    /**
        Draw the button.

        @param g2d Graphics2D Java library
     */

    /**
        Did the user click this button?

        @param inX X-axis coordinate of user's click event
        @param inY Y-axis coordinate of user's click event
        @return <code>true</code> if user clicked this button.
                <code>false</code> otherwise.
     */
    public boolean clicked (int inX, int inY) {
        boolean rtn = false;
        if (inX > x && inX < x + BUTTON_WIDTH &&
            inY > y && inY < y + BUTTON_HEIGHT) {
            rtn = true;
        }
        return rtn;
    }
}
