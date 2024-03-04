package Gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;

import MVC.Controller;

public class HomePane extends BorderPane {
    public HomePane(Controller controller, Form signUpForm, Form signInForm) {
        BorderPane login = new BorderPane();

        HBox top = new HBox();
        HBox forms = new HBox();
        HBox bot = new HBox();

        Line line = new Line();
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        line.startXProperty().bind(top.widthProperty().divide(2));
        line.startYProperty().bind(top.heightProperty());
        line.endXProperty().bind(bot.widthProperty().divide(2));
        line.endYProperty().bind(bot.layoutYProperty().subtract(10));

        forms.getChildren().addAll(signUpForm, line, signInForm);
        forms.setAlignment(Pos.CENTER);

        forms.setMinWidth(forms.getWidth());
        forms.setMinHeight(forms.getHeight());

        login.setTop(top);
        login.setCenter(forms);
        login.setBottom(bot);

        setTop(login);

        ScrollPane scrollPane = new ScrollPane();

        TilePane books = new TilePane();
        books.setHgap(10);
        books.setVgap(10);
        books.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        controller.getBestBooks().forEach(book -> books.getChildren().add(new BookPane(book)));

        scrollPane.setContent(books);
        scrollPane.setFitToWidth(true);

        setCenter(scrollPane);
    }
}
