package Api;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.HashSet;

public class Data implements java.io.Serializable {

    // might need for deserialization
    // private static final long serialVersionUID = 1L;

    private HashMap<String, GenericUser> users;
    private HashSet<String> emails;
    private HashSet<String> ADTs;

    private HashSet<Book> books;
    private HashMap<String, HashSet<Book>> genreToBooks;
    private HashMap<Book, String> bookToGenre;

    private Data() {
        users = new HashMap<>();
        emails = new HashSet<>();
        ADTs = new HashSet<>();

        books = new HashSet<>();
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
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("medialab/data.ser"));
            out.writeObject(this);
            out.close();
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

    public HashSet<Book> getBooks() {
        return books;
    }

    // For setup
    public void addAdmin(Admin admin) {
        users.put(admin.getUsername(), admin);
    }
}
