package Gui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.Stack;

import MVC.Controller;

public class App extends Application {

    private Controller controller;

    private HBox content;
    private Button backButton;

    private Stack<Pane> history = new Stack<>();

    public static void run() {
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.controller = new Controller(this);

        stage.setTitle("Βιβλιοθήκη");

        Scene scene = new Scene(buildScrollPane(), 800, 600);
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.BACK) {
                goBack();
            }
        });

        stage.setScene(scene);

        stage.show();
        stage.setOnCloseRequest(e -> {
            controller.saveData();
        });
    }

    private ScrollPane buildScrollPane() {
        ScrollPane root = new ScrollPane();
        root.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        BorderPane masterBorder = new BorderPane();

        HBox backButtonBox = new HBox();
        backButtonBox.setAlignment(Pos.TOP_RIGHT);

        backButton = new Button("Πίσω");
        backButton.setVisible(false);
        backButton.setOnAction(e -> goBack());

        backButtonBox.getChildren().add(backButton);

        content = new HBox();
        content.setAlignment(Pos.CENTER);

        Pane homePane = new HomePane(controller);
        history.push(homePane);

        content.getChildren().add(homePane);

        masterBorder.setTop(backButtonBox);
        masterBorder.setCenter(content);

        StackPane holder = new StackPane(masterBorder);
        holder.minWidthProperty().bind(
                Bindings.createDoubleBinding(() -> root.getViewportBounds().getWidth(), root.viewportBoundsProperty()));

        root.setContent(holder);

        return root;

    }

    public void goBack() {
        if (history.size() == 1) {
            return;
        }
        if (history.size() == 2) {
            history.pop();
            history.pop();

            setView(new HomePane(controller), false);
            return;
        }
        history.pop();
        setView(history.pop(), true);

    }

    public void setView(Pane pane, boolean showBackButton) {
        backButton.setVisible(showBackButton);

        history.push(pane);
        content.getChildren().clear();
        content.getChildren().add(pane);
    }

}
