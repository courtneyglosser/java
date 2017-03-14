
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
    int x;
    int y;

    public Ball () {
        initBall(0,0);
    }

    public Ball (int x, int y) {
        initBall(x,y);
    }

    public void initBall(int startX, int startY) {
        x = startX;
        y = startY;
    }

    public void setX(int inX) {x = inX;}
    public void setY(int inY) {y = inY;}

    public int getX() {return x;}
    public int getY() {return y;}

    public void drawBall (Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x, y, 9, 9);
    }
}
