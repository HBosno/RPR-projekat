package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RegistrationController {
    public TextField emailField;
    public Label emailWarning;
    public TextField nameField;
    public TextField surnameField;
    public PasswordField passwordField;
    public Label passwordWarning;
    public Button registerButton;

    @FXML
    public void initialize(){
        emailField.textProperty().addListener((obs, oldValue, newValue)->{
            if(validateEmail(newValue))
                emailWarning.setText("");
            else
                emailWarning.setText("Neispravna email adresa!");
        });
        passwordField.textProperty().addListener((obs, oldValue, newValue)->{
            if(validatePassword(newValue))
                passwordWarning.setText("");
            else
                passwordWarning.setText("Password mora sadržavati najmanje 5 karaktera, \nbarem jednu cifru, barem jedan posebni karakter, \n"
                        + "barem jedno veliko slovo i barem jedno malo slovo!");
        });
    }

    /**
     * (?=.*[0-9]) asserts that there is at least one digit.
     * (?=.*[!@#$%^&*]) asserts that there is at least one special character.
     * (?=.*[a-z]) asserts that there is at least one lowercase letter.
     * (?=.*[A-Z]) asserts that there is at least one uppercase letter.
     * .{5,} matches any character (except newline) at least 5 times.
     * @param password - password user entered on registration screen
     * @return true if password matches password regex, false otherwise
     */
    public boolean validatePassword(String password){
        String passwordRegex = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{5,}$";
        return password.matches(passwordRegex);
    }

    /**
     * [A-Za-z0-9._%+-]+ matches one or more alphanumeric characters, dots, underscores, percent signs, plus signs, or hyphens for the email username part.
     * @ matches the "@" symbol.
     * [A-Za-z0-9.-]+ matches one or more alphanumeric characters, dots, or hyphens for the domain name part.
     * \\. matches the dot (.) symbol, which needs to be escaped with double backslashes in Java.
     * [A-Za-z]{2,} matches two or more alphabetic characters for the top-level domain (TLD).
     * @param email - email user entered on registration screen
     * @return true if email matches email regex, false otherwise
     */
    public boolean validateEmail(String email){
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Method modelling an OnClick listener for register button. Redirects user to login screen if valid data is entered, user is alerted otherwise.
     * @param actionEvent
     */
    public void registerButtonOnClick(ActionEvent actionEvent) {
        if(!(validatePassword(passwordField.getText())) || !(validateEmail(emailField.getText()))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText("Neispravni podaci!");
            alert.setContentText("Email adresa i/ili password nisu ispravni.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("img/icon.png"));
            alert.showAndWait();
        }
        else{
            // otvori login screen
        }
    }
}
