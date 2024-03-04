// package Gui;

// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.GridPane;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.Button;

// public class SignInPane extends BorderPane {
// private TextField usernameField;
// private PasswordField passwordField;
// private Button signInButton;

// public SignInPane() {

// GridPane formPane = new GridPane();
// formPane.setHgap(10);
// formPane.setVgap(10);
// formPane.setPadding(new javafx.geometry.Insets(10));

// Label usernameLabel = new Label("Username:");
// Label passwordLabel = new Label("Password:");
// usernameField = new TextField();
// passwordField = new PasswordField();

// signInButton = new Button("Sign In");

// // Add the components to the grid pane
// formPane.add(usernameLabel, 0, 1);
// formPane.add(usernameField, 1, 1);
// formPane.add(passwordLabel, 0, 2);
// formPane.add(passwordField, 1, 2);

// // setCenter(new Form("Sign In", formPane, signInButton));
// }
// }
