package Gui.Admin.AddPanels;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import MVC.Controller;
import Api.Book;

public class AddBook extends Pane {
    private Controller controller;

    private VBox panel;
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
        this.panel = new VBox();

        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(5));

        Label dialogTitle = new Label("Τίτλος");
        Font font = Font.font("Arial", FontWeight.BOLD, 20);
        dialogTitle.setFont(font);
        panel.getChildren().add(dialogTitle);

        buildForm();

        panel.getChildren().add(form);

        Button button = new Button("Προσθήκη");
        button.setOnAction(e -> {
            if (titleField.getText().isEmpty() || authorField.getText().isEmpty()
                    || publisherField.getText().isEmpty() || isbnField.getText().isEmpty()
                    || yearField.getText().isEmpty() || genreComboBox.getValue() == null
                    || copiesField.getText().isEmpty()) {
                if (!errorShown) {
                    Label errorLabel = new Label("Συμπληρώστε όλα τα πεδία");
                    errorShown = true;
                    errorLabel.setAlignment(Pos.CENTER);
                    form.add(errorLabel, 0, 8, 2, 1);
                }
            } else {
                controller.addBook(new Book(titleField.getText(), authorField.getText(), publisherField.getText(),
                        isbnField.getText(), yearField.getText(),
                        Integer.parseInt(copiesField.getText())), genreComboBox.getValue());
                controller.goBack();
            }
        });

        panel.getChildren().add(button);
        getChildren().add(panel);

    }

    public void buildForm() {
        form = new GridPane();
        form.setHgap(5);
        form.setVgap(5);
        form.setPadding(new Insets(5));

        Label titleLabel = new Label("Τίτλος");
        form.add(titleLabel, 0, 0);

        Label authorLabel = new Label("Συγγραφέας");
        form.add(authorLabel, 0, 1);

        Label publisherLabel = new Label("Εκδοτικός Οίκος");
        form.add(publisherLabel, 0, 2);

        Label isbnLabel = new Label("ISBN");
        form.add(isbnLabel, 0, 3);

        Label yearLabel = new Label("Έτος Έκδοσης");
        form.add(yearLabel, 0, 4);

        Label genreLabel = new Label("Κατηγορία");
        form.add(genreLabel, 0, 5);

        Label copiesLabel = new Label("Αντίτυπα");
        form.add(copiesLabel, 0, 6);

        titleField = new TextField();
        form.add(titleField, 1, 0);

        authorField = new TextField();
        form.add(authorField, 1, 1);

        publisherField = new TextField();
        form.add(publisherField, 1, 2);

        isbnField = new TextField();
        form.add(isbnField, 1, 3);

        yearField = new TextField();
        yearField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("\\d")) {
                event.consume();
            }
        });
        form.add(yearField, 1, 4);

        genreComboBox = new ComboBox<>();
        genreComboBox.getItems().addAll(controller.getGenres());
        form.add(genreComboBox, 1, 5);

        copiesField = new TextField();
        copiesField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("\\d")) {
                event.consume();
            }
        });
        form.add(copiesField, 1, 6);

    }

}
