
package cglosser;

import java.awt.Graphics2D;
import java.awt.Color;

/**
    Handle Expand basic Button data and handle income generating asset features

    @author Courtney Glosser
 */

public class AssetButton extends Button {

    int price;
    int perSecond;

    public AssetButton() {
       super();
    }

    public AssetButton(int p, int ps) {
       super();

        price = p;
        perSecond = ps;
    }

    public int getPrice() {return price;}
    public int getPerSecond() {return perSecond;}

    public void setPrice(int p) {price = p;}
    public void setPerSecond(int ps) {perSecond = ps;}

    public void updatePriceForPurchase() {
        price = price * 2;
    }
}
