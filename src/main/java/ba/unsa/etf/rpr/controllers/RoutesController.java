package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RouteManager;
import ba.unsa.etf.rpr.domain.Route;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class RoutesController {
    private final RouteManager routeManager = new RouteManager();
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
    public void initialize() throws AppException {
        List<Route> routes = routeManager.getAllRoutes();
        List<String> routeNames = new ArrayList<>();
        for(Route route: routes){
            routeNames.add(route.getRoute());
        }
        routesList.getItems().addAll(routeNames);
    }

    public void addButtonOnClick(ActionEvent actionEvent) {
    }

    public void removeButtonOnClick(ActionEvent actionEvent) {
    }

    public void backButtonOnClick(ActionEvent actionEvent) {
    }
}
