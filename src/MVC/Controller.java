package MVC;

import java.util.Set;

import Api.Book;
import Api.Data;

import Gui.App;
import Gui.HomePane;
import Gui.Form;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class Controller {

    private Form signUpForm;
    private Form signInForm;
    private HomePane homePane;

    public Controller() {
        Data data = Data.load();

        App app = new App();
        app.setController(this);
        new App().run();
    }

    public HomePane getHomePane() {
        buildHomePane();
        return new HomePane(this, signUpForm, signInForm);
    }

    public void buildHomePane() {
        signUpForm = new Form("Εγγραφή", new Button("Εγγραφή"));
        signUpForm.addField("Username", new TextField());
        signUpForm.addField("Κωδικός", new PasswordField());
        signUpForm.addField("Όνομα", new TextField());
        signUpForm.addField("Επώνυμο", new TextField());
        signUpForm.addField("ΑΔΤ", new TextField());
        signUpForm.addField("Email", new TextField());
        // signUpForm.addEventHandler(() -> {
        // HashMap<String, String> data = signUpForm.getData();
        // data.forEach((label, value) -> System.out.println(label + ": " + value));
        // });

        signInForm = new Form("Σύνδεση", new Button("Σύνδεση"));
        signInForm.addField("Username", new TextField());
        signInForm.addField("Κωδικός", new PasswordField());
        // signInForm.addEventHandler(() -> {
        // HashMap<String, String> data = signInForm.getData();
        // data.forEach((label, value) -> System.out.println(label + ": " + value));
        // });
    }

    // Must return 5 books with top rating
    public Set<Book> getBestBooks() {
        return null;
    }
}
