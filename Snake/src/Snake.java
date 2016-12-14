

import java.awt.event.KeyEvent;

class Snake {

    // Managing snake location
    private int x;
    private int y;

    // Managing snake direction
    private char direction = 'd';

    // Managing snake speed.
    private long moveTimer = System.nanoTime();
    private static final int TARGET_MOVEMENT = 6; // speed per second
    private static final int OPTIMAL_MOVE = 1000000000 / TARGET_MOVEMENT;

    public Snake() {

        x = 0;
        y = 0;

    }

    public Snake(int setX, int setY) {

        x = setX;
        y = setY;

    }

    int getX() { return x; }
    int getY() { return y; }
    int getDirection() {return direction; }

    void setX(int setX) {x = setX;}
    void setY(int setY) {y = setY;}
    void setDirection(char d) {direction = d;}


    boolean moveTime() {
        boolean rtn = false;
        long now = System.nanoTime();

        if (now - moveTimer > OPTIMAL_MOVE) {
            moveTimer = now;
            rtn = true;
        }
        return rtn;
    }

    void move() {

        switch (direction) {
            case 'd':
                x++;
                break;

            case 'a':
                x--;
                break;

            case 'w':
                y--;
                break;

            case 's':
                y++;
                break;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            direction = 'a';
        }

        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            direction = 'd';
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            direction = 'w';
        }

        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            direction = 's';
        }

        System.out.println("Changed Direction to: " + direction);
    }
}
