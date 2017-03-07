
package cglosser;

import java.awt.Graphics2D;
import java.awt.Color;
import java.lang.Math;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
    Handle Expand basic Button data and handle income generating asset features

    @author Courtney Glosser
 */

public class AssetButton extends Button {

    BigInteger price;
    BigInteger origPrice;
    BigInteger perSecond;
    double costEscalation;

    boolean display;
    String displayName;

    public AssetButton() {
       super();
    }

    public AssetButton(BigInteger p, BigInteger ps, String dn) {
       super();

        price = p;
        origPrice = p;
        perSecond = ps;
        costEscalation = 2.1;

        displayName = dn;
    }

    public BigInteger getPrice() {return price;}
    public BigInteger getPerSecond() {return perSecond;}
    public boolean getDisplay() {return display;}
    public String getDisplayName() {return displayName;}

    public void setPrice(BigInteger p) {price = p;}
    public void setPerSecond(BigInteger ps) {perSecond = ps;}
    public void setDisplay(boolean d) {display = d;}
    public void setDisplayName(String dn) {displayName = dn;}

    public void showButton(BigInteger money) {
        if (money.compareTo(origPrice) >= 0) {
            display = true;
        }
    }

    public void updatePriceForPurchase() {
        BigDecimal calc = BigDecimal.valueOf(costEscalation);
        BigDecimal calcPrice = new BigDecimal(price.toString());
        calc = calc.multiply(calcPrice);
        calc = calc.setScale(0, RoundingMode.UP);
        price = calc.toBigInteger();
    }

    public void updateForLoad(int numOwned) {
        for (int i = 0; i < numOwned; i++) {
            updatePriceForPurchase();
        }
    }

}
