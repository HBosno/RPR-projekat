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

    public boolean validateEmail(String email){
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }
}
