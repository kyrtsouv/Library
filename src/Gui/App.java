package Gui;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import MVC.Controller;

public class App extends Application {

    private Controller controller;

    public static void main(String[] args) {
        new Controller();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Βιβλιοθήκη");
        primaryStage.setScene(new Scene(controller.getHomePane()));
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
