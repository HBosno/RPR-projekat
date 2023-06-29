package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller for profile editing screen
 * @author Hamza Bosno
 */
public class ProfileController {

    private final ProfileManager profileManager = new ProfileManager();
    public TextField emailField;
    public TextField nameField;
    public TextField surnameField;
    public TextField adressField;
    public TextField telephoneField;
    public Button confirmButton;
    public Button cancelButton;
    private Profile user;

    public ProfileController(String email) throws AppException {
        user = profileManager.getProfileByEmail(email);
    }
    @FXML
    public void initialize(){
        emailField.setText(user.getEmail());
        nameField.setText(user.getName());
        surnameField.setText(user.getSurname());
        adressField.setText(user.getAdress());
        telephoneField.setText(user.getTelephoneNumber());
        emailField.setOnKeyTyped(e -> confirmButton.setDisable(false));
        nameField.setOnKeyTyped(e -> confirmButton.setDisable(false));
        surnameField.setOnKeyTyped(e -> confirmButton.setDisable(false));
        adressField.setOnKeyTyped(e -> confirmButton.setDisable(false));
        telephoneField.setOnKeyTyped(e -> confirmButton.setDisable(false));
    }

    public void confirmButtonOnClick(ActionEvent actionEvent) {
    }

    public void cancelButtonOnClick(ActionEvent actionEvent) {
    }
}
