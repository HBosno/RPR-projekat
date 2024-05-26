package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.*;
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
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Controller for managing favourite routes interface interactions.
 * @author Hamza Bosno
 */
public class FavouriteRoutesController {

    private TransportSystemFacade facade;
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
        RouteFavouriteManager routeFavouriteManager = new RouteFavouriteManager();
        ProfileManager profileManager = new ProfileManager();
        TimeTableGenerator timeTableGenerator = new TimeTableGenerator();
        this.facade = new TransportSystemFacade(routeFavouriteManager, profileManager, timeTableGenerator);
    }

    /**
     * Initialize method that populates listview with favourite routes from database and regulates displayed info based on radiobutton
       selections. Makes sure only one radiobutton can be selected at time, and user cannot deselect both radio buttons. Handles
       remove button enabling.
     */
    @FXML
    public void initialize() throws AppException {
        List<RouteFavourite> favouriteRoutes = facade.getUserFavouriteRoutes(userId);
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
        RouteFavourite selectedRoute = routesList.getSelectionModel().getSelectedItem();
        if (selectedRoute != null) {
            try {
                boolean isWeekend = weekendRadioButton.isSelected();
                List<TimeTable> timetableData = facade.generateTimeTable(selectedRoute.getRoute().getFrequency(), isWeekend);
                timetable.setItems(FXCollections.observableList(timetableData));
            } catch (AppException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * On click listener method for back button. Redirects user to dashboard.
     */
    public void backButtonOnClick(ActionEvent actionEvent) throws IOException, AppException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
        loader.setController(new DashboardController(facade.getUserEmail(userId)));
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
        facade.deleteRoute(selectedRoute.getId());
        routesList.getItems().remove(selectedRoute);
        removeButton.setDisable(true);
    }
}
