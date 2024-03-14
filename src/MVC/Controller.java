package MVC;

import Api.GenericUser;
import Api.Lending;
import Api.Review;
import Api.User;
import Api.Book;

import Gui.Admin.ADashboard;
import Gui.Admin.ManagementPanes.Books;
import Gui.Admin.ManagementPanes.Genres;
import Gui.Admin.ManagementPanes.Lendings;
import Gui.Admin.ManagementPanes.Users;
import Gui.User.BookInfoPane;
import Gui.User.LendingsPane;
import Gui.User.SearchPane;
import Gui.User.UDashboard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The Controller class is responsible for managing the interactions between the
 * user interface and the data model in the library application.
 * It handles user actions and updates the data accordingly.
 * 
 * It also contains the data model.
 * 
 * It has methods for:
 * 1) showing the different panes of the application
 * 2) getting the data from the Data object and
 * 3) adding, updating and deleting data.
 */
public class Controller {

    private App app;

    private Data data;

    public Controller(App app) {
        this.app = app;

        data = Data.load();
    }

    public void saveData() {
        data.save();
    }

    // ----------------- Show Panes -----------------
    public void showBooksManagerPane() {
        app.setView(new Books(this), true);
    }

    public void showGenresManagerPane() {
        app.setView(new Genres(this), true);
    }

    public void showUsersManagerPane() {
        app.setView(new Users(this), true);
    }

    public void showLendingManagerPane() {
        app.setView(new Lendings(this), true);
    }

    public void showUserPanel(User user) {
        app.setView(new UDashboard(this, user), true);
    }

    public void showAdminPanel() {
        app.setView(new ADashboard(this), true);
    }

    public void showSearchPane(User user) {
        app.setView(new SearchPane(this, user), true);
    }

    public void showLendingsPane(User user) {
        app.setView(new LendingsPane(this, user), true);
    }

    public void showBookInfoPane(User user, Book book) {
        app.setView(new BookInfoPane(book), true);
    }

    public void goBack() {
        app.goBack();
    }

    // ----------------- Getters -----------------
    public GenericUser getUser(String username, String password) {
        return data.getUser(username, password);
    }

    public HashSet<Book> getBooks() {
        return data.getBooks();
    }

    public Set<String> getGenres() {
        return data.getGenreToBooks().keySet();
    }

    public HashMap<String, HashSet<Book>> getGenreToBooks() {
        return data.getGenreToBooks();
    }

    public Set<User> getUsers() {
        return data.getUsernamesToUsers().values().stream().filter(user -> user instanceof User)
                .map(userPane -> (User) userPane)
                .collect(Collectors.toSet());
    }

    public HashSet<Lending> getLendings() {
        return data.getLendings();
    }

    public HashSet<Book> searchBooks(String title, String author, String year) {
        return data.searchBooks(title, author, year);
    }

    // ----------------- Adders -----------------
    public void addBook(Book book) {
        data.addBook(book);
    }

    public void addUser(User user) {
        data.addUser(user);
    }

    public void addGenre(String genre) {
        data.addGenre(genre);
    }

    public void addReview(Book book, User user, Review review) {
        data.addReview(book, user, review);
    }

    // ----------------- Updaters -----------------
    public void updateGenre(String oldGenre, String newGenre) {
        data.updateGenre(oldGenre, newGenre);
    }

    public void updateBook(Book oldBook, Book newBook) {
        data.updateBook(oldBook, newBook);
    }

    public void updateUser(User oldUser, User newUser) {
        data.updateUser(oldUser, newUser);
    }

    public void lendBook(Book book, User user) {
        data.lendBook(book, user);
    }

    public void deleteBook(Book book) {
        data.deleteBook(book);
    }

    public void deleteUser(User user) {
        data.deleteUser(user);
    }

    public void deleteLending(Lending lending) {
        data.deleteLending(lending);
    }
}
