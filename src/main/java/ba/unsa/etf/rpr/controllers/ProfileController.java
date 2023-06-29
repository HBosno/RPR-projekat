package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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

    /**
     * Populates textfields with user's info on start. Detects changes after which confirm button becomes enabled.
     */
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

    /**
     * On click listener method for confirm button. If email is valid, updates database with new info. User is informed with a popup
       window, after which confirm button is disabled again. Otherwise, user is alerted that faulty info had been entered.
     */

    public void confirmButtonOnClick(ActionEvent actionEvent) {
        if(validateEmail(emailField.getText())){
            user.setEmail(emailField.getText());
            user.setName(nameField.getText());
            user.setSurname(surnameField.getText());
            user.setAdress(adressField.getText());
            user.setTelephoneNumber(telephoneField.getText());
            profileManager.updateProfile(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Obavijest");
            alert.setHeaderText("Uspješna izmjena podataka!");
            alert.setContentText("Vaš račun je ažuriran.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("img/icon.png"));
            alert.showAndWait();
            confirmButton.setDisable(true);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText("Neispravni podaci!");
            alert.setContentText("Email adresa nije ispravna.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("img/icon.png"));
            alert.showAndWait();
        }
    }

    /**
     * Regex for validating user email.
     * @param email - email user entered
     * @return true if email is valid, false otherwise
     */

    public boolean validateEmail(String email){
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Cancel button on click listener method. Closes edit profile window and redirects user to dashboard.
     */

    public void cancelButtonOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
        stage.setTitle("JavniPrevozKS");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.getIcons().add(new Image("img/icon.png"));
        stage.setResizable(false);
        stage.show();
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
        currentStage.close();
    }
}
