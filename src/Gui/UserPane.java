package Gui;

import MVC.Controller;
import javafx.scene.layout.Pane;
import Api.User;

public class UserPane extends Pane {

    private Controller controller;

    private User user;

    public UserPane(Controller controller, User user) {
        this.controller = controller;
        this.user = user;

        // TODO

    }

}
