
import java.awt.Color;
import java.awt.Graphics2D;

/**
    A manager class that will handle drawing the game board, including a list
of tiles that will be multiple colors.
 */

public class Board {

    private static final int BOARD_WIDTH = 300;
    private static final int BOARD_HEIGHT = 300;

    private static final int BOARD_X = 315;
    private static final int BOARD_Y = 25;

    public Board () {
        System.out.println("Board Class constructed");
    }

    public void drawBoard(Graphics2D g2d) {
        g2d.setColor(Color.blue);
        g2d.drawRect(BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT);
    }
}
