
package cglosser;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
    Extendings the Swing JPanel class with Fill game specific settings.  Also,
    implementing ActionListener to register and handle mouse click events.
    This class contains the primary Fill game loop, handled as a collaboration
    between the actionPerformed function and the doDrawing function.  The game
    loop is not timer based, and simply awaits user input (actionPerformed),
    calculates the user's impact on the overall game state (within the private
    MAdapter class below), and provides the user an updated game state drawing
    as feedback (doDrawing).

    @author Courtney Glosser
 */

public class ScenesRPGPanel extends JPanel implements ActionListener{
    private String gameState; // welcome, active, win, lose
    private ButtonManager bm;
    private Screen gameScreen;

    public ScenesRPGPanel() {

        gameState = "welcome";
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
            bm.drawBoardButtons(g2d);
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

    /**
        Class extends the MouseAdapter abstract class.  This class will
        register mousePressed events and utilize helper classes to execute
        appropriate game responses.

        @author Courtney Glosser
     */

    private class MAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            if (gameState == "active") {
/**
                if (bm.clickedColoredButton( e.getX(), e.getY() ) == true) {

                    Button clickBtn = bm.registerClick(e.getX(), e.getY());
                    gameBoard.handleClick(clickBtn.getColor());
                    if (gameBoard.checkWin()) {
                        // ASSERT:  Winner!
                        gameState = "win";
                    }
                    else if (gameBoard.checkLose()) {
                        gameState = "lose";
                    }
                    repaint();
                }
                else {
                }
/**/
            }
            if (bm.checkStart(gameState, e.getX(), e.getY())) {
                gameState = "active";
//                gameBoard = new Board();
                repaint();
            }
            if (bm.checkExit(gameState, e.getX(), e.getY())) {
                System.exit(0);
            }
        }

    }
}
