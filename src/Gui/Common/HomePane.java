package Gui.Common;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.util.Iterator;

import Api.Book;
import Api.GenericUser;
import Api.User;
import MVC.Controller;

public class HomePane extends Buildable {

    private Controller controller;

    public HomePane(Controller controller) {
        this.controller = controller;
        build();
    }

    @Override
    public void build() {
        getChildren().clear();
        BorderPane root = new BorderPane();

        BorderPane login = new BorderPane();

        HBox top = new HBox();
        HBox forms = new HBox();
        HBox bot = new HBox();

        Line line = new Line();
        line.setStyle("-fx-stroke: black; -fx-stroke-width: 2; ");
        line.startXProperty().bind(top.widthProperty().divide(2));
        line.startYProperty().bind(top.heightProperty());
        line.endXProperty().bind(bot.widthProperty().divide(2));
        line.endYProperty().bind(bot.layoutYProperty().subtract(10));

        forms.getChildren().addAll(new SignUpForm(), line, new SignInForm());
        forms.setStyle(
                "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-color: white;");

        login.setTop(top);
        login.setCenter(forms);
        login.setBottom(bot);

        FlowPane books = new FlowPane();
        books.setStyle("-fx-hgap: 10; -fx-vgap: 10; -fx-padding: 5;");

        int i = 0;
        Iterator<Book> bookIterator = controller.getBooks().iterator();
        while (i < 5 && bookIterator.hasNext()) {
            books.getChildren().add(new BookPane(bookIterator.next()).addBorder());
            i++;
        }

        root.setTop(login);
        root.setCenter(books);

        getChildren().add(root);
    }

    private class SignUpForm extends GridPane {

        public SignUpForm() {
            setStyle("-fx-padding: 10; -fx-hgap: 10; -fx-vgap: 10;");

            Label titleLabel = new Label("Εγγραφή");
            titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            HBox titleBox = new HBox(titleLabel);
            titleBox.setStyle("-fx-alignment: center;");

            TextField usernameField = new TextField();
            PasswordField passwordField = new PasswordField();
            TextField nameField = new TextField();
            TextField surnameField = new TextField();
            TextField adtField = new TextField();
            TextField emailField = new TextField();
            Button signUpButton = new Button("Εγγραφή");
            HBox errorBox = new HBox();
            errorBox.setStyle("-fx-alignment: center;");

            signUpButton.setOnAction(event -> {
                try {
                    User user = new User(nameField.getText(), surnameField.getText(), adtField.getText(),
                            emailField.getText(),
                            usernameField.getText(), passwordField.getText());
                    controller.addUser(user);
                    controller.showUserPanel(user);
                } catch (Exception e) {
                    Label errorLabel = new Label(e.getMessage());
                    errorLabel.setStyle(
                            "-fx-text-fill: red; -fx-font-weight: bold; -fx-wrap-text: true;");
                    errorBox.getChildren().clear();
                    errorBox.getChildren().add(errorLabel);
                }
            });
            HBox buttonBox = new HBox(signUpButton);
            buttonBox.setStyle("-fx-alignment: center;");

            add(titleBox, 0, 0, 2, 1);

            add(new Label("Όνομα Χρήστη"), 0, 1);
            add(new Label("Κωδικός"), 0, 2);
            add(new Label("Όνομα"), 0, 3);
            add(new Label("Επώνυμο"), 0, 4);
            add(new Label("ΑΔΤ"), 0, 5);
            add(new Label("Email"), 0, 6);

            add(usernameField, 1, 1);
            add(passwordField, 1, 2);
            add(nameField, 1, 3);
            add(surnameField, 1, 4);
            add(adtField, 1, 5);
            add(emailField, 1, 6);

            add(buttonBox, 0, 7, 2, 1);

            add(errorBox, 0, 8, 2, 1);
        }
    }

    private class SignInForm extends GridPane {

        public SignInForm() {
            setStyle("-fx-padding: 10; -fx-hgap: 10; -fx-vgap: 10;");

            Label titleLabel = new Label("Σύνδεση");
            titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            HBox titleBox = new HBox(titleLabel);
            titleBox.setStyle("-fx-alignment: center;");

            TextField usernameField = new TextField();
            PasswordField passwordField = new PasswordField();
            Button signIButton = new Button("Σύνδεση");
            HBox errorBox = new HBox();
            errorBox.setStyle("-fx-alignment: center;");

            signIButton.setOnAction(event -> {
                try {
                    GenericUser user = controller.getUser(usernameField.getText(), passwordField.getText());
                    if (user instanceof User) {
                        controller.showUserPanel((User) user);
                    } else {
                        controller.showAdminPanel();
                    }
                } catch (Exception e) {
                    Label errorLabel = new Label(e.getMessage());
                    errorLabel.setStyle(
                            "-fx-text-fill: red; -fx-font-weight: bold; -fx-wrap-text: true;");
                    errorBox.getChildren().clear();
                    errorBox.getChildren().add(errorLabel);
                }
            });
            HBox buttonBox = new HBox(signIButton);
            buttonBox.setStyle("-fx-alignment: center;");

            add(titleBox, 0, 0, 2, 1);

            add(new Label("Όνομα Χρήστη"), 0, 1);
            add(new Label("Κωδικός"), 0, 2);

            add(usernameField, 1, 1);
            add(passwordField, 1, 2);

            add(buttonBox, 0, 3, 2, 1);

            add(errorBox, 0, 4, 2, 1);
        }
    }

}
