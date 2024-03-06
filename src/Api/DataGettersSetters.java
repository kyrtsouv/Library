package Api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class DataGettersSetters implements Serializable {

    protected HashMap<String, GenericUser> users;
    protected HashSet<String> emails;
    protected HashSet<String> ADTs;

    protected TreeSet<Book> books;
    protected HashMap<String, HashSet<Book>> genreToBooks;
    protected HashMap<Book, String> bookToGenre;

    public DataGettersSetters() {
        users = new HashMap<>();
        emails = new HashSet<>();
        ADTs = new HashSet<>();

        books = new TreeSet<>();
        genreToBooks = new HashMap<>();
        bookToGenre = new HashMap<>();
    }

    public static DataGettersSetters load() {
        return new DataGettersSetters();
    }

    public void save() {
    }

    // ----------------- Getters -----------------
    public HashMap<String, GenericUser> getUsers() {
        return users;
    }

    public HashSet<String> getEmails() {
        return emails;
    }

    public HashSet<String> getADTs() {
        return ADTs;
    }

    public TreeSet<Book> getBooks() {
        return books;
    }

    public HashMap<Book, String> getBookToGenre() {
        return bookToGenre;
    }

    public HashMap<String, HashSet<Book>> getGenreToBooks() {
        return genreToBooks;
    }

    // ----------------- Setters -----------------
    public void setUsers(HashMap<String, GenericUser> users) {
        this.users = users;
    }

    public void setEmails(HashSet<String> emails) {
        this.emails = emails;
    }

    public void setADTs(HashSet<String> ADTs) {
        this.ADTs = ADTs;
    }

    public void setBooks(TreeSet<Book> books) {
        this.books = books;
    }

    public void setBookToGenre(HashMap<Book, String> bookToGenre) {
        this.bookToGenre = bookToGenre;
    }

    public void setGenreToBooks(HashMap<String, HashSet<Book>> genreToBooks) {
        this.genreToBooks = genreToBooks;
    }
}
