package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CardsController {

    private final ProfileManager profileManager = new ProfileManager();
    public ListView cardsList;
    public Label serialNumberField;
    public Label cardTypeField;
    public Label couponField;
    public Label balanceField;
    public Button depositButton;
    public Button activateCouponButton;
    public Button addCardButton;
    public Button backButton;
    private Profile user;

    public CardsController(String email) throws AppException {
       user = profileManager.getProfileByEmail(email);
    }

    public void depositButtonOnClick(ActionEvent actionEvent) {
    }

    public void activateCouponButtonOnClick(ActionEvent actionEvent) {
    }

    public void addCardButtonOnClick(ActionEvent actionEvent) {
    }

    public void backButtonOnClick(ActionEvent actionEvent) {
    }
}
