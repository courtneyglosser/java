
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FillPanel extends JPanel implements ActionListener{
    private Board gameBoard;

    public FillPanel() {
        System.out.println("Constructing the fill panel");

        gameBoard = new Board();

        addMouseListener(new MAdapter());

        setBackground(Color.black);
        setFocusable(true);

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        gameBoard.drawBoard(g2d);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private class MAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
           System.out.println("Mouse pressed; # of clicks: "
                        + e.getClickCount());
            System.out.println("Mouse pressed at: (" + e.getX() + ", "
                        + e.getY() + ")");
            System.out.println("Pressed button: " + e.getButton() );
        }

        public void mouseReleased(MouseEvent e) {
           System.out.println("Mouse released; # of clicks: "
                        + e.getClickCount());
            System.out.println("Mouse released at: (" + e.getX() + ", "
                        + e.getY() + ")");
            System.out.println("released button: " + e.getButton() );
        }
//
//        public void mouseEntered(MouseEvent e) {
//           System.out.println("Mouse entered");
//        }
//
//        public void mouseExited(MouseEvent e) {
//           System.out.println("Mouse exited");
//        }
//
        public void mouseClicked(MouseEvent e) {
           System.out.println("Mouse clicked; # of clicks: "
                        + e.getClickCount());
            System.out.println("Mouse clicked at: (" + e.getX() + ", "
                        + e.getY() + ")");
            System.out.println("clicked button: " + e.getButton() );
        }

    }
}
