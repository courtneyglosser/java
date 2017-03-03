

package cglosser;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


/**
    Handle writing game state data to file for user

    @author Courtney Glosser
 */

public class WriteGame {
    SaveGame sg;

    public WriteGame() {}

    public void setSave(SaveGame s) {sg = s;}

    public SaveGame getSave() {return sg;}

    public void write() {
        try {
            FileOutputStream fileOut = new FileOutputStream("save.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(sg);
            out.close();
            fileOut.close();
            System.out.println("Successfully saved");
        }
        catch (IOException e) {
            System.out.println("Exception!" + e.getMessage());
        }
    }
}
