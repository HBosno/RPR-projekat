package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.business.RouteFavouriteManager;
import ba.unsa.etf.rpr.business.RouteManager;
import ba.unsa.etf.rpr.domain.RouteFavourite;
import ba.unsa.etf.rpr.domain.TimeTable;
import ba.unsa.etf.rpr.exceptions.AppException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Controller for managing favourite routes interface interactions.
 * @author Hamza Bosno
 */
public class FavouriteRoutesController {

    private final RouteFavouriteManager routeFavouriteManager = new RouteFavouriteManager();
    private final RouteManager routeManager = new RouteManager();
    private final ProfileManager profileManager = new ProfileManager();
    public ListView<RouteFavourite> routesList;

    public Button backButton;
    public Button removeButton;

    public TableView timetable;

    public TableColumn startColumn;

    public RadioButton workDaysRadioButton;

    public RadioButton weekendRadioButton;

    public TableColumn endColumn;

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
       selections. Makes sure only one radiobutton can be selected at time, and user cannot deselect both radio buttons. Handles
       remove button enabling.
     */
    @FXML
    public void initialize() throws AppException {
        List<RouteFavourite> favouriteRoutes = routeFavouriteManager.getAllForUser(userId);
        routesList.getItems().addAll(favouriteRoutes);

        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        workDaysRadioButton.setSelected(true);
        ToggleGroup toggleGroup = new ToggleGroup();
        workDaysRadioButton.setToggleGroup(toggleGroup);
        weekendRadioButton.setToggleGroup(toggleGroup);

        // Listener for toggle group's selected toggle property
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Update timetable immediately when the toggle changes
                updateTimetable();
            }
        });

        routesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Update timetable when a route is selected
                updateTimetable();
                removeButton.setDisable(false);
            }
        });
    }

    // Method to update timetable based on selected route and radio button
    private void updateTimetable() {
        System.out.println("Updating timetable..."); // Debug
        RouteFavourite selectedRoute = routesList.getSelectionModel().getSelectedItem();
        if (selectedRoute != null) {
            int frequency;
            try {
                if (workDaysRadioButton.isSelected()) {
                    frequency = frequencyToMinutes(selectedRoute.getRoute().getFrequency());
                } else {
                    frequency = frequencyToMinutes(weekendFrequency(selectedRoute.getRoute().getFrequency()));
                }
                List<TimeTable> timetableData = generisiListu(300, 1380, frequency);
                System.out.println("Timetable data size: " + timetableData.size()); // Debug
                timetable.setItems(FXCollections.observableList(timetableData));
            } catch (AppException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String weekendFrequency(String frequency){
        switch(frequency){
            case "5 min":
                return "20 min";
            case "15 min":
                return "30 min";
            case "20 min":
                return "50 min";
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

    private List<TimeTable> generisiListu(int start, int end, int frequency){
        List<TimeTable> list = new ArrayList<>();
        for(int i = start; i<end; i+=frequency){
            list.add(new TimeTable(minutesToTime(i), minutesToTime(i+frequency)));
        }
        return list;
    }

    private int frequencyToMinutes(String frequency) throws AppException {
        if(frequency.contains("min") && frequency.contains("h")){
            String[] array = frequency.split(" ");
            return Integer.parseInt(array[0])*60 + Integer.parseInt(array[2]);
        }
        else if(frequency.contains("min")){
            return Integer.parseInt(frequency.replaceAll("min", "").trim());
        }
        else if(frequency.contains("h")){
            return Integer.parseInt(frequency.replaceAll("h", "").trim())*60;
        }
        throw new AppException("Invalid frequency");
    }

    private String minutesToTime(int minutes){
        int hours = minutes/60;
        minutes = minutes%60;
        return hours + ":" + minutes;
    }

    /**
     * On click listener method for back button. Redirects user to dashboard.
     */
    public void backButtonOnClick(ActionEvent actionEvent) throws IOException, AppException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
        loader.setController(new DashboardController(profileManager.getById(userId).getEmail()));
        stage.setTitle("JavniPrevozKS");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/icon.png"));
        stage.show();
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        currentStage.close();
    }

    /**
     * On click listener method for remove button. Removes route from favourites.
     */
    public void removeButtonOnClick(ActionEvent actionEvent) throws AppException {
        RouteFavourite selectedRoute = routesList.getSelectionModel().getSelectedItem();
        routeFavouriteManager.deleteRoute(selectedRoute.getId());
        routesList.getItems().remove(selectedRoute);
        removeButton.setDisable(true);
    }
}
