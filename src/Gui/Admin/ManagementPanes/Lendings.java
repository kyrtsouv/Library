package Gui.Admin.ManagementPanes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import Api.Lending;
import Gui.Common.Buildable;
import MVC.Controller;

/*
 * The Lendings class is a pane that displays all the lendings in the library and
 * allows the admin to report the return of a book.
 */
public class Lendings extends Buildable {

    private Controller controller;

    public Lendings(Controller controller) {
        this.controller = controller;
        build();
    }

    @Override
    public void build() {
        getChildren().clear();

        GridPane root = new GridPane();
        root.setStyle("-fx-padding: 10; -fx-hgap: 10; -fx-vgap: 10;");

        int lendingsCount = 0;
        for (Lending lending : controller.getLendings()) {
            Button returnButton = new Button("Επιστροφή");
            returnButton.setOnAction(e -> {
                controller.deleteLending(lending);
                build();
            });
            root.add(new LendingPane(lending), 0, lendingsCount);
            root.add(returnButton, 1, lendingsCount++);
        }

        getChildren().add(root);
    }

    private class LendingPane extends GridPane {
        public LendingPane(Lending lending) {
            setStyle("-fx-padding: 5; -fx-border-color: black; -fx-border-width: 1px;");

            add(new Label("Χρήστης: "), 0, 0);
            add(new Label(lending.getUser().getUsername()), 1, 0);

            add(new Label("Βιβλίο: "), 0, 1);
            add(new Label(lending.getBook().getTitle()), 1, 1);

            add(new Label("Ημερομηνία Έναρξης: "), 0, 2);
            add(new Label(lending.getStartDate().toString()), 1, 2);

            add(new Label("Ημερομηνία Λήξης: "), 0, 3);
            add(new Label(lending.getEndDate().toString()), 1, 3);
        }
    }
}
