
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class SnakePanel extends JPanel implements ActionListener{

    private int score;

    private Snake snake = new Snake();

    private static final int WIDTH = 160;
    private static final int HEIGHT = WIDTH / 12 * 9;

    private static final int SCALE = 10;

    private static final int WIDTH_UNIT = WIDTH / SCALE;
    private static final int HEIGHT_UNIT = HEIGHT / SCALE;

    public SnakePanel () {
        System.out.println("Constructing Snake Panel");

        addKeyListener(new TAdapter());

        setFocusable(true);

        repaint();
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

        moveSnake();
        drawSnake(g2d);

//        Random r = new Random();
    }


    public void drawScore(Graphics2D g2d) {

       g2d.drawString("Score: " + score++, 10, 10); 

    }

    public void drawSnake(Graphics2D g2d) {
        int snakeX = snake.getX() * WIDTH_UNIT;
        int snakeY = snake.getY() * HEIGHT_UNIT;

        g2d.fillRect(snakeX, snakeY, WIDTH_UNIT, HEIGHT_UNIT);
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
