
import java.awt.EventQueue;
import javax.swing.JFrame;


/**
    Simple Hello World application
 */

public class HelloWorldSwing extends JFrame {

    public HelloWorldSwing() {
        System.out.println("Hello World Swing!");

        add(new MyPanel());

        setSize(350, 200);
        setTitle("Hello World, Swing!");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main (String args[]) {
        EventQueue.invokeLater( new Runnable() {
            @Override
            public void run() {
                HelloWorldSwing hws = new HelloWorldSwing();
                hws.setVisible(true);
            }
        });
    }

}
