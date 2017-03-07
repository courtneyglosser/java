
package cglosser;

import java.io.Serializable;
import java.math.BigInteger;


/**
    Handle serializing and saving game state data for the user.

    @author Courtney Glosser
 */

public class SaveGame implements Serializable {
    public BigInteger money;
    public long time;
    public BigInteger perSecond;

    public int numSingles;
    public int numTens;
    public int numHundreds;
    public int numThousands;
    public int numTenKs;

    SaveGame() {}

    public void setMoney(BigInteger m) {money = m;}
    public void setPerSecond(BigInteger ps) {perSecond = ps;}
    public void setTime(long t) {time = t;}
    public void setNumSingles(int s) {numSingles = s;}
    public void setNumTens(int t) {numTens = t;}
    public void setNumHundreds(int h) {numHundreds = h;}
    public void setNumThousands(int t) {numThousands = t;}
    public void setNumTenKs(int t) {numTenKs = t;}

    public BigInteger getMoney() {return money;}
    public BigInteger getPerSecond() {return perSecond;}
    public long getTime() {return time;}
    public int getNumSingles() {return numSingles;}
    public int getNumTens() {return numTens;}
    public int getNumHundreds() {return numHundreds;}
    public int getNumThousands() {return numThousands;}
    public int getNumTenKs() {return numTenKs;}

    
}
