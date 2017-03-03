
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

    SaveGame() {}

    public void setMoney(int m) {money = m;}
    public void setTime(long t) {time = t;}
    public void setPerSecond(int ps) {perSecond = ps;}

    public int getMoney() {return money;}
    public long getTime() {return time;}
    public int getPerSecond() {return perSecond;}

    
}
