package Api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class DataGettersSetters implements Serializable {

    protected HashMap<String, GenericUser> usernamesToUsers;
    protected HashSet<User> users;
    protected HashSet<String> emails;
    protected HashSet<String> ADTs;

    protected OrderedBookSet books;
    protected HashSet<String> genres;
    protected HashMap<String, OrderedBookSet> genreToBooks;
    protected HashMap<Book, String> bookToGenre;

    public DataGettersSetters() {
        usernamesToUsers = new HashMap<>();
        users = new HashSet<>();
        emails = new HashSet<>();
        ADTs = new HashSet<>();

        books = new OrderedBookSet();
        genres = new HashSet<>();
        genreToBooks = new HashMap<>();
        bookToGenre = new HashMap<>();
    }

    // public static DataGettersSetters load() {
    // return new DataGettersSetters();
    // }

    // public void save() {
    // }

    // ----------------- Getters -----------------
    public HashMap<String, GenericUser> getUsernamesToUsers() {
        return usernamesToUsers;
    }

    public HashSet<User> getUsers() {
        return users;
    }

    public HashSet<String> getEmails() {
        return emails;
    }

    public HashSet<String> getADTs() {
        return ADTs;
    }

    public HashSet<String> getGenres() {
        return genres;
    }

    public OrderedBookSet getBooks() {
        return books;
    }

    public HashMap<Book, String> getBookToGenre() {
        return bookToGenre;
    }

    public HashMap<String, OrderedBookSet> getGenreToBooks() {
        return genreToBooks;
    }

    // ----------------- Setters -----------------
    public void setUsernamesToUsers(HashMap<String, GenericUser> usernamesToUsers) {
        this.usernamesToUsers = usernamesToUsers;
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }

    public void setEmails(HashSet<String> emails) {
        this.emails = emails;
    }

    public void setADTs(HashSet<String> ADTs) {
        this.ADTs = ADTs;
    }

    public void setBooks(OrderedBookSet books) {
        this.books = books;
    }

    public void setGenres(HashSet<String> genres) {
        this.genres = genres;
    }

    public void setBookToGenre(HashMap<Book, String> bookToGenre) {
        this.bookToGenre = bookToGenre;
    }

    public void setGenreToBooks(HashMap<String, OrderedBookSet> genreToBooks) {
        this.genreToBooks = genreToBooks;
    }
}
