package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProfileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("img/icon.png"));
            alert.showAndWait();
        }
    }

    /**
     * Method modelling an OnClick listener for register hyperlink. Redirects user to registration screen.
     * @param actionEvent
     */
    public void registerLinkOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/registration.fxml"));
        stage.setTitle("JavniPrevozKS");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.getIcons().add(new Image("img/icon.png"));
        stage.setResizable(false);
        stage.show();
        Stage currentStage = (Stage) loginButton.getScene().getWindow();
        currentStage.close();
    }
}
