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

public class Data implements java.io.Serializable {

    // might need for deserialization
    // private static final long serialVersionUID = 1L;

    private HashMap<String, GenericUser> users;
    private HashSet<String> emails;
    private HashSet<String> ADTs;

    private TreeSet<Book> books;
    private HashMap<String, HashSet<Book>> genreToBooks;
    private HashMap<Book, String> bookToGenre;

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        if (emails.contains(user.getEmail()) || ADTs.contains(user.getADT())) {
            throw new IllegalArgumentException("Το Email ή ΑΔΤ χρησιμοποιείται ήδη");
        }

        users.put(user.getUsername(), user);
        emails.add(user.getEmail());
        ADTs.add(user.getADT());

    }

    public void addBook(Book book, String genre) {
        books.add(book);
        genreToBooks.get(genre).add(book);
        bookToGenre.put(book, genre);
    }

    public GenericUser getUser(String username, String password) {
        GenericUser user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new IllegalArgumentException("Λάθος όνομα χρήστη ή κωδικός πρόσβασης");
    }

    // For setup
    public void addAdmin(Admin admin) {
        users.put(admin.getUsername(), admin);
    }

    public TreeSet<Book> getBooks() {
        return books;
    }

    public HashMap<String, HashSet<Book>> getGenreToBooks() {
        return genreToBooks;
    }
}
