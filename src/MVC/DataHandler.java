package MVC;

import java.util.HashMap;
import java.util.HashSet;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import Api.Book;
import Api.Data;
import Api.GenericUser;
import Api.User;
import Api.DataGettersSetters;

public class DataHandler extends DataGettersSetters {

    private Data data;

    private transient final SimpleObjectProperty<ObservableMap<String, ObservableSet<Book>>> gtbProperty;

    public DataHandler(Data data) {
        this.data = data;
        users = data.getUsers();
        emails = data.getEmails();
        ADTs = data.getADTs();
        books = data.getBooks();
        genreToBooks = data.getGenreToBooks();
        bookToGenre = data.getBookToGenre();

        ObservableMap<String, ObservableSet<Book>> observableMap = FXCollections.observableHashMap();
        genreToBooks.forEach((genre, books) -> {
            ObservableSet<Book> observableSet = FXCollections.observableSet(books);
            observableMap.put(genre, observableSet);
        });

        gtbProperty = new SimpleObjectProperty<>(observableMap);
    }

    public void save() {
        HashMap<String, HashSet<Book>> genreToBooks = new HashMap<>();

        data.setUsers(users);
        data.setEmails(emails);
        data.setADTs(ADTs);
        data.setBooks(books);
        data.setGenreToBooks(genreToBooks);
        data.setBookToGenre(bookToGenre);
        data.save();
    }

    public void addUser(User user) {
        if (emails.contains(user.getEmail()) || ADTs.contains(user.getADT())) {
            throw new IllegalArgumentException("Το Email ή ΑΔΤ χρησιμοποιείται ήδη");
        }

        users.put(user.getUsername(), user);
        emails.add(user.getEmail());
        ADTs.add(user.getADT());
    }

    public void addAdmin(GenericUser admin) {
        users.put(admin.getUsername(), admin);
    }

    public void addBook(Book book, String genre) {
        books.add(book);
        bookToGenre.put(book, genre);
        genreToBooks.get(genre).add(book);

        gtbProperty.get().get(genre).add(book);
    }

    public void addGenre(String genre) {
        genreToBooks.put(genre, new HashSet<>());
    }

    public GenericUser getUser(String username, String password) {
        GenericUser user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new IllegalArgumentException("Λάθος όνομα χρήστη ή κωδικός πρόσβασης");
    }

    public SimpleObjectProperty<ObservableMap<String, ObservableSet<Book>>> getGtbProperty() {
        return gtbProperty;
    }

}
