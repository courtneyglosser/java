
import java.awt.Color;

/**
    Each individual block of color to fill.
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

    public String testString() {return "test"; }
}
