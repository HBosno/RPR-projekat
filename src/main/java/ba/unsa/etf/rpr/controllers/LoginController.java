package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProfileManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private final ProfileManager profileManager = new ProfileManager();
    public Button loginButton;
    public Hyperlink registerLink;
    public TextField emailField;
    public PasswordField passwordField;

    public void loginButtonOnClick(ActionEvent actionEvent) {

    }

    public void registerLinkOnClick(ActionEvent actionEvent) {
    }
}
