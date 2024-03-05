package Gui;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import MVC.Controller;

public class App extends Application {

    private Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.controller = new Controller();

        primaryStage.setTitle("Βιβλιοθήκη");
        primaryStage.setScene(new Scene(controller.getHomePane()));
        primaryStage.show();
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());

        primaryStage.setOnCloseRequest(e -> {
            controller.saveData();
        });
    }

}
