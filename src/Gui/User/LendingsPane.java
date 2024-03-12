package Gui.User;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import Api.Book;
import Api.Review;
import Api.User;
import Gui.Common.BookPane;
import Gui.Common.Buildable;
import MVC.Controller;

public class LendingsPane extends Buildable {
    private Controller controller;
    private User user;

    public LendingsPane(Controller controller, User user) {
        this.controller = controller;
        this.user = user;

        build();
    }

    @Override
    public void build() {
        getChildren().clear();

        VBox root = new VBox();
        root.setStyle("-fx-padding: 10; -fx-spacing: 10;");

        for (Book book : user.getActiveLendings()) {

            Button addReviewButton = new Button("Προσθήκη Κριτικής");
            if (user.hasReviewd(book)) {
                addReviewButton.setDisable(true);
            }
            addReviewButton.setOnAction(e -> {
                new ReviewDialog(book).showAndWait().ifPresent(review -> {
                    controller.addReview(book, user, review);
                });
                build();
            });

            VBox reviewBox = new VBox();
            reviewBox.setStyle("-fx-padding: 5; -fx-border-color: black; -fx-border-width: 1px;");
            reviewBox.getChildren().add(new BookPane(book));
            reviewBox.getChildren().add(addReviewButton);

            root.getChildren().add(reviewBox);
        }

        getChildren().add(root);
    }

    private class ReviewDialog extends Dialog<Review> {

        public ReviewDialog(Book book) {
            setTitle("Προσθήκη Κριτικής");

            ButtonType reviewButtonType = new ButtonType("Προσθήκη αξιολόγησης", ButtonData.OK_DONE);

            TextField reviewField = new TextField();
            Spinner<Integer> ratingSpinner = new Spinner<>(1, 5, 1);

            VBox reviewPane = new VBox();
            reviewPane.getChildren().addAll(new Text("Κριτική: "), reviewField, new Text("Βαθμολογία: "),
                    ratingSpinner);

            getDialogPane().getButtonTypes().addAll(reviewButtonType, ButtonType.CANCEL);
            getDialogPane().setContent(reviewPane);

            setResultConverter(buttonType -> {
                if (buttonType.getButtonData() == ButtonData.OK_DONE) {
                    return new Review(reviewField.getText(), ratingSpinner.getValue());
                }
                return null;
            });

        }

    }

}
