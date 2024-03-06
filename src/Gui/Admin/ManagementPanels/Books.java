package Gui.Admin.ManagementPanels;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.HashSet;

import Api.Book;

import Gui.BookPane;

import MVC.Controller;

public class Books extends Pane {

    private Controller controller;

    private BorderPane panel;

    public Books(Controller controller) {
        this.controller = controller;

        this.panel = new BorderPane();

        HBox addButtonBox = new HBox();
        addButtonBox.setAlignment(Pos.CENTER);

        Button addButton = new Button("Προσθήκη Βιβλίου");
        addButton.setOnAction(e -> {
            controller.showAddBookBox();
        });
        addButtonBox.getChildren().add(addButton);
        panel.setTop(addButtonBox);

        VBox outerBooksBox = new VBox();
        outerBooksBox.setAlignment(Pos.CENTER);

        HashMap<String, HashSet<Book>> gtb = controller.getGenreToBooks();
        for (String genre : gtb.keySet()) {
            BorderPane genrePane = new BorderPane();

            Label genreLabel = new Label(genre);
            genrePane.setTop(genreLabel);

            FlowPane booksPane = new FlowPane();
            booksPane.setAlignment(Pos.CENTER);

            for (Book book : gtb.get(genre)) {
                booksPane.getChildren().add(new BookPane(book));
            }

            genrePane.setCenter(booksPane);

            outerBooksBox.getChildren().add(genrePane);
        }

        panel.setCenter(outerBooksBox);
        getChildren().add(panel);

    }

}
