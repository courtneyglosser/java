
package cglosser;

/**
    Class to handle basic information around an asset purchase

    @author Courtney Glosser
 */

public class Purchase {

    int price;
    int perSecond;

    void Purhcase() {
        price = perSecond = 0;
    }

    void Purchase(int p, int ps) {
        price = p;
        perSecond = ps;
    }

    public int getPrice() { return price; }
    public int getPerSecond() {return perSecond; }

    public void setPrice(int p) {price = p;}
    public void setPerSecond(int ps) {perSecond = ps; }
}
