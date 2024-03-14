package Gui.User;

import MVC.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Api.User;
import Gui.Common.Buildable;

/*
 * The UDashboard class is a pane that displays the dashboard of the user.
 * It contains buttons that allow the user to move to the panes for searching books and viewing lendings.
 */
public class UDashboard extends Buildable {

    private Controller controller;
    private User user;

    public UDashboard(Controller controller, User user) {
        this.controller = controller;
        this.user = user;
        build();
    }

    @Override
    public void build() {
        getChildren().clear();

        Label welcomeLabel = new Label("Καλωσήρθες " + user.getUsername());
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox welcomeBox = new HBox(welcomeLabel);
        welcomeBox.setStyle("-fx-padding: 5; -fx-alignment: center;");

        Button searchButton = new Button("Αναζήτηση Βιβλίου");
        Button lendingsButton = new Button("Προβολή Δανεισμών");

        searchButton.setMinWidth(200);
        lendingsButton.setMinWidth(200);

        searchButton.setOnAction(e -> {
            controller.showSearchPane(user);
        });

        lendingsButton.setOnAction(e -> {
            controller.showLendingsPane(user);
        });

        VBox buttonsBox = new VBox(searchButton, lendingsButton);
        buttonsBox.setStyle("-fx-spacing: 10; -fx-padding: 5; -fx-alignment: center;");

        BorderPane root = new BorderPane();
        root.setTop(welcomeBox);
        root.setCenter(buttonsBox);

        getChildren().add(root);
    }

}
