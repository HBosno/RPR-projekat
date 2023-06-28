package ba.unsa.etf.rpr.controllers;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {
    public TextField emailField;
    public Label emailWarning;
    public TextField nameField;
    public TextField surnameField;
    public PasswordField passwordField;
    public Label passwordWarning;

    public boolean validatePassword(String password){
        String passwordRegex = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{5,}$";
        return password.matches(passwordRegex);
    }
}
