
package cglosser;

import java.io.Serializable;


/**
    Handle serializing and saving game state data for the user.

    @author Courtney Glosser
 */

public class SaveGame implements Serializable {
    public int money;
    public long time;
    public int perSecond;

    public int numSingles;
    public int numTens;
    public int numHundreds;
    public int numThousands;
    public int numTenKs;

    SaveGame() {}

    public void setMoney(int m) {money = m;}
    public void setTime(long t) {time = t;}
    public void setPerSecond(int ps) {perSecond = ps;}
    public void setNumSingles(int s) {numSingles = s;}
    public void setNumTens(int t) {numTens = t;}
    public void setNumHundreds(int h) {numHundreds = h;}
    public void setNumThousands(int t) {numThousands = t;}
    public void setNumTenKs(int t) {numTenKs = t;}

    public int getMoney() {return money;}
    public long getTime() {return time;}
    public int getPerSecond() {return perSecond;}
    public int getNumSingles() {return numSingles;}
    public int getNumTens() {return numTens;}
    public int getNumHundreds() {return numHundreds;}
    public int getNumThousands() {return numThousands;}
    public int getNumTenKs() {return numTenKs;}

    
}
