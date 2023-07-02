package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Controller for managing add card user interface.
 * @author Hamza Bosno
 */

public class AddCardController {

    public TextField serialNumberField;
    public ChoiceBox<String> choiceBox;
    public Button addButton;
    public Button cancelButton;
    private int userId;

    public AddCardController(int id){
        userId = id;
    }

    @FXML
    public void initialize(){
        choiceBox.getItems().addAll("Studentska", "Srednja škola", "Osnovna škola", "Radnička", "Penzionerska", "Ostali");
    }

    public void addButtonOnClick(ActionEvent actionEvent) {
    }

    public void cancelButtonOnClick(ActionEvent actionEvent) {
    }
}
