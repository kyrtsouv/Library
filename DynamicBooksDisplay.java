import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import Api.Book;
import Gui.BookPane;

public class DynamicBooksDisplay extends Application {

    private final SimpleObjectProperty<ObservableMap<String, ObservableSet<Book>>> gtbProperty = new SimpleObjectProperty<>(
            FXCollections.observableHashMap());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox outerBooksBox = new VBox();
        outerBooksBox.setAlignment(Pos.CENTER);

        // Bind outerBooksBox content to gtbProperty
        gtbProperty.addListener((observable, oldValue, newValue) -> {
            outerBooksBox.getChildren().clear();
            for (String genre : newValue.keySet()) {
                if (newValue.get(genre).isEmpty()) {
                    continue;
                }
                BorderPane genrePane = new BorderPane();

                Label genreLabel = new Label(genre);
                genreLabel.setAlignment(Pos.CENTER);
                genreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                genrePane.setTop(genreLabel);

                FlowPane booksPane = new FlowPane();
                booksPane.setAlignment(Pos.CENTER);

                for (Book book : newValue.get(genre)) {
                    booksPane.getChildren().add(new BookPane(book));
                }

                genrePane.setCenter(booksPane);

                outerBooksBox.getChildren().add(genrePane);
            }
        });

        // Bind outerBooksBox to the gtbProperty
        outerBooksBox.visibleProperty().bind(gtbProperty.isNotNull());
        outerBooksBox.managedProperty().bind(outerBooksBox.visibleProperty());

        BorderPane root = new BorderPane();
        root.setCenter(outerBooksBox);

        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setTitle("Dynamic Books Display");
        primaryStage.show();

        // Simulating changes to gtb (Replace this with your actual logic)

        Book book1 = new Book("Book 1", "Author 1", "Publisher 1", "ISBN 1", "2021", 1);
        gtbProperty.get().put("Fiction", FXCollections.observableSet(new HashSet<>() {
            {
                add(book1);
            }
        }));
        gtbProperty.get().put("Science Fiction", FXCollections.observableSet(new HashSet<>()));

        Book book2 = new Book("Book 2", "Author 2", "Publisher 2", "ISBN 2", "2022", 2);
        gtbProperty.get().get("Science Fiction").add(book2);

        Book book3 = new Book("Book 3", "Author 3", "Publisher 3", "ISBN 3", "2023", 3);
        gtbProperty.get().get("Science Fiction").add(book3);
    }

}
