package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProfileManager;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

/**
 * Controller for managing login screen interactions
 * @author Hamza Bosno
 */
public class LoginController {

    private final ProfileManager profileManager = new ProfileManager();
    public Button loginButton;
    public Hyperlink registerLink;
    public TextField emailField;
    public PasswordField passwordField;

    /**
     * Method modelling an OnClick button listener for login button. Validates entered data and proceeds accordingly.
     * @param actionEvent
     */
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

    /**
     * Method modelling an OnClick listener for register hyperlink. Redirects user to registration screen.
     * @param actionEvent
     */
    public void registerLinkOnClick(ActionEvent actionEvent) {
    }
}
