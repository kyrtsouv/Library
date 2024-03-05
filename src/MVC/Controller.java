package MVC;

import Api.Book;
import Api.Data;
import Api.GenericUser;
import Api.User;

import Gui.HomePane;
import Gui.Form;
import Gui.BookPane;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

import java.util.HashMap;
import java.util.HashSet;

public class Controller {

    private Form signUpForm;
    private Form signInForm;

    private HomePane homePane;

    Data data;

    public Controller() {
        data = Data.load();

        signUpForm = new Form("Εγγραφή", new Button("Εγγραφή"));
        signUpForm.addField("Όνομα Χρήστη", new TextField());
        signUpForm.addField("Κωδικός", new PasswordField());
        signUpForm.addField("Όνομα", new TextField());
        signUpForm.addField("Επώνυμο", new TextField());
        signUpForm.addField("ΑΔΤ", new TextField());
        signUpForm.addField("Email", new TextField());
        signUpForm.addEventHandler(() -> {
            HashMap<String, String> signUpData = signUpForm.getData();
            try {
                String name = signUpData.get("Όνομα");
                String surname = signUpData.get("Επώνυμο");
                String adt = signUpData.get("ΑΔΤ");
                String email = signUpData.get("Email");
                String username = signUpData.get("Όνομα Χρήστη");
                String password = signUpData.get("Κωδικός");
                if (name.isEmpty() || surname.isEmpty() || adt.isEmpty() || email.isEmpty() || username.isEmpty()
                        || password.isEmpty()) {
                    throw new IllegalArgumentException("Όλα τα πεδία είναι υποχρεωτικά");
                }
                User user = new User(name, surname, adt, email, username, password);
                data.addUser(user);

                System.out.println("Επιτυχής εγγραφή");
            } catch (Exception e) {
                signUpForm.printError(e.getMessage());
            }

        });

        signInForm = new Form("Σύνδεση", new Button("Σύνδεση"));
        signInForm.addField("Όνομα Χρήστη", new TextField());
        signInForm.addField("Κωδικός", new PasswordField());
        signInForm.addEventHandler(() -> {
            HashMap<String, String> signInData = signInForm.getData();

            try {
                GenericUser user = data.getUser(signInData.get("Όνομα Χρήστη"), signInData.get("Κωδικός"));
                System.out.println("Επιτυχής σύνδεση");
            } catch (Exception e) {
                signInForm.printError(e.getMessage());
            }
        });

        ScrollPane scrollPane = new ScrollPane();

        TilePane books = new TilePane();
        books.setHgap(10);
        books.setVgap(10);
        books.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        int i = 0;
        while (i < 5 && data.getBooks().iterator().hasNext()) {
            books.getChildren().add(new BookPane(data.getBooks().iterator().next()));
            i++;
        }

        scrollPane.setContent(books);
        scrollPane.setFitToWidth(true);

        homePane = new HomePane(this, signUpForm, signInForm, scrollPane);
    }

    public HomePane getHomePane() {
        return homePane;
    }

    // Must return 5 books with top rating
    public HashSet<Book> getBestBooks() {
        return new HashSet<>();
    }

    public void saveData() {
        data.save();
    }
}
