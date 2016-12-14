
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener{

    private int score;

    private Snake snake = new Snake();

    // Set basic unit height and width
    private static final int WIDTH = 160;
    private static final int HEIGHT = WIDTH / 12 * 9;

    // Set scale
    private static final int SCALE = 10;

    // Define pixel units
    private static final int WIDTH_UNIT = WIDTH / SCALE;
    private static final int HEIGHT_UNIT = HEIGHT / SCALE;

    // Define board dimentions
    private static final int BOARD_X = WIDTH_UNIT * 5;
    private static final int BOARD_Y = HEIGHT_UNIT * 2;
    private static final int BOARD_WIDTH = WIDTH_UNIT * 30;
    private static final int BOARD_HEIGHT = HEIGHT_UNIT * 30;

    // Defining snake as a grid
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private int dots;

    // Defining time delay for timer.
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    public SnakePanel () {
        System.out.println("Constructing Snake Panel");

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        loadImages();
        initPanel();


        repaint();
    }

    /**
        @TODO:  Re-introduce images when I get package management / jar file
        creation figured out.
     */
    private void loadImages() {

    }

    private void initPanel() {
        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        //@TODO:  Learn more about how this callchain works.  We seem to get
        // Graphics g somewhat "magically"
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.blue);

        int w = getWidth();
        int h = getHeight();

        drawScore(g2d);
        drawGameBoard(g2d);

        moveSnake();
        drawSnake(g2d);

//        Random r = new Random();
    }


    public void drawScore(Graphics2D g2d) {

       g2d.drawString("Score: " + score++, 10, 10); 
       g2d.drawString("Snake Length: " + dots, 10, 40); 

    }

    public void drawGameBoard(Graphics2D g2d) {

       g2d.drawRect(BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT); 

    }

    public void drawSnake(Graphics2D g2d) {
        for (int z = 0; z < dots; z++) {
            if (z == 0) {
                // @TODO:  Make head different color
                g2d.setColor(Color.yellow);
                g2d.fillRect(x[z], y[z], DOT_SIZE, DOT_SIZE);
                g2d.setColor(Color.blue);
            } else {
                g2d.fillRect(x[z], y[z], DOT_SIZE, DOT_SIZE);
            }
        }
    }

    public void moveSnake() {

        if (snake.moveTime() ) {
//            snake.move();
            for (int z = dots; z > 0; z--) {
                x[z] = x[(z - 1)];
                y[z] = y[(z - 1)];
            }

            if (snake.getDirection() == 'a') {
                x[0] -= DOT_SIZE;
            }

            if (snake.getDirection() == 'd') {
                x[0] += DOT_SIZE;
            }

            if (snake.getDirection() == 'w') {
                y[0] -= DOT_SIZE;
            }

            if (snake.getDirection() == 's') {
                y[0] += DOT_SIZE;
                // Temp:  Testing snake growing:
                dots++;
            }
        }

    }

    @Override
    /**
        This implements the necessary functionality for the ActionListener
        abstract interface
     */
    public void actionPerformed(ActionEvent e) {
        // @TODO:  Learn more about this repaint functionality.
        repaint();  
    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("Registered a keyboard button press.");
            snake.keyPressed(e);
        }
    }

}
