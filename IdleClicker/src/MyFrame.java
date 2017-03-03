package cglosser;

import javax.swing.JFrame;

/**
    Extending the swing JFrame class with My game specific settings.

    @author Courtney Glosser
 */

public class MyFrame extends JFrame implements Runnable {

    private MyPanel mp;

    // Implementing a 16:9 ratio.
    private static final int WIDTH = 640;
    private static final int HEIGHT = 360;

    private boolean done = false;
    private int safety = 0;

    public MyFrame() {

        mp = new MyPanel();
        add(mp);

        setSize(WIDTH, HEIGHT);
        setTitle ("IdleClicker");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);

        gameLoop();
    }

    private void gameLoop() {
        new Thread(this).start();
    }

    /**
        Interpreting from here:
        http://www.java-gaming.org/index.php?topic=24220.0

     */
    public void run() {
        long loopTime = System.currentTimeMillis();
        long secondTime = System.currentTimeMillis();
        int seconds = 0;
        float optimalFPS = 1000/60;

        while (!done) {
            if (System.currentTimeMillis() - secondTime >= 1000) {
                secondTime = System.currentTimeMillis();
                seconds++;
            }
            mp.update(seconds);

            while (System.currentTimeMillis() - loopTime < optimalFPS) {
                Thread.yield();
                try {
                    Thread.sleep(1);
                }
                catch (Exception e) {
                }
            }
            loopTime = System.currentTimeMillis();
        }
    }

}
