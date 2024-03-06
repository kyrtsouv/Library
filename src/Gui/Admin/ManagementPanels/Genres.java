package Gui.Admin.ManagementPanels;

import MVC.Controller;

import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.Set;

public class Genres extends Pane {

    private Controller controller;

    public Genres(Controller controller) {
        this.controller = controller;

        Button button = new Button("Προσθήκη");
        TextField genreField = new TextField();
        genreField.setPromptText("Κατηγορία");
        button.setOnAction(e -> {
            controller.addGenre(genreField.getText());
            genreField.clear();
        });

        HBox addBox = new HBox();
        addBox.getChildren().addAll(genreField, button);
        addBox.setAlignment(Pos.CENTER);
        addBox.setSpacing(5);
        addBox.setPadding(new Insets(5));

        VBox panel = new VBox();
        panel.getChildren().add(addBox);

        Set<String> genres = controller.getGenres();
        for (String genre : genres) {
            Label genreLabel = new Label(genre);
            panel.getChildren().add(genreLabel);
        }

        getChildren().add(panel);

    }
}