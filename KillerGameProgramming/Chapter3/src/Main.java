package cglosser;

/**
    Main game application class.  Wanted to define a clear entry point for the
    application.

    @author Courtney Glosser
 */

public class Main {
    public static int DEFAULT_FPS = 80;

    public static void main(String args[]) {
        int fps = DEFAULT_FPS;

        if (args.length != 0) {
            fps = Integer.parseInt(args[0]);
        }

        long period = (long) 1000/fps;
        
        // Start up a "Game" frame to display the window.
        new WormChase(period);
    }

}
