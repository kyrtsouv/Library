package Gui.Common;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import Api.Book;

public class BookPane extends GridPane {

    public BookPane(Book book) {
        setStyle("-fx-padding: 5; -fx-hgap: 5; -fx-vgap: 5;");

        add(new Label("Τίτλος: "), 0, 0);
        add(new Label(book.getTitle()), 1, 0);

        add(new Label("Συγγραφέας: "), 0, 1);
        add(new Label(book.getAuthor()), 1, 1);

        add(new Label("ISBN: "), 0, 2);
        add(new Label(book.getIsbn()), 1, 2);

        add(new Label("Βαθμολογία: "), 0, 3);
        add(new Label(String.valueOf(book.getRating())), 1, 3);

        add(new Label("Αξιολογήσεις: "), 0, 4);
        add(new Label(String.valueOf(book.getReviews().size())), 1, 4);
    }

    public BookPane addBorder() {
        setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        return this;
    }

}
