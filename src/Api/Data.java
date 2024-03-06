package Api;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import java.util.Comparator;
import java.io.Serializable;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Data extends DataGettersSetters implements java.io.Serializable {

    // might need for deserialization
    // private static final long serialVersionUID = 1L;

    private class BookComparator implements Comparator<Book>, Serializable {

        private static final long serialVersionUID = 1L;

        @Override
        public int compare(Book b1, Book b2) {
            return b2.getRating() - b1.getRating();
        }
    }

    private Data() {
        users = new HashMap<>();
        emails = new HashSet<>();
        ADTs = new HashSet<>();

        books = new TreeSet<>(new BookComparator());
        genreToBooks = new HashMap<>();
        bookToGenre = new HashMap<>();
    }

    // ----------------- Serialization -----------------
    public static Data load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("medialab/data.ser"));
            Data data = (Data) in.readObject();
            in.close();
            return data;
        } catch (Exception e) {
            return new Data();
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
