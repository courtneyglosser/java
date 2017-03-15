
package cglosser;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;
import java.util.Random;

/**
    The ball class handles drawing and updating the ball on the screen.

    @author Courtney Glosser
 */

public class Ball {
    // Board dimensions (in pixels)
    private static final int BOARD_WIDTH = 250;
    private static final int BOARD_HEIGHT = 260;

    // Board orientation on the Panel
    private static final int BOARD_X = 200;
    private static final int BOARD_Y = 25;

    int x;
    int y;

    boolean x_positive = true;
    boolean y_positive = true;

    Color color;

    public Ball () {
        initBall(0,0, Color.BLUE);
    }

    public Ball (int x, int y, Color c) {
        initBall(x,y, c);
    }

    public void initBall(int startX, int startY, Color c) {
        x = startX;
        y = startY;
        color = c;
    }

    public void setX(int inX) {x = inX;}
    public void setY(int inY) {y = inY;}
    public void setColor(Color c) {color = c;}

    public int getX() {return x;}
    public int getY() {return y;}
    public Color getColor() {return color;}

    public void drawBall (Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(x, y, 9, 9);
    }

    public void updateBall() {

        if (x + 9 + 1 >= BOARD_X + BOARD_WIDTH) {
            x_positive = false;
        }
        if (x - 1 <= BOARD_X) {
            x_positive = true;
        }
        if (y + 9 + 1 >= BOARD_Y + BOARD_HEIGHT) {
            y_positive = false;
        }
        if (y - 1 <= BOARD_Y) {
            y_positive = true;
        }

        if (x_positive) {
            x++;
        }
        else {
            x--;
        }
        if (y_positive) {
            y++;
        }
        else {
            y--;
        }
    }
}
