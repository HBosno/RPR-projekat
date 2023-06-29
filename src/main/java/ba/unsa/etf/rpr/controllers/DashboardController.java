package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Controller for managing dashboard interactions
 * @author Hamza Bosno
 */
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

    /**
     * Sets up dashboard's welcome label based on signed in user's name from db.
     */
    @FXML
    public void initialize() throws AppException {
        welcomeLabel.setText("Dobrodošli nazad, " + profileManager.getUserName(userEmail));
    }

    /**
     * On click listener method for logout button. Redirects user to login screen.
     */
    public void logoutButtonOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setTitle("JavniPrevozKS");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.getIcons().add(new Image("img/icon.png"));
        stage.setResizable(false);
        stage.show();
        Stage currentStage = (Stage) logoutButton.getScene().getWindow();
        currentStage.close();
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
