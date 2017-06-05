
package cglosser;


/**
    Player character implementation

    @author Courtney Glosser
 */

public class Player {

    private String name;
    private int level;
    private int xp;
    private int hpmax;
    private int hpcurr;
    private int mpmax;
    private int mpcurr;
    private int damage;
    private int evade;
    private int armor;
    private int chanceToHit;
    private int money;


    public Player() {

        initPlayer();

    }

    public void initPlayer() {

    }

    public String getName() {return name;}
    public int getLevel() {return level;}
    public int getXp() {return xp;}
    public int getHpmax() {return hpmax;}
    public int getHpcurr() {return hpcurr;}
    public int getMpmax() {return mpmax;}
    public int getMpcurr() {return mpcurr;}
    public int getDamage() {return damage;}
    public int getEvade() {return evade;}
    public int getArmor() {return armor;}
    public int getChanceToHit() {return chanceToHit;}
    public int getMoney() {return money;}


    public void setName(String name) {this.name = name;}
    public void setLevel(int level) {this.level = level;}
    public void setXp(int xp) {this.xp = xp;}
    public void setHpmax(int hpmax) {this.hpmax = hpmax;}
    public void setHpcurr(int hpcurr) {this.hpcurr = hpcurr;}
    public void setMpmax(int mpmax) {this.mpmax = mpmax;}
    public void setMpcurr(int mpcurr) {this.mpcurr = mpcurr;}
    public void setDamage(int damage) {this.damage = damage;}
    public void setEvade(int evade) {this.evade = evade;}
    public void setArmor(int armor) {this.armor = armor;}
    public void setChanceToHit (int chanceToHit) {
        this.chanceToHit = chanceToHit;
    }
    public void setMoney(int money) {this.money = money;}

    
}
