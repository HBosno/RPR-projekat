package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.business.RouteFavouriteManager;
import ba.unsa.etf.rpr.business.RouteManager;
import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.domain.Route;
import ba.unsa.etf.rpr.domain.RouteFavourite;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class RoutesController {
    private final RouteManager routeManager = new RouteManager();
    private final RouteFavouriteManager routeFavouriteManager = new RouteFavouriteManager();
    private final ProfileManager profileManager = new ProfileManager();
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
        workDaysRadioButton.setSelected(true);
        ToggleGroup toggleGroup = new ToggleGroup();
        workDaysRadioButton.setToggleGroup(toggleGroup);
        weekendRadioButton.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                oldValue.setSelected(true);
            }
        });
        routesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                relationLabel.setText(newValue);
                try {
                    if(routeFavouriteManager.checkForRoute(userId, routeManager.getByName(routesList.getSelectionModel().getSelectedItem()).getId())){
                        addButton.setDisable(true);
                        removeButton.setDisable(false);
                    }
                    else{
                        addButton.setDisable(false);
                        removeButton.setDisable(true);
                    }
                } catch (AppException e) {
                    e.printStackTrace();
                }
                try {
                    if(workDaysRadioButton.isSelected()) {
                        frequencyLabel.setText("Frekventnost: " + routeManager.getByName(newValue).getFrequency());
                    }
                    else{
                        frequencyLabel.setText("Frekventnost: " + newFrequency(routeManager.getByName(newValue).getFrequency()));
                    }
                } catch (AppException e) {
                    e.printStackTrace();
                }
            }
        });
        weekendRadioButton.setOnAction(event -> {
            String selectedRoute = routesList.getSelectionModel().getSelectedItem();
            if(selectedRoute != null){
                try {
                    frequencyLabel.setText("Frekventnost: " + newFrequency(routeManager.getByName(selectedRoute).getFrequency()));
                } catch (AppException e) {
                    e.printStackTrace();
                }
            }
        });
        workDaysRadioButton.setOnAction(event -> {
            String selectedRoute = routesList.getSelectionModel().getSelectedItem();
            if(selectedRoute != null){
                try {
                    frequencyLabel.setText("Frekventnost: " + routeManager.getByName(selectedRoute).getFrequency());
                } catch (AppException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String newFrequency(String frequency){
        switch(frequency){
            case "5 min":
                return "20 min";
            case "15 min":
                return "30 min";
            case "30 min":
                return "1 h";
            case "45 min":
                return "1h 30 min";
            case "55 min":
                return "2 h";
            case "1 h":
                return "1 h 30 min";
        }
        return "";
    }

    public void addButtonOnClick(ActionEvent actionEvent) throws AppException {
        String selectedRoute = routesList.getSelectionModel().getSelectedItem();
        RouteFavourite route = new RouteFavourite(-1, profileManager.getById(userId), routeManager.getByName(selectedRoute));
        routeFavouriteManager.addFavourite(route);
        addButton.setDisable(true);
        removeButton.setDisable(false);
    }

    public void removeButtonOnClick(ActionEvent actionEvent) {
    }

    public void backButtonOnClick(ActionEvent actionEvent) {
    }
}
