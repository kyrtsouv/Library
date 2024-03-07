package MVC;

import Api.DataStorer;
import Api.GenericUser;
import Api.OrderedBookSet;
import Api.User;
import Api.Book;

import Gui.Admin.Admin;
import Gui.Admin.AddPanels.AddBook;
import Gui.Admin.ManagementPanels.Books;
import Gui.Admin.ManagementPanels.Genres;
import Gui.Admin.ManagementPanels.Lending;
import Gui.Admin.ManagementPanels.Users;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import Gui.App;
import Gui.UserPane;

import java.util.HashMap;
import java.util.HashSet;

public class Controller {

    private App app;

    private DataHandler dataHandler;

    public Controller(App app) {
        this.app = app;

        dataHandler = new DataHandler(DataStorer.load());
    }

    public void saveData() {
        dataHandler.save();
    }

    // ----------------- Show Panes -----------------

    public void showAddBookBox() {
        app.setView(new AddBook(this), true);
    }

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
        app.setView(new Lending(this), true);
    }

    public void showUserPanel(User user) {
        app.setView(new UserPane(this, user), true);
    }

    public void showAdminPanel() {
        app.setView(new Admin(this), true);
    }

    public void goBack() {
        app.goBack();
    }

    // ----------------- Getters -----------------
    public GenericUser getUser(String username, String password) {
        return dataHandler.getUser(username, password);
    }

    public ObservableSet<Book> getObservedBooks() {
        return dataHandler.getObservedBooks();
    }

    public OrderedBookSet getBooks() {
        return dataHandler.getBooks();
    }

    public HashSet<String> getGenres() {
        return new HashSet<>(dataHandler.getGenreToBooks().keySet());
    }

    public HashMap<String, OrderedBookSet> getGenreToBooks() {
        return dataHandler.getGenreToBooks();
    }

    // ----------------- Adders -----------------
    public void addBook(Book book, String genre) {
        dataHandler.addBook(book, genre);
    }

    public void addUser(User user) {
        dataHandler.addUser(user);
    }

    public void addGenre(String genre) {
        dataHandler.addGenre(genre);
    }

    public void setObservedBooksListener(SetChangeListener<? super Book> listener) {
        dataHandler.setObservedBooksListener(listener);
    }

}
