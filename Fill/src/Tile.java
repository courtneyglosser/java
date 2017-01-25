
package cglosser;

import java.awt.Color;

/**
    The Tile class represents each individual tile of color on the game board
    that requires filling to complete the game.

    @author Courtney Glosser
 */

public class Tile {

    private Color tileColor;
    private boolean checked;

    public Tile() {

    }

    public Color getColor() { return tileColor; }
    public boolean getChecked() { return checked; }

    public void setColor(Color inColor) { tileColor = inColor; }
    public void setChecked(boolean inChecked) { checked = inChecked; }
}
