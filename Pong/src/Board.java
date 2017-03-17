
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
    private static final int BOARD_HEIGHT = 260;

    // Board orientation on the Panel
    private static final int BOARD_X = 200;
    private static final int BOARD_Y = 25;

    private Ball ball;

    private Paddle player;
    private Paddle computer;

    private int playerScore = 0;
    private int computerScore = 0;

    public Board () {
        initBoard();
    }

    /**
        Initialize settings
     */
    private void initBoard() {
        ball = new Ball(BOARD_X + 75, BOARD_Y, Color.BLUE);

        player = new Paddle(BOARD_X + 9, BOARD_Y + 80);
        computer = new Paddle(BOARD_X + BOARD_WIDTH - 18, BOARD_Y + 80);
    }

    public void restartBoard() {
        initBoard();
        playerScore = 0;
        computerScore = 0;
    }

    /**
        Update any pertinent board settings (i.e. move the ball)

        @param ticker - a clock item for iteration
     */
    public void updateBoard (int ticker) {
        ball.updateBall(player, computer);

        if (ball.checkXAxisCollision()) {
            // Someone scored:
            if (ball.playerScore()) { playerScore++; }
            if (ball.computerScore()) { computerScore++; }
            initBoard();
        }

        computer.updatePaddle(ball);
    }

    public void playerDown() {
        player.down();
    }

    public void playerUp() {
        player.up();
    }

    /**
        Draw the board to the Panel, including drawing the ball.

        @param g2d - The Java Graphics2D library
     */
    public void drawBoard(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString("Player1: " + playerScore, 50, 200);
        g2d.drawString("Computer: " + computerScore, 50, 220);


        g2d.setColor(Color.blue);
        drawBorder(g2d);

        ball.drawBall(g2d);

        player.drawPaddle(g2d);
        computer.drawPaddle(g2d);
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
