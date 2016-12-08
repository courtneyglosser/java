
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;


public class SnakePanel extends JPanel{

    private int score;

    private int[] snakePos = {1};

    private static final int WIDTH = 160;
    private static final int HEIGHT = WIDTH / 12 * 9;

    private static final int SCALE = 10;

    private static final int WIDTH_UNIT = WIDTH / SCALE;
    private static final int HEIGHT_UNIT = HEIGHT / SCALE;

    public SnakePanel () {
        System.out.println("Constructing Snake Panel");

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

        drawSnake(g2d);

//        Random r = new Random();
//
//        for (int i = 0; i < 2000; i++) {
//
//            int x = Math.abs(r.nextInt()) % w;
//            int y = Math.abs(r.nextInt()) % h;
//            g2d.drawLine(x, y, x, y);
//            g2d.fillRect(100, 100, WIDTH_UNIT, HEIGHT_UNIT);
//            
//        }
    }


    public void drawScore(Graphics2D g2d) {

       g2d.drawString("Score: " + score++, 10, 10); 

    }

    public void drawSnake(Graphics2D g2d) {
        for(int i = 0; i < snakePos.length; i++) {
            System.out.println("Snake array val: " + snakePos[i]);
            g2d.fillRect(snakePos[i] * WIDTH_UNIT, snakePos[i] * HEIGHT_UNIT, WIDTH_UNIT, HEIGHT_UNIT);
        }

    }

    public void moveSnake() {

    }
}
