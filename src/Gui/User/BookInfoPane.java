package Gui.User;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import Api.Book;
import Gui.Common.Buildable;

public class BookInfoPane extends Buildable {
    private Book book;

    public BookInfoPane(Book book) {
        this.book = book;
        build();
    }

    @Override
    public void build() {
        getChildren().clear();

        Label label = new Label("Πληροφορίες Βιβλίου");
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; ");

        GridPane infoPane = new GridPane();
        infoPane.setStyle("-fx-padding: 5; -fx-hgap: 5; -fx-vgap: 5;");
        infoPane.add(new Label("Τίτλος: "), 0, 0);
        infoPane.add(new Label(book.getTitle()), 1, 0);

        infoPane.add(new Label("Συγγραφέας: "), 0, 1);
        infoPane.add(new Label(book.getAuthor()), 1, 1);

        infoPane.add(new Label("Εκδοτικός Οίκος: "), 0, 3);
        infoPane.add(new Label(book.getPublisher()), 1, 3);

        infoPane.add(new Label("ISBN: "), 0, 2);
        infoPane.add(new Label(book.getIsbn()), 1, 2);

        infoPane.add(new Label("Έτος Έκδοσης: "), 0, 4);
        infoPane.add(new Label(book.getYear()), 1, 4);

        infoPane.add(new Label("Κατηγορία: "), 0, 5);
        infoPane.add(new Label(book.getGenre()), 1, 5);

        infoPane.add(new Label("Διαθέσιμα Αντίτυπα: "), 0, 6);
        infoPane.add(new Label(String.valueOf(book.getAvailableCopies())), 1, 6);

        VBox reviewsPane = new VBox();
        reviewsPane.setStyle("-fx-padding: 5;");
        reviewsPane.getChildren().add(new Label(book.getReviews().size() + " Αξιολογήσεις"));
        for (String review : book.getReviews()) {
            reviewsPane.getChildren().add(new Label(review));
        }

        BorderPane bookPane = new BorderPane();
        bookPane.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 5;");
        bookPane.setTop(infoPane);
        bookPane.setBottom(reviewsPane);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-padding: 5;");
        root.setTop(label);
        root.setCenter(bookPane);

        getChildren().add(root);
    }

}
