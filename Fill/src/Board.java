
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

    // Track available moves to determine failure state
    private int availMoves;

    // Board dimensions (in pixels)
    private static final int BOARD_WIDTH = 250;
    private static final int BOARD_HEIGHT = 250;

    // Board orientation on the Panel
    private static final int BOARD_X = 200;
    private static final int BOARD_Y = 25;

    // Board configuration (size of grid)
    private static final int NUM_COLS = 10;
    private static final int NUM_ROWS = 10;
    private static final int NUM_TILES = NUM_COLS * NUM_ROWS;

    // Tiles' width and height based on configuratino
    private static final int TILE_WIDTH = BOARD_WIDTH / NUM_COLS;
    private static final int TILE_HEIGHT = BOARD_WIDTH / NUM_ROWS;

    // Arry of tiles
    private Tile[] tiles = new Tile[NUM_TILES];

    // Available Colors
    private ColorManager cm;

    // Ranodmize Colors
    private Random rand = new Random();

    public Board () {
        initBoard();
    }

    /**
        Initialize settings
     */
    private void initBoard() {
        availMoves = 30;
        cm = new ColorManager();

        // Loop through array of tiles and randomize colors
        for (int i = 0; i < NUM_TILES; i++) {
            tiles[i] = new Tile();
            tiles[i].setColor(cm.getColor(rand.nextInt(6)));
            tiles[i].setChecked(false);
        }

    }

    /**
        Draw the board to the Panel, including drawing the individual tiles.

        @param g2d - The Java Graphics2D library
     */
    public void drawBoard(Graphics2D g2d) {
        g2d.setColor(Color.blue);
        drawBorder(g2d);

        for (int i = 0; i < NUM_TILES; i++) {
            int x = BOARD_X + (getTileX(i) * TILE_WIDTH);
            int y = BOARD_Y + (getTileY(i) * TILE_HEIGHT);

            g2d.setColor (tiles[i].getColor());

            g2d.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
        }

        g2d.setColor(Color.white);

        g2d.drawString("Moves: " + availMoves, 500, 30);

    }

    /**
        Update the state of the board to reflect a new clicked color.  Colors
        spread until they reach a new color.  handleClick will set up the
        appropriate variables and settings to initiate the recursive call to
        colorNeighbors.

        @param clr  The new color the user clicked.
     */
    public void handleClick(Color clr) {
        // ASSERT:  User clicked a color.  Update the array of tiles to
        // reflect the change.
        Color origColor = tiles[0].getColor();
        tiles[0].setColor(clr);
        tiles[0].setChecked(true);

        colorNeighbors(0, origColor, clr);
        resetChecks();
        availMoves--;
    }

    /**
        Look for win state. If all tiles are the same color, winner!

        @return <code>true</code> if the board is all one color.
                <code>false</code> otherwise
     */
    public boolean checkWin() {
        boolean rtn = true;
        Color checkColor = tiles[0].getColor();

        for (int i = 0; i < NUM_TILES; i++) {
            if (tiles[i].getColor() != checkColor) {
                rtn = false;
            }
        }

        return rtn;
    }

    /**
        Look for loss state. If all "availMoves" move exhausted, loser.

        @return <code>true</code> if the user has exhauted all their moves
                <code>false</code> otherwise
     */
    public boolean checkLose() {
        boolean rtn = false;

        if (availMoves <= 0) {
            rtn = true;
        }

        return rtn;
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

    /**
        A mapping service to simplify the one dimensional array of Tiles into a
        system of x,y coordinates.

        @param idx tile's array index
        @return the tile's position within its row on the board 
     */
    private int getTileX(int idx) {
        int rawPos = idx % NUM_COLS;
        return rawPos;
    }


    /**
        A mapping service to simplify the one dimensional array of Tiles into a
        system of x,y coordinates.

        @param idx tile's array index
        @return the tile's position within its column on the board 
     */
    private int getTileY(int idx) {
        int rawPos = (int)Math.floor(idx / NUM_COLS);

        return rawPos;
    }

    /**
        Recursive function to "spread" the newly selected color across the
        board.  If the tile neighboring the <code>idx</code> tile (in the rows
        above and below, or the columns to the left or right) match the current
        color (<code>origColor</code>), then change them to the new color
        (<code>clr</code>), and make a recursive call to check _that_ tile's
        neighbors.  Each tile's "checked" state is tracked, and the recursion
        ends when all applicable neighbors have been "checked".

        @param idx represents the tile's place in the Board's array of Tiles
        @param origColor the color on the board before the user's click.
        @param clr the new color that the user's chosen to "spread"
      */
    private void colorNeighbors(int idx, Color origColor, Color clr) {
        // ASSERT:  this tile has been checked.
        tiles[idx].setChecked(true);
        if (idx > NUM_COLS) {
            // ASSERT:  Not on the top row, need to check "up"
            if (tiles[idx - NUM_COLS].getColor() == origColor
                && !tiles[idx - NUM_COLS].getChecked()) {
                tiles[idx - NUM_COLS].setColor(clr);
                colorNeighbors(idx-NUM_COLS, origColor, clr);
            }
        }

        if (idx % NUM_COLS != NUM_COLS - 1) {
            // ASSERT:  Not on the column furtherst to the right, check right.
            if (tiles[idx+1].getColor() == origColor
                && !tiles[idx+1].getChecked()) {
                tiles[idx+1].setColor(clr);
                colorNeighbors(idx+1, origColor, clr);
            }
        }

        if (idx + NUM_COLS < NUM_TILES) {
            // ASSERT:  Not on the bottome row, check below.
            if (tiles[idx + NUM_COLS].getColor() == origColor
                && !tiles[idx + NUM_COLS].getChecked()) {
                tiles[idx + NUM_COLS].setColor(clr);
                colorNeighbors(idx + NUM_COLS, origColor, clr);
            }
        }

        if (idx % NUM_COLS > 0) {
            // ASSERT:  This cannot be the furthest left, check left.
            if (tiles[idx-1].getColor() == origColor
                && !tiles[idx-1].getChecked()) {
                tiles[idx-1].setColor(clr);
                colorNeighbors(idx-1, origColor, clr);
            }
        }
    }

    /**
        Ensure all tiles are ready to receive another click.
     */
    private void resetChecks() {
        for (int i = 0; i < NUM_TILES; i++) {
            tiles[i].setChecked(false);
        }
    }
}
