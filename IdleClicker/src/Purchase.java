
package cglosser;

import java.math.BigInteger;

/**
    Class to handle basic information around an asset purchase

    @author Courtney Glosser
 */

public class Purchase {

    BigInteger price;
    BigInteger perSecond;

    void Purhcase() {
        price = new BigInteger("0");
        perSecond = new BigInteger("0");
    }

    void Purchase(BigInteger p, BigInteger ps) {
        price = p;
        perSecond = ps;
    }

    public BigInteger getPrice() { return price; }
    public BigInteger getPerSecond() {return perSecond; }

    public void setPrice(BigInteger p) {price = p;}
    public void setPerSecond(BigInteger ps) {perSecond = ps; }
}
