package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CardManager;
import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.domain.*;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Controller for managing smart card user interface.
 * @author Hamza Bosno
 */
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
    public boolean enableNotifications = false;
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
       for populating each card's information labels. Also manages enabling and disabling of certain buttons.
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
                    removeCardButton.setDisable(false);
                    activateCouponButton.setDisable(false);
                    depositButton.setDisable(false);
                    Card card = cardManager.getCard(Integer.parseInt(newValue));
                    Card selectedCard = new BasicCard(card.getId(), card.getSerialNumber(), card.getCardType(), card.getBalance(), card.isMonthlyCoupon(), card.getProfile());
                    if (selectedCard.getCardType() == CardType.STUDENT) {
                        selectedCard = new DiscountedCard(selectedCard, 0.10); // 10% discount for students
                    }
                    serialNumberField.setText(newValue);
                    cardTypeField.setText(selectedCard.getDescription());
                    if(selectedCard.isMonthlyCoupon()) {
                        couponField.setText("Aktiviran");
                        activateCouponButton.setDisable(true);
                    }
                    else {
                        couponField.setText("Neaktiviran");
                        activateCouponButton.setDisable(false);
                    }
                    balanceField.setText(String.valueOf(selectedCard.getBalance()) + " KM");
                } catch (AppException ex) {
                    ex.printStackTrace();
                }
            }
            else{
                removeCardButton.setDisable(true);
                activateCouponButton.setDisable(true);
                depositButton.setDisable(true);
            }
        });
    }

    /**
     * On click listener method for deposit button. Allow user to enter a non negative value to update card balance. On faulty input,
       alert dialog window is shown.
     */
    public void depositButtonOnClick(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Dopuni karticu");
        dialog.setHeaderText("Plaćanje kreditnom karticom");
        dialog.setContentText("Unesite iznos u KM za dopunu:");
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("img/icon.png"));
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            try{
                Double newBalance = Double.parseDouble(result.get());
                if(newBalance < 0)
                    throw new NumberFormatException();
                String selectedItem = cardsList.getSelectionModel().getSelectedItem();
                Card selectedCard = cardManager.getCard(Integer.parseInt(selectedItem));
                selectedCard.setBalance(selectedCard.getBalance() + newBalance);
                cardManager.updateCard(selectedCard);
                balanceField.setText(selectedCard.getBalance() + " KM");
            }
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText("Greška pri transakciji");
                alert.setContentText("Unijeli ste neispravnu vrijednost. Molimo unesite nenegativan numerički iznos u KM.");
                stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("img/icon.png"));
                alert.showAndWait();
            } catch (AppException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * On click listener for activate coupon button. Handles transaction based on card type and if sufficient value on card balance is
       available.
     */
    public void activateCouponButtonOnClick(ActionEvent actionEvent) throws AppException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Aktiviranje mjesečnog kupona");
        alert.setHeaderText("Mjesečni kupon");
        alert.setContentText("Želite li aktivirati mjesečni kupon? Iznos će biti preuzet sa trenutnog stanja SMART kartice.\n" +
                "Studenti: 20 KM\nSrednja škola: 16 KM\nOsnovna škola: 16KM\nRadnička: 25 KM\nPenzionerska: 20 KM\nOstali: 23 KM");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("img/icon.png"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String selectedItem = cardsList.getSelectionModel().getSelectedItem();
            Card card = cardManager.getCard(Integer.parseInt(selectedItem));

            // Wrap the card with a DiscountedCard decorator if needed
            Card selectedCard = new BasicCard(card.getId(), card.getSerialNumber(), card.getCardType(), card.getBalance(), card.isMonthlyCoupon(), card.getProfile());
            if (selectedCard.getCardType() == CardType.STUDENT) {
                selectedCard = new DiscountedCard(selectedCard, 0.10); // 10% discount for students
            }

            selectedCard = new NotificationCard(selectedCard, user.getEmail());
            if(selectedCard.balanceNegative()){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Greška");
                alert1.setHeaderText("Greška pri aktivaciji");
                alert1.setContentText("Nedovoljan iznos na kartici!");
                stage = (Stage) alert1.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("img/icon.png"));
                alert1.showAndWait();
            }
            else{
                selectedCard.setBalance(selectedCard.newBalance());
                selectedCard.setMonthlyCoupon(true);
                if(enableNotifications) {
                    NotificationCard notificationCard = new NotificationCard(selectedCard, user.getEmail());
                    notificationCard.sendCouponActivatedNotification();
                    if (notificationCard.getBalance() < 16) {
                        notificationCard.sendLowBalanceNotification();
                    }
                }
                couponField.setText("Aktiviran");
                activateCouponButton.setDisable(true);
                cardManager.updateCard(selectedCard);
                balanceField.setText(selectedCard.getBalance() + " KM");
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Uspješna aktivacija");
                alert2.setHeaderText(null);
                alert2.setContentText("Uspješno ste aktivirali mjesečni kupon!");
                stage = (Stage) alert2.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("img/icon.png"));
                alert2.showAndWait();
            }
        }
    }

    public void addCardButtonOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addCard.fxml"));
        loader.setController(new AddCardController(user));
        stage.setTitle("JavniPrevozKS");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setWidth(500);
        stage.setHeight(400);
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/icon.png"));
        stage.show();
        stage.setOnHiding(x->{
            List<Card> cards = null;
            try {
                cards = cardManager.getUserCards(user.getId());
            } catch (AppException e) {
                e.printStackTrace();
            }
            List<String> serialNumbers = new ArrayList<>();
            for(Card card: cards){
                serialNumbers.add(String.valueOf(card.getSerialNumber()));
            }
            cardsList.getItems().clear();
            cardsList.getItems().addAll(serialNumbers);
        });
    }

    public void backButtonOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
        loader.setController(new DashboardController(user.getEmail()));
        stage.setTitle("JavniPrevozKS");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/icon.png"));
        stage.show();
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        currentStage.close();
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
