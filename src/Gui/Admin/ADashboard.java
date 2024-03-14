package Gui.Admin;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import Gui.Common.Buildable;
import MVC.Controller;

/*
 * The ADashboard class is a pane that displays the dashboard of the admin.
 * It contains buttons that allow the admin to move to the different management panes.
 */
public class ADashboard extends Buildable {

    private Controller controller;

    public ADashboard(Controller controller) {
        this.controller = controller;
        build();
    }

    @Override
    public void build() {
        getChildren().clear();

        Label label = new Label("Διαχείριση Βιβλιοθήκης");
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; ");

        Button booksButton = new Button("Διαχείριση Βιβλίων");
        Button genresButton = new Button("Διαχείριση Κατηγοριών");
        Button usersButton = new Button("Διαχείριση Χρηστών");
        Button lendingButton = new Button("Διαχείριση Δανεισμών");

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

        VBox buttonsBox = new VBox();
        buttonsBox.setStyle("-fx-spacing: 10; -fx-padding: 5; -fx-alignment: center;");
        buttonsBox.getChildren().addAll(booksButton, genresButton, usersButton, lendingButton);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-padding: 5;");
        root.setTop(label);
        root.setCenter(buttonsBox);

        getChildren().add(root);
    }

}
