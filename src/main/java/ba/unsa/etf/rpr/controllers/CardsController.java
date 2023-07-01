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
    public Button removeCardButton;
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
                    cardTypeField.setText(determineCategory(card.getCardType().toString()));
                    if(card.isMonthlyCoupon()) {
                        couponField.setText("Aktiviran");
                        activateCouponButton.setDisable(true);
                    }
                    else {
                        couponField.setText("Neaktiviran");
                        activateCouponButton.setDisable(false);
                    }
                    balanceField.setText(String.valueOf(card.getBalance()) + " KM");
                } catch (AppException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private String determineCategory(String category){
        switch(category){
            case "STUDENT":
                return "Studentska";
            case "HIGH SCHOOL":
                return "Srednja škola";
            case "ELEMENTARY":
                return "Osnovna škola";
            case "WORKER":
                return "Radnička";
            case "PENSIONER":
                return "Penzionerska";
        }
        return "Ostali";
    }

    public void depositButtonOnClick(ActionEvent actionEvent) {
    }

    public void activateCouponButtonOnClick(ActionEvent actionEvent) {
    }

    public void addCardButtonOnClick(ActionEvent actionEvent) {
    }

    public void backButtonOnClick(ActionEvent actionEvent) {
    }

    public void removeCardButtonOnClick(ActionEvent actionEvent) throws AppException {
        String selectedItem = cardsList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            cardsList.getItems().remove(selectedItem);
            serialNumberField.setText("");
            cardTypeField.setText("");
            couponField.setText("");
            balanceField.setText("");
            cardManager.deleteCard(cardManager.getCard(Integer.parseInt(selectedItem)).getId());
        }
    }
}
