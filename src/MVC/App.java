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

/**
 * The main class of the application that extends the JavaFX Application class.
 * It represents the entry point of the application and manages the user
 * interface.
 * It is responsible for the main window and
 * the navigation between the different panes.
 * It also contains the history of the navigation, so that the user can go back
 * to the previous pane.
 * 
 * It also contains the controller, which is responsible for the data and the
 * logic of the application.
 */
public class App extends Application {

    private Controller controller;

    private HBox content;
    private Button backButton;

    private Stack<Buildable> history = new Stack<>();

    public static void main(String[] args) {
        launch(args);
    }

    /*
     * The start method is called when the application is launched.
     * It is responsible for setting up the main window and the initial view with
     * the home pane.
     * It also sets up the event filter for the back button of the mouse.
     */
    @Override
    public void start(Stage stage) {
        controller = new Controller(this);

        stage.setTitle("Βιβλιοθήκη");

        Scene scene = new Scene(buildScrollPane(), 800, 800);
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

    /*
     * Navigates to the previous pane in the history.
     * If the history is empty, the user is in the home pane and nothing happens.
     */
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

    /**
     * Sets the view of the application to the given pane.
     */
    public void setView(Buildable pane, boolean notInHomePane) {
        backButton.setVisible(notInHomePane);

        if (notInHomePane)
            history.push(pane);
        content.getChildren().clear();
        pane.build();
        content.getChildren().add(pane);
    }

}
