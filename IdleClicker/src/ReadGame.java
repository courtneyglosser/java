

package cglosser;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


/**
    Handle reading game state data to file for user

    @author Courtney Glosser
 */

public class ReadGame {
    SaveGame sg;

    public ReadGame() {}

    public void setSave(SaveGame s) {sg = s;}

    public SaveGame getSave() {return sg;}

    public void read() {
        try {
            FileInputStream fileIn = new FileInputStream("save.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            sg = (SaveGame) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Successfully Read");
            System.out.println("Saved Money: " + sg.getMoney());
            System.out.println("Saved Time: " + sg.getTime());
            System.out.println("Saved PS: " + sg.getPerSecond());
        }
        catch (IOException e) {
            System.out.println("Exception!" + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println("Exception!" + e.getMessage());
        }
    }
}
