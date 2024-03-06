package Gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import Api.Book;

public class BookPane extends Pane {

    private GridPane panel;

    private Book book;

    public BookPane(Book book) {
        this.book = book;
        this.panel = new GridPane();

        panel.setPadding(new Insets(5));
        panel.setHgap(5);
        panel.setVgap(5);

        Label titleLabel = new Label(book.getTitle());
        panel.add(titleLabel, 0, 0);
        Label authorLabel = new Label(book.getAuthor());
        panel.add(authorLabel, 0, 1);
        Label isbnLabel = new Label(book.getIsbn());
        panel.add(isbnLabel, 0, 2);
        Label ratingLabel = new Label(String.valueOf(book.getRating()));
        panel.add(ratingLabel, 0, 3);
        Label reviewsLabel = new Label(String.valueOf(book.getReviews().size()));
        panel.add(reviewsLabel, 0, 4);

        getChildren().add(panel);

    }

}
