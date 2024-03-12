package Gui.Admin.Dialogs;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;

import Api.Book;
import MVC.Controller;

public class AddBook extends Dialog<Book> {
    private Controller controller;

    private VBox root;

    private GridPane form;

    private TextField titleField;
    private TextField authorField;
    private TextField publisherField;
    private TextField isbnField;
    private TextField yearField;
    private ComboBox<String> genreComboBox;
    private TextField copiesField;

    private boolean errorShown = false;

    public AddBook(Controller controller) {
        this.controller = controller;

        setTitle("Προσθήκη Βιβλίου");

        ButtonType addButtonType = new ButtonType("Προσθήκη", ButtonData.OK_DONE);

        getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        getDialogPane().setContent(buildForm());

        getDialogPane().lookupButton(addButtonType).addEventFilter(ActionEvent.ACTION, event -> {
            if (titleField.getText().isEmpty() || authorField.getText().isEmpty()
                    || publisherField.getText().isEmpty() || isbnField.getText().isEmpty()
                    || yearField.getText().isEmpty() || genreComboBox.getValue() == null
                    || copiesField.getText().isEmpty()) {
                if (!errorShown) {
                    Label errorLabel = new Label("Συμπληρώστε όλα τα πεδία");
                    errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-alignment: center;");
                    errorShown = true;
                    form.add(errorLabel, 0, 8, 2, 1);
                    getDialogPane().getScene().getWindow().sizeToScene();
                }
                event.consume();
            }
        });

        setResultConverter(buttonType -> {
            if (buttonType.getButtonData() == ButtonData.OK_DONE) {
                return new Book(titleField.getText(), authorField.getText(), publisherField.getText(),
                        isbnField.getText(), yearField.getText(), genreComboBox.getValue(),
                        Integer.parseInt(copiesField.getText()));
            }
            return null;
        });
    }

    private VBox buildForm() {
        titleField = new TextField();
        authorField = new TextField();
        publisherField = new TextField();
        isbnField = new TextField();
        yearField = new TextField();
        yearField.setTextFormatter(getFormatter());
        genreComboBox = new ComboBox<>();
        genreComboBox.getItems().addAll(controller.getGenres());
        copiesField = new TextField();
        copiesField.setTextFormatter(getFormatter());

        form = new GridPane();
        form.setStyle("-fx-hgap: 5; -fx-vgap: 5; -fx-padding: 5;");

        form.add(new Label("Τίτλος"), 0, 0);
        form.add(new Label("Συγγραφέας"), 0, 1);
        form.add(new Label("Εκδοτικός Οίκος"), 0, 2);
        form.add(new Label("ISBN"), 0, 3);
        form.add(new Label("Έτος Έκδοσης"), 0, 4);
        form.add(new Label("Κατηγορία"), 0, 5);
        form.add(new Label("Αντίτυπα"), 0, 6);

        form.add(titleField, 1, 0);
        form.add(authorField, 1, 1);
        form.add(publisherField, 1, 2);
        form.add(isbnField, 1, 3);
        form.add(yearField, 1, 4);
        form.add(genreComboBox, 1, 5);
        form.add(copiesField, 1, 6);

        root = new VBox();
        root.setStyle("-fx-padding: 5; -fx-alignment: center;");
        root.getChildren().add(form);

        return root;
    }

    public TextFormatter<String> getFormatter() {
        return new TextFormatter<>(change -> {
            String text = change.getText();
            if (text.matches("\\d*")) {
                return change;
            }
            return null;
        });
    }

}
