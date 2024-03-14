package Gui.User;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import Gui.Common.BookPane;
import Gui.Common.Buildable;

import java.util.HashSet;

import Api.Book;
import Api.User;
import MVC.Controller;

/*
 * The SearchPane class is a pane that allows the user to search for books, view their details and lend them.
 * It contains a side bar with search fields and a results pane that displays the results of the search.
 */

public class SearchPane extends Buildable {

    private Controller controller;
    private User user;

    private BorderPane root;

    public SearchPane(Controller controller, User user) {
        this.controller = controller;
        this.user = user;

        build();
    }

    @Override
    public void build() {
        getChildren().clear();
        root = new BorderPane();

        ResultsPane resultsPane = new ResultsPane();
        SideBar sideBar = new SideBar(resultsPane);

        root.setLeft(sideBar);
        root.setCenter(resultsPane);

        getChildren().add(root);
    }

    private class SideBar extends GridPane {
        public SideBar(ResultsPane resultsPane) {
            setStyle("-fx-padding: 5; -fx-hgap: 5; -fx-vgap: 5;");

            Label titleLabel = new Label("Τίτλος: ");
            Label authorLabel = new Label("Συγγραφέας: ");
            Label yearLabel = new Label("Έτος: ");

            TextField titleField = new TextField();
            TextField authorField = new TextField();
            TextField yearField = new TextField();

            Button searchButton = new Button("Αναζήτηση");
            searchButton.setOnAction(e -> {
                resultsPane.build(
                        controller.searchBooks(titleField.getText(), authorField.getText(), yearField.getText()));
            });

            add(titleLabel, 0, 0);
            add(titleField, 1, 0);

            add(authorLabel, 0, 1);
            add(authorField, 1, 1);

            add(yearLabel, 0, 2);
            add(yearField, 1, 2);

            add(searchButton, 0, 3);
        }
    }

    private class ResultsPane extends VBox {

        public ResultsPane() {
            build(controller.getBooks());
        }

        public void build(HashSet<Book> results) {
            getChildren().clear();
            setStyle("-fx-padding: 5; -fx-spacing: 5;");

            for (Book book : results) {
                Button infoButton = new Button("Πληροφορίες");
                infoButton.setOnAction(e -> {
                    controller.showBookInfoPane(user, book);
                });
                Button lendButton = new Button("Δανεισμός");
                boolean hasEnoughCopies = book.getAvailableCopies() > 0;
                boolean hasLessThanTwoLendings = user.getActiveLendings().size() < 2;
                boolean isNotAlreadyLent = !user.getActiveLendings().contains(book);
                if (!(hasEnoughCopies && hasLessThanTwoLendings && isNotAlreadyLent)) {
                    lendButton.setDisable(true);
                }
                lendButton.setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Δανεισμός Βιβλίου");
                    alert.setHeaderText("Δανεισμός του βιβλίου " + book.getTitle());
                    alert.setContentText("Είσαι σίγουρος;");
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        controller.lendBook(book, user);
                        build(results);
                    }
                });
                BorderPane outerBookPane = new BorderPane();
                outerBookPane.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 5;");

                outerBookPane.setTop(infoButton);
                outerBookPane.setCenter(new BookPane(book));
                outerBookPane.setBottom(lendButton);

                getChildren().add(outerBookPane);
            }
        }

    }
}
