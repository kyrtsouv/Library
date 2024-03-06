package Gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BorderWidths;

import java.util.HashMap;

import Api.GenericUser;
import Api.User;
import MVC.Controller;

public class HomePane extends Pane {

    private Controller controller;

    private BorderPane panel;

    public HomePane(Controller controller) {
        this.controller = controller;
        this.panel = new BorderPane();

        BorderPane login = new BorderPane();

        HBox top = new HBox();
        HBox forms = new HBox();
        HBox bot = new HBox();

        Line line = new Line();
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        line.startXProperty().bind(top.widthProperty().divide(2));
        line.startYProperty().bind(top.heightProperty());
        line.endXProperty().bind(bot.widthProperty().divide(2));
        line.endYProperty().bind(bot.layoutYProperty().subtract(10));
        Form signUpForm = buildSignUpForm();
        Form signInForm = buildSignInForm();
        forms.getChildren().addAll(signUpForm, line, signInForm);
        forms.setAlignment(Pos.CENTER);
        forms.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));

        login.setTop(top);
        login.setCenter(forms);
        login.setBottom(bot);

        TilePane books = new TilePane();

        books.setHgap(10);
        books.setVgap(10);
        books.setPadding(new Insets(10));

        int i = 0;
        while (i < 5 && controller.getBooks().iterator().hasNext()) {
            books.getChildren().add(new BookPane(controller.getBooks().iterator().next()));
            i++;
        }

        panel.setTop(login);
        panel.setCenter(books);

        getChildren().add(panel);

    }

    private Form buildSignUpForm() {
        Form signUpForm;
        Button signUpButton = new Button("Εγγραφή");
        signUpForm = new Form("Εγγραφή", signUpButton);

        signUpButton.setOnAction(event -> {
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
                controller.addUser(user);

                System.out.println("Επιτυχής εγγραφή");
            } catch (Exception e) {
                signUpForm.printError(e.getMessage());
            }

        });
        signUpForm.addField("Όνομα Χρήστη", new TextField());
        signUpForm.addField("Κωδικός", new PasswordField());
        signUpForm.addField("Όνομα", new TextField());
        signUpForm.addField("Επώνυμο", new TextField());
        signUpForm.addField("ΑΔΤ", new TextField());
        signUpForm.addField("Email", new TextField());
        return signUpForm;
    }

    private Form buildSignInForm() {
        Button signInButton = new Button("Σύνδεση");
        Form signInForm = new Form("Σύνδεση", signInButton);
        signInButton.setOnAction(event -> {
            HashMap<String, String> signInData = signInForm.getData();

            try {
                GenericUser user = controller.getUser(signInData.get("Όνομα Χρήστη"), signInData.get("Κωδικός"));
                if (user instanceof User) {
                    controller.showUserPanel((User) user);
                } else {
                    controller.showAdminPanel();
                }
            } catch (Exception e) {
                signInForm.printError(e.getMessage());
            }
        });
        signInForm.addField("Όνομα Χρήστη", new TextField());
        signInForm.addField("Κωδικός", new PasswordField());

        return signInForm;
    }

}
