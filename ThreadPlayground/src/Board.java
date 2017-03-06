
package cglosser;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;
import java.util.Random;

/**
    The Board class manages several aspects of the user's interaction with the
    game board:
    <ul>
    <li>Drawing the board of multi-colored tiles
    <li>Handling the process of changing tile colors based on user clicks
    <li>Checking state of tiles for win condition
    <li>Managing available moves to track lose condition
    </ul>
    <p>
    The difficulty of the game can be arbitrarily set by updating the number of
    available moves (availMoves).  The game is simpler with more available
    moves, and more difficult with fewer.  In addition, the NUM_COLS and
    NUM_ROWS have the converse relationship to difficulty.  That is, larger
    values will present a more challenging board.

    @author Courtney Glosser
 */

public class Board {

    // Board dimensions (in pixels)
    private static final int BOARD_WIDTH = 250;
    private static final int BOARD_HEIGHT = 250;

    // Board orientation on the Panel
    private static final int BOARD_X = 200;
    private static final int BOARD_Y = 25;

    private Ball ball;

    public Board () {
        initBoard();
    }

    /**
        Initialize settings
     */
    private void initBoard() {
        ball = new Ball(BOARD_X, BOARD_Y);
    }

    /**
        Draw the board to the Panel, including drawing the individual tiles.

        @param g2d - The Java Graphics2D library
     */
    public void drawBoard(Graphics2D g2d) {
        g2d.setColor(Color.blue);
        drawBorder(g2d);

        ball.drawBall(g2d);
    }

    /**
        Simple border around the dimensions of the board.
     */
    private void drawBorder(Graphics2D g2d) {
        int x = BOARD_X - 1;
        int y = BOARD_Y - 1;
        int width = BOARD_WIDTH + 2;
        int height = BOARD_HEIGHT + 2;
        g2d.drawRect(x, y, width, height);
    }

}
