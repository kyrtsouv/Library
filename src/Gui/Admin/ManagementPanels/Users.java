package Gui.Admin.ManagementPanels;

import java.util.Optional;

import Api.User;
import Gui.Admin.Dialogs.EditUser;
import Gui.Common.Buildable;
import MVC.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Users extends Buildable {

    private Controller controller;

    public Users(Controller controller) {
        this.controller = controller;
        build();
    }

    @Override
    public void build() {
        getChildren().clear();

        VBox root = new VBox();
        root.setStyle("-fx-padding: 5px; -fx-spacing: 5px; -fx-alignment: center;");

        for (User user : controller.getUsers()) {
            Button editUserButton = new Button();
            editUserButton.setGraphic(new UserPane(user));
            editUserButton.setOnAction(e -> {
                Optional<User> result = new EditUser(controller, user).showAndWait();
                if (result.isPresent()) {
                    User newUser = result.get();
                    controller.updateUser(user, newUser);
                }
                build();
            });

            root.getChildren().add(editUserButton);
        }

        getChildren().add(root);
    }

    private class UserPane extends GridPane {
        public UserPane(User user) {

            setStyle(
                    "-fx-padding: 5px; -fx-hgap: 5px; -fx-vgap: 5px;");

            add(new Label("Όνομα: "), 0, 0);
            add(new Label(user.getName()), 1, 0);

            add(new Label("Επώνυμο: "), 0, 1);
            add(new Label(user.getSurname()), 1, 1);

            add(new Label("Ηλεκτρονική Διεύθυνση: "), 0, 2);
            add(new Label(user.getEmail()), 1, 2);

            add(new Label("ΑΔΤ: "), 0, 3);
            add(new Label(user.getADT()), 1, 3);

            add(new Label("Όνομα Χρήστη: "), 0, 4);
            add(new Label(user.getUsername()), 1, 4);

            add(new Label("Κωδικός: "), 0, 5);
            add(new Label(user.getPassword()), 1, 5);
        }
    }

}
