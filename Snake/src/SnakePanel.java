
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;

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

    private Image snakeSegment;

    public SnakePanel () {
        System.out.println("Constructing Snake Panel");

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));


        loadImages();
        repaint();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("snake-seg.png");
        snakeSegment = iid.getImage();

        System.out.println("Got ImageIcon:  " + iid);
        System.out.println("Load Status: " + iid.getImageLoadStatus());
        System.out.println("Got Image:  " + snakeSegment);
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

    }

    public void drawGameBoard(Graphics2D g2d) {

       g2d.drawRect(BOARD_X, BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT); 

    }

    public void drawSnake(Graphics2D g2d) {
        int snakeX = BOARD_X + snake.getX() * WIDTH_UNIT;
        int snakeY = BOARD_Y + snake.getY() * HEIGHT_UNIT;

        g2d.drawImage(snakeSegment, snakeX, snakeY, this);
        g2d.drawImage(snakeSegment, 0, 0, this);

        Toolkit.getDefaultToolkit().sync();
//        g2d.fillRect(snakeX, snakeY, WIDTH_UNIT, HEIGHT_UNIT);
    }

    public void moveSnake() {

        if (snake.moveTime() ) {
            snake.move();
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
