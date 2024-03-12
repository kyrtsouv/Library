package MVC;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.Stack;

import Gui.Common.Buildable;
import Gui.Common.HomePane;

public class App extends Application {

    private Controller controller;

    private HBox content;
    private Button backButton;

    private Stack<Buildable> history = new Stack<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        controller = new Controller(this);

        stage.setTitle("Βιβλιοθήκη");

        Scene scene = new Scene(buildScrollPane(), 800, 600);
        scene.addEventFilter((MouseEvent.MOUSE_CLICKED), event -> {
            if (event.getButton() == MouseButton.BACK) {
                goBack();
                event.consume();
            }
        });

        stage.setScene(scene);

        stage.show();
        stage.setOnCloseRequest(e -> {
            controller.saveData();
        });
    }

    private ScrollPane buildScrollPane() {

        backButton = new Button("Πίσω");
        backButton.setVisible(false);
        backButton.setOnAction(e -> goBack());
        HBox backButtonBox = new HBox(backButton);
        backButtonBox.setStyle("-fx-alignment: top-right;");

        content = new HBox(new HomePane(controller));
        content.setStyle("-fx-alignment: center; -fx-padding: 10;");

        BorderPane masterBorder = new BorderPane();
        masterBorder.setTop(backButtonBox);
        masterBorder.setCenter(content);

        ScrollPane root = new ScrollPane();
        root.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        StackPane holder = new StackPane(masterBorder);
        holder.minWidthProperty().bind(
                Bindings.createDoubleBinding(() -> root.getViewportBounds().getWidth(), root.viewportBoundsProperty()));
        root.setContent(holder);

        return root;
    }

    public void goBack() {
        if (history.size() == 0) {
            return;
        }
        if (history.size() == 1) {
            history.pop();
            setView(new HomePane(controller), false);
            return;
        }
        history.pop();
        setView(history.pop(), true);

    }

    public void setView(Buildable pane, boolean notInHomePane) {
        backButton.setVisible(notInHomePane);

        if (notInHomePane)
            history.push(pane);
        content.getChildren().clear();
        pane.build();
        content.getChildren().add(pane);
    }

}
