
package cglosser;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FillPanel extends JPanel implements ActionListener{
    private String gameState; // welcome, active, win, lose
    private Board gameBoard;
    private ButtonManager bm;
    private Screen gameScreen;

    public FillPanel() {
        System.out.println("Constructing the Fill panel");

        gameState = "welcome";
        gameBoard = new Board();
        bm = new ButtonManager();
        gameScreen = new Screen();

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

        if (gameState == "active") {
            gameBoard.drawBoard(g2d);
            bm.drawButtons(g2d);
        }
        else if (gameState == "welcome") {
            // Draw a welcome screen
            gameScreen.drawWelcome(g2d);
        }
        else if (gameState == "win") {
            // Draw a win screen
            gameScreen.drawWin(g2d);
        }
        else if (gameState == "lose") {
            // Draw a lose screen
            gameScreen.drawLose(g2d);
        }

    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private class MAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            if (gameState == "active") {
                if (bm.clickedButton( e.getX(), e.getY() ) == true) {
                    System.out.println("Clicked a button!");

                    Button clickBtn = bm.registerClick(e.getX(), e.getY());

                    if (gameBoard.handleClick(clickBtn.getColor())) {
                        // ASSERT:  Winner!
                        gameState = "win";
                        System.out.println("Winner!!");
                    }
                    else if (gameBoard.checkLose()) {
                        gameState = "lose";
                        System.out.println("Loser!!");
                    }
                    repaint();
                }
                else {
                    System.out.println("Did not click a button!");
                }
            }
            if (gameScreen.checkStart(gameState, e.getX(), e.getY())) {
                gameState = "active";
                gameBoard = new Board();
                repaint();
            }
            if (gameScreen.checkExit(gameState, e.getX(), e.getY())) {
                System.exit(0);
            }
        }

    }
}
