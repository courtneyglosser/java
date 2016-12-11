


class Snake {

    // Managing snake location
    private int x;
    private int y;

    // Managing snake direction
    private char direction = 'd';

    // Managing snake speed.
    private long moveTimer = System.nanoTime();
    private static final int TARGET_MOVEMENT = 3; // speed per second
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

    void setX(int setX) {x = setX;}
    void setY(int setY) {y = setY;}


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
}
