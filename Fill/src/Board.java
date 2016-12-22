
import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;
import java.util.Random;

/**
    A manager class that will handle drawing the game board, including a list
of tiles that will be multiple colors.
 */

public class Board {

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
    private Color[] colors = new Color[6];

    // Ranodmize Colors
    private Random rand = new Random();

    public Board () {
        initBoard();
    }

    public void initBoard() {
        colors[0] = Color.cyan;
        colors[1] = Color.yellow;
        colors[2] = Color.red;
        colors[3] = Color.green;
        colors[4] = Color.magenta;
        colors[5] = Color.lightGray;

        // Loop through array of tiles and randomize colors
        for (int i = 0; i < NUM_TILES; i++) {
            tiles[i] = new Tile();
            tiles[i].setColor(colors[rand.nextInt(6)]);
        }

    }

    public void drawBoard(Graphics2D g2d) {
        g2d.setColor(Color.blue);
        drawBorder(g2d);

        for (int i = 0; i < NUM_TILES; i++) {
            int x = BOARD_X + (getTileX(i) * TILE_WIDTH);
            int y = BOARD_Y + (getTileY(i) * TILE_HEIGHT);

            g2d.setColor (tiles[i].getColor());

            g2d.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
        }
    }

    private void drawBorder(Graphics2D g2d) {
        int x = BOARD_X - 1;
        int y = BOARD_Y - 1;
        int width = BOARD_WIDTH + 2;
        int height = BOARD_HEIGHT + 2;
        g2d.drawRect(x, y, width, height);
    }

    private int getTileX(int idx) {
        int rawPos = idx % NUM_COLS;
        return rawPos;
    }


    private int getTileY(int idx) {
        int rawPos = (int)Math.floor(idx / NUM_COLS);

        return rawPos;
    }
}
