package Gui.Admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import MVC.Controller;

public class Admin extends Pane {

    private Controller controller;

    private BorderPane panel;

    private Button booksButton;
    private Button genresButton;
    private Button usersButton;
    private Button lendingButton;

    public Admin(Controller controller) {
        this.controller = controller;
        this.panel = new BorderPane();

        setPadding(new Insets(5));

        HBox titleBox = new HBox();

        Label label = new Label("Διαχείριση Βιβλιοθήκης");
        Font font = Font.font("Arial", FontWeight.BOLD, 20);
        label.setFont(font);

        titleBox.getChildren().add(label);
        titleBox.setAlignment(Pos.CENTER);

        VBox buttonsBox = new VBox();
        buttonsBox.setSpacing(10);
        buttonsBox.setPadding(new Insets(5));

        buttonsBox.setAlignment(Pos.CENTER);

        booksButton = new Button("Διαχείριση Βιβλίων");
        genresButton = new Button("Διαχείριση Κατηγοριών");
        usersButton = new Button("Διαχείριση Χρηστών");
        lendingButton = new Button("Διαχείριση Δανεισμών");

        booksButton.setMinWidth(200);
        genresButton.setMinWidth(200);
        usersButton.setMinWidth(200);
        lendingButton.setMinWidth(200);

        booksButton.setOnAction(e -> {
            controller.showBooksManagerPane();
        });
        genresButton.setOnAction(e -> {
            controller.showGenresManagerPane();
        });
        usersButton.setOnAction(e -> {
            controller.showUsersManagerPane();
        });
        lendingButton.setOnAction(e -> {
            controller.showLendingManagerPane();
        });

        buttonsBox.getChildren().addAll(booksButton, genresButton, usersButton, lendingButton);

        panel.setTop(titleBox);
        panel.setCenter(buttonsBox);

        getChildren().add(panel);

    }

}
