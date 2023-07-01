package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CardManager;
import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.domain.CardType;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * Utility function for determining display name for category label.
     * @param category - enum to string value from db
     * @return display name string
     */
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

    public void activateCouponButtonOnClick(ActionEvent actionEvent) throws AppException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Aktiviranje mjesečnog kupona");
        alert.setHeaderText("Mjesečni kupon");
        alert.setContentText("Želite li aktivirati mjesečni kupon? Iznos će biti preuzet sa trenutnog stanja SMART kartice.\n" +
                "Studenti: 20 KM\nSrednja škola: 16 KM\n Osnovna škola: 16KM\n Radnička: 25 KM\n Penzionerska: 20 KM\nOstali: 23 KM);");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String selectedItem = cardsList.getSelectionModel().getSelectedItem();
            Card selectedCard = cardManager.getCard(Integer.parseInt(selectedItem));
            if(balanceNegative(selectedCard.getCardType())){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText("Neuspješna aktivacija");
                alert.setContentText("Nedovoljan iznos na kartici!");
                alert.showAndWait();
            }
            else{
                selectedCard.setBalance(newBalance(selectedCard.getCardType(), selectedCard.getBalance()));
                cardManager.updateCard(selectedCard);
                balanceField.setText(String.valueOf(selectedCard.getBalance()));
            }
        }
    }

    private boolean balanceNegative(CardType type){
        switch(type){
            case STUDENT:
            case PENSIONER:
                return (Double.parseDouble(balanceField.getText()) - 20) < 0;
            case HIGH_SCHOOL:
            case ELEMENTARY:
                return (Double.parseDouble(balanceField.getText()) - 16) < 0;
            case WORKER:
                return (Double.parseDouble(balanceField.getText()) - 25) < 0;
        }
        return (Double.parseDouble(balanceField.getText()) - 23) < 0;
    }

    private double newBalance(CardType type, double balance){
        switch(type){
            case STUDENT:
            case PENSIONER:
                return balance - 20;
            case HIGH_SCHOOL:
            case ELEMENTARY:
                return balance - 16;
            case WORKER:
                return balance - 25;
        }
        return balance - 23;
    }

    public void addCardButtonOnClick(ActionEvent actionEvent) {
    }

    public void backButtonOnClick(ActionEvent actionEvent) {
    }

    /**
     * On click listener method for remove button. Removes currently selected card both from listview and from database.
     */
    public void removeCardButtonOnClick(ActionEvent actionEvent) throws AppException {
        String selectedItem = cardsList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            cardsList.getItems().remove(selectedItem);
            serialNumberField.setText("");
            cardTypeField.setText("");
            couponField.setText("");
            balanceField.setText("");
            Card selectedCard = cardManager.getCard(Integer.parseInt(selectedItem));
            cardManager.deleteCard(selectedCard.getId());
        }
    }
}
