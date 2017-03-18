
package cglosser;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;
import java.util.Random;

/**
    The ball class handles drawing and updating the paddle on the screen.

    @author Courtney Glosser
 */

public class Paddle {
    // Board dimensions (in pixels)
    private static final int BOARD_WIDTH = 250;
    private static final int BOARD_HEIGHT = 260;

    // Board orientation on the Panel
    private static final int BOARD_X = 200;
    private static final int BOARD_Y = 25;

    private static final int PADDLE_HEIGHT = 100;
    private static final int PADDLE_WIDTH = 9;

    private static final int SPEED = 3;
    private static final int MAX_AI_LAG = 3;

    int aiLag;

    int y = BOARD_Y + 80;
    int x = BOARD_X + 9;

    int lagTimer = 0;
    Random rnd;

    public Paddle () {
        init(x, y);
    }

    public Paddle(int inX, int inY) {
        init(inX, inY);
    }

    private void init(int inX, int inY) {
        x = inX;
        y = inY;

        rnd = new Random();
        aiLag = rnd.nextInt(MAX_AI_LAG);
    }

    public int getX() {return x;}
    public int getY() {return y;}

    public int getRightSideX() {
        return x + PADDLE_WIDTH;
    }

    public int getBottomY() {
        return y + PADDLE_HEIGHT;
    }

    public void setX(int inX) {x = inX;}
    public void setY(int inY) {y = inY;}


    public void drawPaddle(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public void updatePaddle(Ball ball) {
        // Slow down computer paddle to make is beatable.
        if (lagTimer++ > aiLag) {
            lagTimer = 0;
            aiLag = rnd.nextInt(MAX_AI_LAG);
            if (y + PADDLE_HEIGHT / 2 > ball.getY()) {
                y--;
            }
            else {
                y++;
            }
            checkYCollision();
        }
    }

    public void down() {
        y += SPEED;
        checkYCollision();
    }
    public void up() {
        y -= SPEED;
        checkYCollision();
    }

    private void checkYCollision() {
        if (y < BOARD_Y) {
            y = BOARD_Y;
        }
        if (y + PADDLE_HEIGHT > BOARD_Y + BOARD_HEIGHT) {
            y = BOARD_Y + BOARD_HEIGHT - PADDLE_HEIGHT;
        }
    }

}
