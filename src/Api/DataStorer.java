package Api;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.HashMap;
import java.util.HashSet;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DataStorer extends DataGettersSetters {

    // might need for deserialization
    // private static final long serialVersionUID = 1L;

    private DataStorer() {
        usernamesToUsers = new HashMap<>();
        emails = new HashSet<>();
        ADTs = new HashSet<>();

        books = new OrderedBookSet();
        genreToBooks = new HashMap<>();
        bookToGenre = new HashMap<>();
    }

    // ----------------- Serialization -----------------
    public static DataStorer load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("medialab/data.ser"));
            DataStorer data = (DataStorer) in.readObject();
            in.close();
            return data;
        } catch (Exception e) {
            return new DataStorer();
        }
    }

    public void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("medialab/tempData.ser"));
            out.writeObject(this);
            out.close();

            Files.copy(Paths.get("medialab/tempData.ser"), Paths.get("medialab/data.ser"),
                    StandardCopyOption.REPLACE_EXISTING);

            Files.delete(Paths.get("medialab/tempData.ser"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
