



/**
    Main game application class
 */


public class SnakeGame {

    public static final int ACTIVE = 0; // Active game state
    public static final int MENU = 1; // Main Menu
    public static final int PAUSE = 2; // Pause the game
    public static final int WIN = 3;
    public static final int LOSE = 4; 

    public short gameState;

    public boolean gameLoop;
    SnakeFrame sf;

    public int gameLoopCount = 0;

    public SnakeGame() {
        sf = new SnakeFrame();

        gameState = MENU;

        gameLoop = true;

    }

    public static void main (String args[]) {
        System.out.println("Main!");

        SnakeGame sg = new SnakeGame();

        sg.run();
    }

    public void run() {
        sf.setVisible(true);

        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final int OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        while (gameLoop) {
            long now = System.nanoTime();

            if (now - lastLoopTime > OPTIMAL_TIME) {
                lastLoopTime = now; 

                Thread t = new Thread(new Runnable(){
                    public void run() {
                        // @TODO:  Learning more about Threads vs Runnable
                        update();
                    }
                });

                t.start();
                render();
            }
        } 

    }

    public void update() {

    }


    public void render() {
        sf.render();
    }
}
