package Gui.Admin.ManagementPanels;

import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashMap;

import Api.Book;
import Api.OrderedBookSet;
import Gui.BookPane;

import MVC.Controller;

public class Books extends BorderPane {

    private Controller controller;
    private VBox booksBox;

    public Books(Controller controller) {
        this.controller = controller;

        HBox addButtonBox = new HBox();
        addButtonBox.setAlignment(Pos.CENTER);

        Button addButton = new Button("Προσθήκη Βιβλίου");
        addButton.setOnAction(e -> {
            controller.showAddBookBox();
        });
        addButtonBox.getChildren().add(addButton);
        setTop(addButtonBox);

        booksBox = new VBox();
        booksBox.setAlignment(Pos.CENTER);

        buildBooksBox(controller.getGenreToBooks());

        controller.setObservedBooksListener((SetChangeListener.Change<? extends Book> change) -> {
            System.out.println("Books changed!");
            buildBooksBox(controller.getGenreToBooks());
        });
        controller.getObservedBooks().addListener((ob, oldValue, newValue) -> {
            System.out.println("Books changed!");
            buildBooksBox(controller.getGenreToBooks());
        });
        // controller.getObservedBooks().addListener((SetChangeListener.Change<? extends
        // Book> change) -> {
        // System.out.println("Books changed!");
        // buildBooksBox(controller.getGenreToBooks());
        // });
    }

    public void buildBooksBox(HashMap<String, OrderedBookSet> gtb) {
        booksBox = new VBox();
        booksBox.setAlignment(Pos.CENTER);

        for (String genre : gtb.keySet()) {
            if (gtb.get(genre).isEmpty()) {
                continue;
            }

            Label genreLabel = new Label(genre);
            genreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

            HBox genreLabelBox = new HBox();
            genreLabelBox.setAlignment(Pos.CENTER);
            genreLabelBox.getChildren().add(genreLabel);

            FlowPane booksPane = new FlowPane();
            booksPane.setAlignment(Pos.CENTER_LEFT);
            for (Book book : gtb.get(genre)) {
                booksPane.getChildren().add(new BookPane(book));
            }

            BorderPane genrePane = new BorderPane();
            genrePane.setTop(genreLabel);
            genrePane.setCenter(booksPane);

            booksBox.getChildren().add(genrePane);
        }

        setCenter(booksBox);
    }

}
