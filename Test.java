import java.util.HashSet;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.Button;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox content = new VBox();

        TextField textField = new TextField();

        Button button = new Button("Click me!");

        ObservableMap<String, ObservableSet<String>> strings = FXCollections.observableHashMap();
        strings.put("strings", FXCollections.observableSet(new HashSet<String>()));

        button.setOnAction(e -> {
            strings.get("strings").add(textField.getText());
        });

        strings.addListener((MapChangeListener<String, ObservableSet<String>>) change -> {
            System.out.println("Map changed!");
        });
        VBox root = new VBox();
        root.getChildren().addAll(content, textField, button);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
