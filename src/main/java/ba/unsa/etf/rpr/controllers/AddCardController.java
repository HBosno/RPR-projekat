package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CardManager;
import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.domain.CardType;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Controller for managing add card user interface.
 * @author Hamza Bosno
 */

public class AddCardController {

    private final CardManager cardManager = new CardManager();
    public TextField serialNumberField;
    public ChoiceBox<String> choiceBox;
    public Button addButton;
    public Button cancelButton;
    private Profile user;

    public AddCardController(Profile profile){
        user = profile;
    }

    /**
     * Initialize method that populates view's choicebox and implements listeners for choicebox and serial number entry field in order
       to manage enabling of add button.
     */
    @FXML
    public void initialize(){
        choiceBox.getItems().addAll("Studentska", "Srednja škola", "Osnovna škola", "Radnička", "Penzionerska", "Ostali");
        choiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !serialNumberField.getText().equals("")) {
                addButton.setDisable(false);
            }
        });
        serialNumberField.textProperty().addListener((obs, oldValue, newValue)->{
            if(!newValue.equals("") && choiceBox.getValue() != null) {
                addButton.setDisable(false);
            }
        });
    }

    /**
     * Private utility method for validating serial number input with regex.
     * @param input - entered serial number
     * @return true if matches regex, false otherwise
     */
    private boolean validateSerialNumber(String input){
        String serialNoRegex = "\\d{0,9}";
        return input.matches(serialNoRegex);
    }

    /**
     * On click listener method for add button. Checks if card is already in database and if serial number matches regex
       requirements, then adds the card to database. Otherwise alerts the user.
     */
    public void addButtonOnClick(ActionEvent actionEvent){
        if(validateSerialNumber(serialNumberField.getText()) && !cardManager.cardExists(Integer.parseInt(serialNumberField.getText()))){
            Card card = new Card (-1, Integer.parseInt(serialNumberField.getText()), determineCategory(choiceBox.getValue()), 0, false, user);
            try {
                cardManager.addCard(card);
            } catch (AppException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška pri dodavanju kartice");
            alert.setContentText("Neispravan serijski broj/Kartica već dodana.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("img/icon.png"));
            alert.showAndWait();
        }
    }

    /**
     * Utility method for determining card type when creating a Card bean, based on choice from choicebox.
     * @param category - chosen string value from choicebox
     * @return respective CardType enum
     */
    private CardType determineCategory(String category){
        switch(category){
            case "Studentska":
                return CardType.valueOf("STUDENT");
            case "Srednja škola":
                return CardType.valueOf("HIGH_SCHOOL");
            case "Osnovna škola":
                return CardType.valueOf("ELEMENTARY");
            case "Radnička":
                return CardType.valueOf("WORKER");
            case "Penzionerska":
                return CardType.valueOf("PENSIONER");
        }
        return CardType.valueOf("OTHER");
    }

    /**
     * On click listener method for cancel button. Simply closes add card view.
     */
    public void cancelButtonOnClick(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
