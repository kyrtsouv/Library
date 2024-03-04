// package Gui;

// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.GridPane;
// import javafx.geometry.Insets;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.TextField;

// public class SignUpPane extends BorderPane {

// public SignUpPane() {

// GridPane formPane = new GridPane();
// formPane.setHgap(10);
// formPane.setVgap(10);
// formPane.setPadding(new Insets(10));

// // Create the UI components
// Label usernameLabel = new Label("Username:");
// TextField usernameField = new TextField();

// Label passwordLabel = new Label("Κωδικός:");
// PasswordField passwordField = new PasswordField();

// Label name = new Label("Όνομα:");
// TextField nameField = new TextField();

// Label surname = new Label("Επώνυμο:");
// TextField surnameField = new TextField();

// Label ID = new Label("ΑΔΤ:");
// TextField IDField = new TextField();

// Label email = new Label("Email:");
// TextField emailField = new TextField();

// // Add the components to the grid pane
// formPane.add(usernameLabel, 0, 0);
// formPane.add(usernameField, 1, 0);
// formPane.add(passwordLabel, 0, 1);
// formPane.add(passwordField, 1, 1);
// formPane.add(name, 0, 2);
// formPane.add(nameField, 1, 2);
// formPane.add(surname, 0, 3);
// formPane.add(surnameField, 1, 3);
// formPane.add(ID, 0, 4);
// formPane.add(IDField, 1, 4);
// formPane.add(email, 0, 5);
// formPane.add(emailField, 1, 5);

// Button signUpButton = new Button("Sign Up");
// // setCenter(new Form("Sign Up", formPane, signUpButton));
// }
// }
