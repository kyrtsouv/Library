package Gui.Admin.Dialogs;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;

import Api.Book;
import MVC.Controller;

/*
 * The EditBook class is a dialog that that is used to edit the details of a book.
 */
public class EditBook extends Dialog<Book> {
    private Controller controller;
    private Book book;

    private TextField titleField;
    private TextField authorField;
    private TextField publisherField;
    private TextField isbnField;
    private TextField yearField;
    private ComboBox<String> genreComboBox;
    private TextField copiesField;

    private Label incompleteErrorLabel;
    private Label invalidCopiesLabel;

    public EditBook(Book book, Controller controller) {
        this.controller = controller;
        this.book = book;

        setTitle("Επεξεργασία Βιβλίου");

        ButtonType delButtonType = new ButtonType("Διαγραφή", ButtonData.APPLY);
        ButtonType addButtonType = new ButtonType("Αλλαγή", ButtonData.OK_DONE);

        getDialogPane().getButtonTypes().addAll(delButtonType, addButtonType, ButtonType.CANCEL);
        getDialogPane().setContent(buildForm());

        getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        getDialogPane().lookupButton(addButtonType).addEventFilter(ActionEvent.ACTION, event -> {
            if (titleField.getText().isEmpty() || authorField.getText().isEmpty()
                    || publisherField.getText().isEmpty() || isbnField.getText().isEmpty()
                    || yearField.getText().isEmpty() || genreComboBox.getValue() == null
                    || copiesField.getText().isEmpty()) {
                incompleteErrorLabel.setVisible(true);
                event.consume();
            } else {
                incompleteErrorLabel.setVisible(false);
                int copiesLent = book.getTotalCopies() - book.getAvailableCopies();
                if (Integer.parseInt(copiesField.getText()) < copiesLent) {
                    invalidCopiesLabel.setVisible(true);
                    event.consume();
                } else {
                    invalidCopiesLabel.setVisible(false);
                }
            }

        });

        setResultConverter(buttonType -> {
            if (buttonType == addButtonType) {
                return new Book(titleField.getText(), authorField.getText(), publisherField.getText(),
                        isbnField.getText(),
                        yearField.getText(), genreComboBox.getValue(), Integer.parseInt(copiesField.getText()));
            }
            if (buttonType == delButtonType) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Διαγραφή Βιβλίου");
                alert.setHeaderText("Διαγραφή του βιβλίου " + book.getTitle());
                alert.setContentText("Είσαι σίγουρος;");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    controller.deleteBook(book);
                }

            }
            return null;
        });
    }

    public GridPane buildForm() {

        titleField = new TextField(book.getTitle());
        authorField = new TextField(book.getAuthor());
        publisherField = new TextField(book.getPublisher());
        isbnField = new TextField(book.getIsbn());
        yearField = new TextField(book.getYear());
        yearField.setTextFormatter(getFormatter());
        genreComboBox = new ComboBox<>();
        genreComboBox.getItems().addAll(controller.getGenres());
        genreComboBox.setValue(book.getGenre());
        copiesField = new TextField(String.valueOf(book.getTotalCopies()));
        copiesField.setTextFormatter(getFormatter());

        incompleteErrorLabel = new Label("Συμπληρώστε όλα τα πεδία");
        incompleteErrorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-alignment: center;");
        incompleteErrorLabel.setVisible(false);
        invalidCopiesLabel = new Label("Μη έγκυρος αριθμός αντιτύπων");
        invalidCopiesLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-alignment: center;");
        invalidCopiesLabel.setVisible(false);

        GridPane form = new GridPane();
        form.setStyle("-fx-padding: 10; -fx-hgap: 5; -fx-vgap: 5; -fx-alignment: center;");

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

        form.add(incompleteErrorLabel, 1, 7);
        form.add(invalidCopiesLabel, 1, 8);

        return form;
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
