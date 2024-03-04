package Gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class Form extends BorderPane {
    private int rows = 0;
    private GridPane formPane;
    private Button submitButton;

    private HashMap<String, TextField> fields = new HashMap<>();

    public Form(String title, Button submitButton) {
        this.submitButton = submitButton;

        setPadding(new Insets(5));

        // -------Title-------
        HBox titleBox = new HBox();
        Label titleLabel = new Label(title);
        Font font = Font.font("Arial", FontWeight.BOLD, 20);
        titleLabel.setFont(font);
        titleBox.getChildren().add(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        // -------Form-------
        VBox formBox = new VBox();
        formPane = new GridPane();
        formPane.setPadding(new Insets(5));
        formPane.setHgap(5);
        formPane.setVgap(5);
        formBox.getChildren().add(formPane);
        formBox.setAlignment(Pos.CENTER);

        // -------Button-------
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(submitButton);

        setTop(titleBox);
        setCenter(formBox);
        setBottom(buttonBox);
    }

    public void addField(String label, TextField field) {
        formPane.add(new Label(label), 0, rows);
        formPane.add(field, 1, rows++);
        fields.put(label, field);
    }

    public void addEventHandler(Runnable handler) {
        submitButton.setOnAction(e -> handler.run());
    }

    public HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<>();
        fields.forEach((label, field) -> data.put(label, field.getText()));
        return data;
    }

}
