package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DashboardController {

    private final ProfileManager profileManager = new ProfileManager();
    public Button logoutButton;
    public Button editProfileButton;
    public Button smartCardButton;
    public Button routesButton;
    public Button favouriteRoutesButton;
    public Label welcomeLabel;
    private String userEmail;
    public DashboardController(String email){
        userEmail = email;
    }

    @FXML
    public void initialize() throws AppException {
        welcomeLabel.setText("Dobrodošli nazad, " + profileManager.getUserName(userEmail));
    }

    public void logoutButtonOnClick(ActionEvent actionEvent) {
    }

    public void editProfileButtonOnClick(ActionEvent actionEvent) {
    }

    public void smartCardButtonOnClick(ActionEvent actionEvent) {
    }

    public void routesButtonOnClick(ActionEvent actionEvent) {
    }

    public void favouriteRoutesButtonOnClick(ActionEvent actionEvent) {
    }
}
