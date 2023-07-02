package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;

public class RoutesController {
    public ListView<String> routesList;
    public Label relationLabel;
    public Label frequencyLabel;
    public RadioButton workDaysRadioButton;
    public RadioButton weekendRadioButton;
    public Button addButton;
    public Button removeButton;
    public Button backButton;
    private int userId;

    public RoutesController(int id){
        userId = id;
    }

    @FXML
    public void initialize(){

    }

    public void addButtonOnClick(ActionEvent actionEvent) {
    }

    public void removeButtonOnClick(ActionEvent actionEvent) {
    }

    public void backButtonOnClick(ActionEvent actionEvent) {
    }
}
