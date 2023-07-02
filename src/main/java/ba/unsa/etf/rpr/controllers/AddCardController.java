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
    private final ProfileManager profileManager = new ProfileManager();
    public TextField serialNumberField;
    public ChoiceBox<String> choiceBox;
    public Button addButton;
    public Button cancelButton;
    private Profile user;

    public AddCardController(Profile profile){
        user = profile;
    }

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

    private boolean validateSerialNumber(String input){
        String serialNoRegex = "\\d{0,9}";
        return input.matches(serialNoRegex);
    }

    public void addButtonOnClick(ActionEvent actionEvent) throws AppException {
        if(validateSerialNumber(serialNumberField.getText())){
            Card card = new Card (-1, Integer.parseInt(serialNumberField.getText()), determineCategory(choiceBox.getValue()), 0, false, user);
            cardManager.addCard(card);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška pri dodavanju kartice");
            alert.setContentText("Neispravan serijski broj!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("img/icon.png"));
            alert.showAndWait();
        }
    }

    private CardType determineCategory(String category){
        switch(category){
            case "Studentska":
                return CardType.valueOf("STUDENT");
            case "Srednja škola":
                return CardType.valueOf("HIGH SCHOOL");
            case "Osnovna škola":
                return CardType.valueOf("ELEMENTARY");
            case "Radnička":
                return CardType.valueOf("WORKER");
            case "Penzionerska":
                return CardType.valueOf("PENSIONER");
        }
        return CardType.valueOf("OTHER");
    }

    public void cancelButtonOnClick(ActionEvent actionEvent) {
    }
}
