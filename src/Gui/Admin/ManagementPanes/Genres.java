package Gui.Admin.ManagementPanes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

import Gui.Common.Buildable;
import MVC.Controller;

/*
 * The Genres class is a pane that displays all the genres in the library and
 * allows the admin to add new genres, edit or delete existing ones.
 */
public class Genres extends Buildable {

    private Controller controller;

    public Genres(Controller controller) {
        this.controller = controller;

        build();
    }

    @Override
    public void build() {
        getChildren().clear();

        TextField addGenreField = new TextField();
        addGenreField.setPromptText("Κατηγορία");
        Button button = new Button("Προσθήκη");
        button.setOnAction(e -> {
            controller.addGenre(addGenreField.getText());
            build();
        });

        HBox addBox = new HBox();
        addBox.getChildren().addAll(addGenreField, button);
        addBox.setStyle("-fx-padding: 5; -fx-spacing: 5; -fx-alignment: center;");

        VBox root = new VBox();
        root.setStyle("-fx-padding: 5; -fx-spacing: 5;");
        root.getChildren().add(addBox);
        root.getChildren().add(buildList());

        getChildren().add(root);
    }

    private VBox buildList() {
        VBox list = new VBox();
        list.setStyle("-fx-spacing: 5;");
        for (String genre : controller.getGenres()) {
            TextField genreField = new TextField(genre);
            genreField.focusedProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal) {
                    if (genreField.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Διαγραφή Κατηγορίας");
                        alert.setHeaderText("Διαγραφή Κατηγορίας: " + genre);
                        alert.setContentText("Είσαι σίγουρος;");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            controller.updateGenre(genre, "");
                        } else {
                            genreField.setText(genre);
                        }
                    } else {
                        controller.updateGenre(genre, genreField.getText());
                    }
                    build();
                }
            });
            genreField.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    this.requestFocus();
                }
            });
            list.getChildren().add(genreField);
        }

        return list;
    }

}