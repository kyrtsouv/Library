package MVC;

import Api.Data;
import Api.GenericUser;
import Api.User;
import Api.Book;

import Gui.HomePane;
import Gui.Admin.Admin;
import Gui.Admin.EditPanels.AddBook;
import Gui.Admin.ManagementPanels.Books;
import Gui.Admin.ManagementPanels.Genres;
import Gui.Admin.ManagementPanels.Lending;
import Gui.Admin.ManagementPanels.Users;
import Gui.App;
import Gui.UserPane;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Controller {

    private App app;

    Data data;

    public Controller(App app) {
        this.app = app;

        data = Data.load();
    }

    public void saveData() {
        data.save();
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

    // ----------------- Getters -----------------
    public GenericUser getUser(String username, String password) {
        return data.getUser(username, password);
    }

    public Set<Book> getBooks() {
        return data.getBooks();
    }

    public Set<String> getGenres() {
        return data.getGenreToBooks().keySet();
    }

    public HashMap<String, HashSet<Book>> getGenreToBooks() {
        return data.getGenreToBooks();
    }

    // ----------------- Adders -----------------
    public void addBook(Book book, String genre) {
        data.addBook(book, genre);
    }

    public void addUser(User user) {
        data.addUser(user);
    }
}
