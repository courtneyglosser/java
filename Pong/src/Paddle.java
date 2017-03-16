
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

    int y = BOARD_Y + 80;
    int x = BOARD_X + 9;

    public Paddle () {
        init(x, y);
    }

    public Paddle(int inX, int inY) {
        init(inX, inY);
    }

    private void init(int inX, int inY) {
        x = inX;
        y = inY;
    }

    public void drawPaddle(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }
}
