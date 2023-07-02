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

    /**
     * On click listener method for edit profile button. Redirects user to edit profile screen.
     */
    public void editProfileButtonOnClick(ActionEvent actionEvent) throws IOException, AppException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/profile.fxml"));
        loader.setController(new ProfileController(userEmail));
        stage.setTitle("JavniPrevozKS");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/icon.png"));
        stage.show();
        Stage currentStage = (Stage) editProfileButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * On click listener method for smart card button. Redirects user to smart card overview screen.
     */
    public void smartCardButtonOnClick(ActionEvent actionEvent) throws AppException, IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cards.fxml"));
        loader.setController(new CardsController(userEmail));
        stage.setTitle("JavniPrevozKS");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/icon.png"));
        stage.show();
        Stage currentStage = (Stage) editProfileButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * On click listener method for routes button. Redirects user routes overview.
     */
    public void routesButtonOnClick(ActionEvent actionEvent) throws AppException, IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/routes.fxml"));
        loader.setController(new RoutesController(profileManager.getProfileByEmail(userEmail).getId()));
        stage.setTitle("JavniPrevozKS");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/icon.png"));
        stage.show();
        Stage currentStage = (Stage) editProfileButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * On click listener method for favourite routes button. Redirects favourite routes overview.
     */
    public void favouriteRoutesButtonOnClick(ActionEvent actionEvent) throws AppException, IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/favouriteRoutes.fxml"));
        loader.setController(new FavouriteRoutesController(profileManager.getProfileByEmail(userEmail).getId()));
        stage.setTitle("JavniPrevozKS");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/icon.png"));
        stage.show();
        Stage currentStage = (Stage) editProfileButton.getScene().getWindow();
        currentStage.close();
    }
}
