
package cglosser;

import java.awt.Graphics2D;
import java.awt.Color;

/**
    Handle Expand basic Button data and handle income generating asset features

    @author Courtney Glosser
 */

public class AssetButton extends Button {

    int price;
    int origPrice;
    int perSecond;

    boolean display;
    String displayName;

    public AssetButton() {
       super();
    }

    public AssetButton(int p, int ps, String dn) {
       super();

        price = p;
        origPrice = p;
        perSecond = ps;

        displayName = dn;
    }

    public int getPrice() {return price;}
    public int getPerSecond() {return perSecond;}
    public boolean getDisplay() {return display;}
    public String getDisplayName() {return displayName;}

    public void setPrice(int p) {price = p;}
    public void setPerSecond(int ps) {perSecond = ps;}
    public void setDisplay(boolean d) {display = d;}
    public void setDisplayName(String dn) {displayName = dn;}

    public void showButton(int money) {
        if (money > origPrice) {
            display = true;
        }
    }

    public void updatePriceForPurchase() {
        price = price * 2;
    }

}
