package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CardManager;
import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class CardsController {

    private final ProfileManager profileManager = new ProfileManager();
    private final CardManager cardManager = new CardManager();
    public ListView<String> cardsList;
    public Label serialNumberField;
    public Label cardTypeField;
    public Label couponField;
    public Label balanceField;
    public Button depositButton;
    public Button activateCouponButton;
    public Button addCardButton;
    public Button backButton;
    private Profile user;

    /**
     * Controller constructor for fetching current user profile.
     * @param email - user email
     */
    public CardsController(String email) throws AppException {
       user = profileManager.getProfileByEmail(email);
    }

    /**
     * Initialize method used for populating listview with user's cards' serial numbers, as well as implementing the listview's listener
       for populating each card's information labels.
     */
    @FXML
    public void initialize() throws AppException {
        List<Card> cards = cardManager.getUserCards(user.getId());
        List<String> serialNumbers = new ArrayList<>();
        for(Card card: cards){
            serialNumbers.add(String.valueOf(card.getSerialNumber()));
        }
        cardsList.getItems().addAll(serialNumbers);
        cardsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    Card card = cardManager.getCard(Integer.parseInt(newValue));
                    serialNumberField.setText(newValue);
                    String kategorija = card.getCardType().toString();
                    switch(kategorija){
                        case "STUDENT":
                            kategorija = "Student";
                            break;
                        case "HIGH SCHOOL":
                            kategorija = "Srednja škola";
                            break;
                        case "ELEMENTARY":
                            kategorija = "Osnovna škola";
                            break;
                        case "WORKER":
                            kategorija = "Radnička";
                            break;
                        case "PENSIONER":
                            kategorija = "Penzionerska";
                            break;
                        case "OTHER":
                            kategorija = "Ostali";
                    }
                    cardTypeField.setText(kategorija);
                    if(card.isMonthlyCoupon())
                        couponField.setText("Aktiviran");
                    else
                        couponField.setText("Neaktiviran");
                    balanceField.setText(String.valueOf(card.getBalance()));
                } catch (AppException ex) {
                    ex.printStackTrace();
                }
            }
        });
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
