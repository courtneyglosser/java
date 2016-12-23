
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
    private ButtonManager bm;

    public FillPanel() {
        System.out.println("Constructing the Fill panel");

        gameBoard = new Board();
        bm = new ButtonManager();

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
        bm.drawButtons(g2d);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private class MAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            if (bm.clickedButton( e.getX(), e.getY() ) == true) {
                System.out.println("Clicked a button!");

                Button clickBtn = bm.registerClick(e.getX(), e.getY());

                if (clickBtn.getColor() != Color.black) {
                    System.out.println("Got a colored button!");
                    // TODO:  Change color!
                    gameBoard.handleClick(clickBtn.getColor());
                    repaint();
                }
                else {
                    // ASSERT:  Convention to set New / Exit buttons as black
                    System.out.println("Got a new / exit button!");
                }
            }
            else {
                System.out.println("Did not click a button!");
            }
        }

    }
}
