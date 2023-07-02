package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.RouteFavouriteManager;
import ba.unsa.etf.rpr.business.RouteManager;
import ba.unsa.etf.rpr.domain.Route;
import ba.unsa.etf.rpr.domain.RouteFavourite;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing favourite routes interface interactions.
 * @author Hamza Bosno
 */
public class FavouriteRoutesController {

    private final RouteFavouriteManager routeFavouriteManager = new RouteFavouriteManager();
    private final RouteManager routeManager = new RouteManager();
    public ListView<String> routesList;
    public Label relationLabel;
    public Label frequencyLabel;
    public RadioButton workDaysRadioButton;
    public RadioButton weekendRadioButton;
    public Button backButton;
    private int userId;

    /**
     * Controller constructor for setting logged in user's id.
     * @param id - user id
     */
    public FavouriteRoutesController(int id){
        userId = id;
    }

    /**
     * Initialize method that populates listview with favourite routes from database and regulates displayed info based on radiobutton
       selections. Makes sure only one radiobutton can be selected at time, and user cannot deselect both radio buttons.
     */
    @FXML
    public void initialize() throws AppException {
        List<RouteFavourite> favouriteRoutes = routeFavouriteManager.getAllForUser(userId);
        List<Integer> routeIds = new ArrayList<>();
        for(RouteFavourite route: favouriteRoutes){
            routeIds.add(route.getId());
        }
        List<String> routeNames = new ArrayList<>();
        for(Integer id: routeIds){
            routeNames.add(routeManager.getById(id).getRoute());
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

    /**
     * Private utility method for updating frequency info if weekends radio button is selected.
     * @param frequency - old frequency
     * @return new frequency string
     */
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

    public void backButtonOnClick(ActionEvent actionEvent) {
    }
}
