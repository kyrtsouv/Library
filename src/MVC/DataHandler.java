package MVC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import Api.Book;
import Api.DataStorer;
import Api.DataGettersSetters;
import Api.GenericUser;
import Api.OrderedBookSet;
import Api.User;

public class DataHandler extends DataGettersSetters {

    private DataStorer data;

    private transient final ObservableSet<Book> observedBooks;
    private transient final ObservableSet<User> observedUsers;
    private transient final ObservableSet<String> observedGenres;

    public DataHandler(DataStorer data) {
        this.data = data;
        this.usernamesToUsers = data.getUsernamesToUsers();
        this.users = data.getUsers();
        this.emails = data.getEmails();
        this.ADTs = data.getADTs();

        this.books = data.getBooks();
        this.genres = data.getGenres();
        this.genreToBooks = data.getGenreToBooks();
        this.bookToGenre = data.getBookToGenre();

        observedBooks = FXCollections.observableSet(books);
        observedUsers = FXCollections.observableSet(users);
        observedGenres = FXCollections.observableSet(genres);
    }

    public void save() {
        data.setUsernamesToUsers(usernamesToUsers);
        data.setUsers(users);
        data.setEmails(emails);
        data.setADTs(ADTs);

        data.setBooks(books);
        data.setGenres(genres);
        data.setGenreToBooks(genreToBooks);
        data.setBookToGenre(bookToGenre);

        data.save();
    }

    // ----------------- Observable Getters -----------------
    public ObservableSet<Book> getObservedBooks() {
        return observedBooks;
    }

    public ObservableSet<User> getObservedUsers() {
        return observedUsers;
    }

    public ObservableSet<String> getObservedGenres() {
        return observedGenres;
    }

    // ----------------- Adders -----------------
    public void addAdmin(GenericUser admin) {
        usernamesToUsers.put(admin.getUsername(), admin);
    }

    public void addBook(Book book, String genre) {
        books.add(book);
        bookToGenre.put(book, genre);
        genreToBooks.get(genre).add(book);

        // observedBooks.add(book);
    }

    public void addGenre(String genre) {
        genreToBooks.put(genre, new OrderedBookSet());
        genres.add(genre);

        observedGenres.add(genre);
    }

    public void addUser(User user) {
        if (emails.contains(user.getEmail()) || ADTs.contains(user.getADT())) {
            throw new IllegalArgumentException("Το Email ή ΑΔΤ χρησιμοποιείται ήδη");
        }

        usernamesToUsers.put(user.getUsername(), user);
        users.add(user);
        emails.add(user.getEmail());
        ADTs.add(user.getADT());

        observedUsers.add(user);
    }

    public GenericUser getUser(String username, String password) {
        GenericUser user = usernamesToUsers.get(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        throw new IllegalArgumentException("Λάθος όνομα χρήστη ή κωδικός πρόσβασης");
    }

    public void setObservedBooksListener(SetChangeListener<? super Book> listener) {
        observedBooks.addListener((SetChangeListener<? super Book>) listener);
    }
}
