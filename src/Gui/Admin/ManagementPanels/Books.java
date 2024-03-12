package Gui.Admin.ManagementPanels;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Optional;

import Api.Book;
import Api.OrderedBookSet;
import Gui.Admin.Dialogs.AddBook;
import Gui.Admin.Dialogs.EditBook;
import Gui.Common.BookPane;
import Gui.Common.Buildable;
import MVC.Controller;

public class Books extends Buildable {

    private Controller controller;

    public Books(Controller controller) {
        this.controller = controller;
        build();
    }

    @Override
    public void build() {
        getChildren().clear();

        Button addButton = new Button("Προσθήκη Βιβλίου");
        addButton.setStyle("-fx-alignment: center;");
        addButton.setOnAction(e -> {
            Optional<Book> result = new AddBook(controller).showAndWait();
            if (result.isPresent()) {
                controller.addBook(result.get());
                build();
            }
        });

        HBox addButtonBox = new HBox();
        addButtonBox.setStyle("-fx-alignment: center;");
        addButtonBox.getChildren().add(addButton);

        VBox booksBox = new VBox();
        booksBox.setStyle("-fx-alignment: center;");

        HashMap<String, OrderedBookSet> gtb = controller.getGenreToBooks();
        for (String genre : gtb.keySet()) {
            if (!gtb.get(genre).isEmpty()) {

                Label genreLabel = new Label(genre);
                genreLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-font-family: Arial;");

                FlowPane booksPane = new FlowPane();
                booksPane.setStyle("-fx-alignment: center-left; -fx-hgap: 5; -fx-vgap: 5;");

                for (Book book : gtb.get(genre)) {
                    Button bookButton = new Button();
                    bookButton.setGraphic(new BookPane(book));
                    bookButton.setOnAction(e -> {
                        Optional<Book> result = new EditBook(book, controller).showAndWait();
                        if (result.isPresent()) {
                            Book newBook = result.get();
                            controller.updateBook(book, newBook);
                        }
                        build();
                    });
                    booksPane.getChildren().add(bookButton);
                }

                BorderPane genrePane = new BorderPane();
                genrePane.setTop(genreLabel);
                genrePane.setCenter(booksPane);

                booksBox.getChildren().add(genrePane);
            }
        }

        BorderPane root = new BorderPane();
        root.setTop(addButtonBox);
        root.setCenter(booksBox);

        getChildren().add(root);
    }

}
