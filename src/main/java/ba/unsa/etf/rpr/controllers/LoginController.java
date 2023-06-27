package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProfileManager;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class LoginController {

    private final ProfileManager profileManager = new ProfileManager();
    public Button loginButton;
    public Hyperlink registerLink;
    public TextField emailField;
    public PasswordField passwordField;

    public void loginButtonOnClick(ActionEvent actionEvent) {
        if(profileManager.validateLogin(emailField.getText(), passwordField.getText())){
            emailField.setText("radi");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText("Neispravni podaci!");
            alert.setContentText("Email adresa i/ili password nisu ispravni.");
            alert.showAndWait();
        }
    }

    public void registerLinkOnClick(ActionEvent actionEvent) {
    }
}
