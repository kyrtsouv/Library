package Gui.Admin.Dialogs;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;

import Api.User;
import MVC.Controller;

public class EditUser extends Dialog<User> {

    private User user;

    private TextField nameField;
    private TextField surnameField;
    private TextField emailField;
    private TextField adtField;
    private TextField usernameField;
    private TextField passwordField;

    private Label errorLabel;

    public EditUser(Controller controller, User user) {

        setTitle("Επεξεργασία Χρήστη");

        ButtonType delButtonType = new ButtonType("Διαγραφή", ButtonData.APPLY);
        ButtonType editButtonType = new ButtonType("Αλλαγή", ButtonData.OK_DONE);

        getDialogPane().getButtonTypes().addAll(delButtonType, editButtonType, ButtonType.CANCEL);
        getDialogPane().setContent(buildForm());

        getDialogPane().lookupButton(delButtonType)
                .setStyle("-fx-background-color: #ff0000; -fx-font-weight: bold; -fx-text-fill: white;");
        getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        getDialogPane().lookupButton(editButtonType).addEventFilter(ActionEvent.ACTION, event -> {
            if (nameField.getText().isEmpty() || surnameField.getText().isEmpty() || emailField.getText().isEmpty()
                    || adtField.getText().isEmpty() || usernameField.getText().isEmpty()
                    || passwordField.getText().isEmpty()) {
                errorLabel.setVisible(true);
                getDialogPane().getScene().getWindow().sizeToScene();
                event.consume();
            }
        });

        setResultConverter(buttonType -> {
            if (buttonType == editButtonType) {
                return new User(nameField.getText(), surnameField.getText(), emailField.getText(),
                        adtField.getText(), usernameField.getText(), passwordField.getText());

            }
            if (buttonType == delButtonType) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Διαγραφή Χρήστη");
                alert.setHeaderText("Διαγραφή του χρήστη " + user.getUsername());
                alert.setContentText("Είσαι σίγουρος;");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    controller.deleteUser(user);
                }
            }
            return null;

        });
    }

    public GridPane buildForm() {

        nameField = new TextField(user.getName());
        surnameField = new TextField(user.getSurname());
        emailField = new TextField(user.getEmail());
        adtField = new TextField(user.getADT());
        usernameField = new TextField(user.getUsername());
        passwordField = new TextField(user.getPassword());

        errorLabel = new Label("Συμπληρώστε όλα τα πεδία");
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-alignment: center;");
        errorLabel.setVisible(false);

        GridPane form = new GridPane();
        form.setStyle("-fx-padding: 10px; -fx-hgap: 10px; -fx-vgap: 10px;");

        form.add(new Label("Όνομα: "), 0, 0);
        form.add(new Label("Επώνυμο: "), 0, 1);
        form.add(new Label("Email: "), 0, 2);
        form.add(new Label("ΑΔΤ: "), 0, 3);
        form.add(new Label("Όνομα Χρήστη: "), 0, 4);
        form.add(new Label("Κωδικός: "), 0, 5);

        form.add(nameField, 1, 0);
        form.add(surnameField, 1, 1);
        form.add(emailField, 1, 2);
        form.add(adtField, 1, 3);
        form.add(usernameField, 1, 4);
        form.add(passwordField, 1, 5);

        form.add(errorLabel, 0, 6, 2, 1);

        return form;
    }
}
