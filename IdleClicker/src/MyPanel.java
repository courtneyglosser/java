
package cglosser;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;

/**
    Extendings the Swing JPanel class with My game specific settings.  Also,
    implementing ActionListener to register and handle mouse click events.
    This class contains the primary game loop, handled as a collaboration
    between the actionPerformed function and the doDrawing function. 

    @author Courtney Glosser
 */

public class MyPanel extends JPanel implements ActionListener{
    private String gameState; // welcome, active, win, lose
    private ButtonManager bm;
    private Screen gameScreen;

    private int count;
    private int seconds;
    private BigInteger perSecond;
    private BigInteger money;
    private BigInteger checkTen;
    private BigInteger checkHundred;
    private BigInteger checkThousand;
    private BigInteger checkTenK;

    // 0 => Singles, 1 => tens, etc
    private int[] assets = {0, 0, 0, 0, 0};

    public MyPanel() {

        gameState = "welcome";
        bm = new ButtonManager();
        gameScreen = new Screen();

        addMouseListener(new MAdapter());

        setBackground(Color.black);
        setFocusable(true);

        count = seconds = 0;
        perSecond = new BigInteger("0");
        money = new BigInteger("1");
        checkTen = new BigInteger("10");
        checkHundred = new BigInteger("100");
        checkThousand = new BigInteger("1000");
        checkTenK = new BigInteger("10000");


        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.drawString("Count: " + count, 20, 20);
        g2d.drawString("Seconds: " + seconds, 20, 50);
        if (seconds > 0) {
            g2d.drawString("FPS: " + count / seconds, 200, 20);
        }
        
        g2d.drawString("Money: " + money, 200, 50);
        g2d.drawString("Per Second: " + perSecond, 200, 80);

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

    public void update(int updateSeconds) {
        if (seconds != updateSeconds) {
            // ASSERT:  ticked over a second:
            money = money.add(perSecond);
            seconds = updateSeconds;
            updateButtons();
        }
        else {
        }
        count++;
        repaint();
    }

    private void updateButtons() {
        if (!bm.checkTens() && money.compareTo(checkTen) >= 0) {
            bm.showTens();
        }
        if (!bm.checkHundreds() && money.compareTo(checkHundred) >= 0) {
            bm.showHundreds();
        }
        if (!bm.checkThousands() && money.compareTo(checkThousand) >= 0) {
            bm.showThousands();
        }
        if (!bm.checkTenK() && money.compareTo(checkTenK) >= 0) {
            bm.showTenK();
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
            try {
                if (gameState == "active") {
                    Purchase myPurchase = bm.doPurchase(e.getX(), e.getY(), money);
                    BigInteger comp = new BigInteger("0");
                    BigInteger tmp = myPurchase.getPerSecond();
                    if (tmp != null) {
                        int out = tmp.compareTo(comp);
                        if (tmp.compareTo(comp) > 0) {
                            perSecond = perSecond.add(myPurchase.getPerSecond());
                            BigInteger calc = myPurchase.getPrice();
                            money = money.subtract(calc);
                            calc = myPurchase.getPerSecond();
                            if(calc.compareTo(BigInteger.valueOf(1)) == 0) {
                                assets[0]++;
                            }
                            if(calc.compareTo(BigInteger.valueOf(10)) == 0) {
                                assets[1]++;
                            }
                            if(calc.compareTo(BigInteger.valueOf(100)) == 0) {
                                assets[2]++;
                            }
                            if(calc.compareTo(BigInteger.valueOf(1000)) == 0) {
                                assets[3]++;
                            }
                            if(calc.compareTo(BigInteger.valueOf(10000)) == 0) {
                                assets[4]++;
                            }
                        }
                    }
/**/
                }
                if (bm.checkStart(gameState, e.getX(), e.getY())) {
                    gameState = "active";
                    // Testing save game reading / writing
                    SaveGame sg = new SaveGame();
                    sg.setMoney(money);
                    sg.setPerSecond(perSecond);
                    sg.setTime(System.currentTimeMillis());
                    sg.setNumSingles(assets[0]);
                    sg.setNumTens(assets[1]);
                    sg.setNumHundreds(assets[2]);
                    sg.setNumThousands(assets[3]);
                    sg.setNumTenKs(assets[4]);

                    WriteGame wg = new WriteGame();
                    wg.setSave(sg);
                    wg.write();

                    repaint();
                }
                if (bm.checkExit(gameState, e.getX(), e.getY())) {
                    System.exit(0);
                }
                if (bm.checkContinue(gameState, e.getX(), e.getY())) {

                    ReadGame rg = new ReadGame();
                    rg.read();
                    SaveGame sg = rg.getSave();

                    long tmp = System.currentTimeMillis() - sg.getTime();
                    tmp = (long) tmp / 1000;

                    BigInteger elapsedSeconds = BigInteger.valueOf(tmp);

                    perSecond = sg.getPerSecond();
                    money = sg.getMoney();

                    BigInteger calc = perSecond.multiply(elapsedSeconds);
                    money = money.add(calc);
                    assets[0] = sg.getNumSingles();
                    assets[1] = sg.getNumTens();
                    assets[2] = sg.getNumHundreds();
                    assets[3] = sg.getNumThousands();
                    assets[4] = sg.getNumTenKs();

                    for (int i = 0; i < assets.length; i++) {
                        bm.updateButtonForLoad(i, assets[i]);
                    }
                }
            }
            catch (Exception ex) {
                System.out.println("Message: " + ex.getMessage());
                System.out.println("Cause? " + ex.getCause());
                System.out.println("Stack: ");
                ex.printStackTrace();
            }
        }

    }
}
